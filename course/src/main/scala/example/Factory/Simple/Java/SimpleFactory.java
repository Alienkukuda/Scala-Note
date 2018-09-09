package example.Factory.Simple.Java;

//定义产品接口
interface Product{
	void show();
}
//以下实现了具体产品类
class Mouse implements Product {
	@Override
	public void show() {
		System.out.println("A mouse has been built");
	}
}
class Keyboard implements Product {
	@Override
	public void show(){
		System.out.println("A keyboard has been built");
	}
}
class initFactory {
	public Product produce(String name) {
		switch (name) {
			case "Mouse":
				return new Mouse();
			case "Keyboard":
				return new Keyboard();
			default:
				throw new IllegalArgumentException();
		}
	}
}
//简单使用
public class SimpleFactory {
	public static void main(String[] args) {
		initFactory simpleFactory = new initFactory();
		Product mouse = simpleFactory.produce("Mouse");
		mouse.show();
	}
}