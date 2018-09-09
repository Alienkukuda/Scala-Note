trait Mouse {
	def show()
}
trait KeyBoard {
	def show()
}
case class Logitech() extends Mouse {
	override def show {
		printf("Logitech mouse has been built")
	}
}
case class Rapoo() extends Mouse {
	override def show {
		printf("Rapoo mouse has been built")
	}
}
case class Cherry() extends KeyBoard {
	override def show {
		printf("Cherry keyboard has been built")
	}
}
case class Razer() extends KeyBoard {
	override def show {
		printf("Razer keyboard has been built")
	}
}
trait Factory {
	def produceMouse()
	def produceKeyboard()
}
object FactoryA extends Factory {//用object代替class
	override def produceMouse() = Logitech()
	override def produceKeyboard() = Cherry()
}
object Test extends App {
	val mouse: Product = FactoryA.produceMouse()
	mouse.show()
}