trait Product {
	def show()
}
case class Mouse() extends Product {
	override def show {
		printf("A mouse has been built")
	}
}
case class Keyboard() extends Product {
	override def show {
		printf("A keyboard has been built")
	}
}
trait Factory {
	def produce():Product
}
object MouseFactory extends Factory{//用object代替class
override def produce() = Mouse()
}
object KeyboardFactory extends Factory{
	override def produce() = Keyboard()
}
object Test extends App {
	val mouse: Product = MouseFactory.produce
	val keyboard: Product = KeyboardFactory.produce
	mouse.show()
	keyboard.show()
}