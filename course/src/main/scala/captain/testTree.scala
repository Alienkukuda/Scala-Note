package captain

/**
  * Created by dripower on 2018/7/19.
  */
object testTree {
  def main(args: Array[String]): Unit = {
    val tree: Node[Int] = Node(1,Node(2,TNil,TNil),Node(3,TNil,TNil))
    val fun = (init: Int,value: Int) => init + value
    println(tree.scanLeft(0)(fun))
//    tree.reverse()
    tree.reverse(tree)
    tree.printDot()
  }
}
