package example.Factory.Method.Java;

//定义工厂接口
interface Factory {
	Product produce();
}
//定义产品接口
interface Product{
	void show();
}
//以下实现了具体产品类
class Mouse implements Product {
	public void show() {
		System.out.println("A mouse has been built");
	}
}
class Keyboard implements Product {
	public void show(){
		System.out.println("A keyboard has been built");
	}
}
//以下实现了具体工厂类
class MouseFactory implements Factory {
	@Override
	public Product produce() {
		return new Mouse();
	}
}
class KeyboardFactory implements Factory {
	@Override
	public Product produce() {
		return new Keyboard();
	}
}
//简单使用
public class MethodFactory {
	public static void main(String[] args) {
		Factory mouseFactory =  new MouseFactory();
		Factory keyboardFactory =  new KeyboardFactory();
		mouseFactory.produce().show();//A mouse has been built
		keyboardFactory.produce().show();//A keyboard has been built
	}
}