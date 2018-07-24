import scala.util.Try

/**
  * Created by dripower on 2018/7/20.
  */
object io {
  sealed trait StdIO[A] {
    def map[B](f: A => B): StdIO[B] = StdIOMap(this, f)
    def flatMap[B](f: A => StdIO[B]): StdIO[B] = StdIOFlatMap(this, f)
  }
  object StdIO {
    def pure[A](a: A): StdIO[A] = StdIOPure(a)
    def read(): StdIO[String] = StdIORead
    def write(l: String): StdIO[Unit] = StdIOWrite(l)
    def calcTryFun(o: String): Try[(Int, Int) => Int] = {
      o match {
        case "+" => Try((a:Int, b:Int) => a + b)
        case "-" => Try((a:Int, b:Int) => a - b)
        case "*" => Try((a:Int, b:Int) => a * b)
        case "/" => Try((a:Int, b:Int) => a / b)
        case _ => Try((a:Int, b:Int) => -1)
      }
    }
    def calcFlatMap(left: StdIO[String], op: StdIO[String], right: StdIO[String]): StdIO[Try[Int]] = {//实现四则运算
      left.flatMap(
        l => op.flatMap(
          o => right.map(r =>{
            val ll = Try(l.toInt)
            val rr = Try(r.toInt)
            val oo = calcTryFun(o)//返回Try[(Int, Int) => Int]类型
            ll.flatMap(lll => rr.flatMap(rrr => oo.map(x => x(lll,rrr))))
//            val ll = l.toInt
//            val rr = r.toInt
//            val oo = calcTryFun(o)
//            oo.map(x => x(ll,rr))
            }
          )
        )
      )
    }
    def calcForYield(left: StdIO[String], op: StdIO[String], right: StdIO[String]): StdIO[Try[Int]] = {
        for {
          l <- left
          o <- op
          r <- right
        } yield {
          val ll = Try(l.toInt)
          val rr = Try(r.toInt)
          val oo = calcTryFun(o)
          for {
            lll <- ll
            x <- oo
            rrr <- rr
          } yield x(lll, rrr)
        }
    }
    def run[A](io: StdIO[A]): Try[A] = {
      io match {
        case StdIORead => Try(readLine().asInstanceOf[A])
        case StdIOPure(a: A) => Try(a)
        case StdIOWrite(l: String) => Try(print(l).asInstanceOf[A])
        case StdIOMap(io, f) => run(io).map(x => f(x))
        case StdIOFlatMap(io, f) => run(io).flatMap(x => run(f(x)))
      }
    }
    implicit class StdIOSyntax[A](io: StdIO[A]) {
      def run = StdIO.run(io)
    }
  }
  case class StdIOMap[A, B](io: StdIO[A], f: A => B) extends StdIO[B]
  case class StdIOFlatMap[A, B](io: StdIO[A], f: A => StdIO[B]) extends StdIO[B]
  case class StdIOPure[A](a: A) extends StdIO[A]
  case object StdIORead extends StdIO[String]
  case class StdIOWrite(l: String) extends StdIO[Unit]
  def main(args: Array[String]): Unit = {
//     StdIO.calcForYield(StdIO.read(),StdIO.read(),StdIO.read()).flatMap(x => print(x.map(_.toString()))).run //错误，返回的应该是StdIO类型
    StdIO.calcForYield(StdIO.read(),StdIO.read(),StdIO.read()).flatMap { x =>
      StdIO.write(x.map(_.toString()).getOrElse("输入错误"))
    }.run
  }
}

