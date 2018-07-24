## Task2

### 一.scala的主要特性

#### 1.**面向对象**: 

Scala是面向对象的编程语言，所有的变量和方法都封装在对象中，可以把信息封装起来供外部使用，这个跟java相同不做解释。

#### 2.函数式编程

说起函数式编程，首先会想到的就是命令式编程。自己对函数式编程的理解，先前还是只停留在只要是只要运用了高阶函数就是函数式编程，虽然这在某些方面是对的，但是又不全对。现在看来，其实不必去特意地去定义，函数式编程是一种思想，要判断它是不是函数式编程，其实是要回归到原点，函数式编程目的是用计算来表示程序, 用计算的组合来表达程序的组合，而不是像命令式编程一样告诉机器怎么去做，过程按照步骤一步一步地来。简单来说，后者需要知道的是how，而前者只需知道what，让机器自己思考怎么去做。

##### 2.1.函数是一等公民

说到函数是一等公民这个概念，通俗地来说就是和其他基本类型一样，可以出现在任何地方，它可以作为参数传到另一个函数，又可以作为另一个函数的返回值。

```scala
scala> val numbers = List(1, 2, 3, 4)
numbers: List[Int] = List(1, 2, 3, 4)

scala> numbers.map((i: Int) => i * 2)
res0: List[Int] = List(2, 4, 6, 8)
```

**2.2. 只用表达式，不用语句**

"表达式"是一个单纯的运算过程，总是有返回值；"语句"（statement）是执行某种操作，没有返回值。函数式编程要求，只使用表达式，不使用语句。也就是说，每一步都是单纯的运算，而且都有返回值。

原因是函数式编程的开发动机，一开始就是为了处理运算（computation），不考虑系统的读写（I/O）。"语句"属于对系统的读写操作，所以就被排斥在外。

当然，实际应用中，不做I/O是不可能的。因此，编程过程中，函数式编程只要求把I/O限制到最小，不要有不必要的读写行为，保持计算过程的单纯性。

##### 2.3.变量不可变(副作用)

##### 2.4.引用透明性

##### 2.5.惰性传值

其实函数式这个特点范围很大，要想定义确实很难。在我理解看来，支持高阶函数，就是函数式编程。它的特点即函数可以独立存在，可以定义一个函数作为另一个函数的返回值，也可以接受函数作为函数的参数。下面是一个**组合子**的例子

```scala
val nums = Array(1,2,3,4)
val doubleNums = nums.map(_*2)
```

这是一个简单的例子，可以函数式编程并不是让你告诉机器做的步骤，而是让他自己去想，这种编程思想的好处在后面与命令式编程的对比中再提。

#### 3.没有静态方法和静态字段

在使用*class*关键词时你会发现它并不能像*java*一样被执行，这是由于在*scala*中类只能被编译不能被执行。所以在*scala*中用*object*来实现这些功能，直接用对象名调用的方法都是采用这种实现方式，例如Array.toString，也就是说*scala*删除了*static*关键词，取而代之的就是*object*伴随对象，然而我个人认为这反而是一种损耗，毕竟类方法的执行效率还是高于实例方法的，不过，从某些方面比如单例模式来说，*scala*直接就从语法上支持了。

```scala
object Accounts {
  private var lastNumber=0
  def newUniqueNumber() = { lastNumber+=1, lastNumber}
}
```

同时*scala*也传递出对象即函数的思想，这里就拿*apply*举例，这是*scala*一个特殊的方法，先给一个通俗的解释。

> 当对象（伴生对象）以函数的方式进行调用时，scala 会隐式地将调用改为在该对象上调用apply方法。

例如： 

```scala
object Greeting{
  def apply(word: String) {
      print(word)
  }
}
```

Demo(“hello”) 实际调用的是 Demo.apply(“hello”), 所以apply方法又被称为注入方法，也就是说在调用对象的时候即调用了函数。

#### 4.类型推导

```scala
def func():String = {
  "hello"
}
// 可简写为
def func() = {
  "hello"
}
```

#### **5.模式匹配**

### 二.静态类型、动态类型、强类型、弱类型

静态类型：编译期间做检查数据类型，通俗理解就是写程序前要声明所有变量的类型，例c和java等

动态类型：运行期间才做数据类型检查，能够随时改变它的类型

弱类型：相对于强类型来说类型检查更不严格，比如说允许变量类型的隐式转换，允许强制类型转换等等

强类型：一般不允许这么做

## Task3

#### **1.什么是纯函数/引用透明性**

纯函数-输入输出数据流全是显式（Explicit）的，而非纯函数，输入输出则是隐式的（Implicit）。简单理解一下，显式的意思是函数与外界交换数据只有一个唯一渠道——参数和返回值，隐式的意思则是从外界获取数据，或者向外部输出数据。下面是个简单的例子

```scala
//纯函数
def add(a:Int,b:Int) = a + b
//非纯函数
var a = 1
def addA(b:Int) = a + b
```

函数式编程严格来说是没有变量的赋值行为，讲究的正是引用透明性，也就是说一个表达式返回一个值，那么它永远返回一个值，不会变。而且，函数的返回值只依赖于其输入值，这种特性就称为引用透明性。

不是纯函数，因为并没有通过参数交换数据，而是引用了同一个类的私有值。

#### **2.简述数学意义上集合、映射、函数、域和值域的概念，以及编程意义上函数与其对应关系**

集合：指具有某种特定性质的事物的总体

映射：两个元素的集合之间元素相互对应的关系

函数：可以定义为一类特殊的映射

域：非正式的讲，域是种集合

值域：因变量改变而改变的取值范围

从函数的结构上来看，不管是不是通过参数来交换数据，输入的数据就是集合，而里面对数据进行的操作则是一种函数方法，也就是一种特殊的映射，因为他在对每个数据进行处理，无论是输出还是改变值，其最后值的数值类型或者数值的集合就是值域。

#### **3.简述函数式编程和指令式以及面向对象的关联、区别**

函数式编程关心数据的映射，命令式编程关心解决问题的步骤。举个简单例子，反转二叉树，从命令式编程来解决，会按如下步骤考虑，判断节点是否为空，然后呢，反转左子树，反转右子数，最后交换左右子树，一步一步地来，而函数编程则是描述旧树到新树的映射，从最直观地角度来看，代码更加精简了。

而函数式编程和面向对象编程并不出于对立面，相反，这两者是正交的。更多的不同则是关注维度的不同，举个例子，假设我们要增加一个额外的减法，那么在函数式编程中，我们只需要增加一个减法函数即可，对于已有的代码无需做任何改动，在面向对象编程中则需要对基类和每个数据类添加减法操作；但假设我们要增加的是一个操作数类型，比如分数，那么在面向对象编程中，我们只需要增加一个类并让其继承基类，编写相关抽象方法的实现过程即可，而在函数式编程中则需要修改所有的已有函数，添加新的操作数类型。

#### **4.简述高阶函数，高阶类型，类型构造器**

函数的参数能够接收别的函数，或者能返回函数。

与其类似，高阶类型则是接受其他类型作为参数构造出来新类型的类型，比如scala里的type关键字。

类型构造器跟函数几乎是类似的，但前者是在类型层面。举个例子，你在日常的编程中可以给一个函数传入一个值 `a`，然后返回一个值 `b` 。于是在类型层面编程，你可以认为一个 `List[T]` 是一个类型构造器，表现如下：

- `List[T]` 有一个类型参数 (`T`)；
- 它本身并不是一个有效的类型，你需要填充 `T` 所在的地方来「构造类型」；
- 填上 `Int` 你就得到了一个具体的类型 `List[Int]` 。

可以看出，唯一不同的地方在于类型构造器处理的是类型，而不是对象的实例。

#### **5.什么是non-strict/strict**

strict通过抛出错误来消除了一些原有静默错误。

。。。