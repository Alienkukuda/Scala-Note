package example.Factory.Abstract.Java;

interface Mouse {
	void show();
}
interface Keyboard {
	void show();
}
class Logitech implements Mouse {
	public void show(){
		System.out.println("Logitech mouse has been built");
	}
}
class Rapoo implements Mouse {
	public void show(){
		System.out.println("Rapoo mouse has been built");
	}
}
class Cherry implements Keyboard {
	public void show() {
		System.out.println("Cherry keyboard has been built");
	}
}
class Razer implements Keyboard {
	public void show(){
		System.out.println("Razer keyboard has been built");
	}
}
interface Factory {
	Mouse produceMouse();
	Keyboard produceKeyboard();
}
class FactoryA implements Factory {//A工厂生产Logitech鼠标、Cherry键盘
	@Override
	public Mouse produceMouse() {
		return new Logitech();
	}
	@Override
	public Keyboard produceKeyboard() {
		return new Cherry();
	}
}
class FactoryB implements Factory {//B工厂生产Rapoo鼠标、Razer键盘
	@Override
	public Mouse produceMouse() {
		return new Logitech();
	}
	@Override
	public Keyboard produceKeyboard() {
		return new Cherry();
	}
}
//简单使用
public class AbstractFactory {
	public static void main(String[] args) {
		Factory factoryA = new FactoryA();
		factoryA.produceMouse().show();
		Factory factoryB = new FactoryB();
		factoryB.produceKeyboard().show();
	}
}