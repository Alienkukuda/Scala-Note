trait Product {
	def show()
}
case class Mouse() extends Product {
	def show = println("A mouse has been built")
}
case class Keyboard() extends Product {
	def show = println("A keyboard has been built")
}
object SimpleFactory  {//object代替class
def produce(name: String): Product =  name match {
	case "Mouse" =>   Mouse()
	case "Keyboard" =>   Keyboard()
}
}
object Test extends App {
	val mouse: Product = SimpleFactory.produce("Mouse")
	mouse.show()
}