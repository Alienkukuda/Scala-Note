# Task 2  

## 1. 列举 Scala 的主要特性 

-  Scala是纯面向对象语言 

几乎所有数值都是对象，对象的类型和行为是由class和trait来描述的，可以使用 `1.toString` 等方法。

-  特质（trats）  

类似于加强版的接口（可以提供方法和字段的实现），关键字是 `with xxx`，构造顺序：超类构造器->特质构造器->类构造器。 从左到右，父特质优先构造（只构造一次），再子类构造。

-  类（class） 

类自带 getter setter 方法(通过.调用), @BeanProperty 注解生成 JavaBeans 的getXxx／setXxx方法（具体情况依据参数类型）。

-  类型推断  

Scala 是一门静态类型语言，但是有强大的类型推断，一般情况下变量或函数都可以不声明类型（递归函数中需要声明类型）。

-  语句／表达式 

Scala 中没有语句，最后一个表达式的值就是返回值 (如 `if(a==1) 0 else -1`，或者有值返回的函数也可以不用`return`)。

-  模式匹配(pattern match)

Scala里没有 `switch` ，模式匹配有：值匹配，类型匹配，匹配数组等。如果需要匹配所有的模式，需要给模式添加守卫（任何Boolean）。

-  object  

Scala不能定义静态成员，取而代之的是 obejct 定义的单例对象。  

包含 main 接口的 object 实例，就是程序的入口。  

当单例对象与某个类共享同一个名称时，它就被称为是这个类的伴生对象。（必须定义在同一个源文文件中）

-  函数 

`map`，`foreach`（没有返回值），`filter`，`zip`（组合），`flatten`（合并），`flatMap`（结合映射[mapping]和扁平化[flattening]），`partition`（分割），柯里化 currying （两个参数的函数变成一个函数的过程）以及一些高阶函数。

```
(1 to 9).map(0.1 * _)  // 产出0.1 - 0.9

(1 to 4).map("*" * _).foreach(println _) // 输出三角形

(1 to 9).filter(_ % 2 == 0) // 2, 4, 6 ,8  

(1 to 9).reduceleft(_ * _) // 等同于 1 * 2 * 3 * 4....* 9 

```

- 与Java和.NET进行互操作  

Scala 有像 Java 和 C# 一样的编译模型(独立编译，动态装载类)，允许访问成千上万的高质量类库。


## 2. 简述静态类型、动态类型、强类型、弱类型怎么理解  

弱类型相对强类型来说类型检查更不严格，比如型的隐说允许变量类式转换，允许强制类型转换等等。强类型语言一般不允许这么做。

静态类型指的是编译器在 compile time 执行类型检查，动态类型指的是编译器（虚拟机）在 runtime 执行类型检查。简单地说，在声明了一个变量之后，不能改变它的类型的语言，是静态语言；能够随时改变它的类型的语言，是动态语言。因为动态语言的特性，一般需要运行时虚拟机支持。


## 3. 扩展  

### 函数 vs 方法  

方法是一个以 `def` 开头的带有参数列表（可以无参数列表）的一个逻辑操作块，比如 `object` 或者 `class` 中的成员方法一样。

函数是一个赋值给一个变量（或者常量）的匿名方法（带或者不带参数列表），并且通过 `=>` 转换符号跟上逻辑代码块的一个表达式。`=>`转换符号后面的逻辑代码块的写法与 method 的 body 部分相同。

可以在方法后面加一个下划线强制变成函数，如果方法有重载，则需要指定参数和返回值类型。  

ex. 

```
def moo(a: Int, b: Int) = a + b 
val foo = moo _ 


object Tool {
     | def increment(n: Int): Int = n + 1
     | def increment(n: Int, step: Int): Int = n + step
     | }

val fun = Tool.increment _ //报错 error: ambiguous reference to overloaded definition
val fun = Tool.increment _ :((Int, Int) => Int)
```

### 模式匹配的内部实现

scala选择了采用 样本类(case class) 和 抽取器(extractor) 来实现模式匹配。

1. 样本类(case class)  

本质上 `case class` 是个语法糖，对你的类构造参数增加了 getter 访问，还有 toString, hashCode, equals 等方法，实现了 Serializable 特征；
最重要的是帮你实现了一个伴生对象，这个伴生对象里定义了 `apply` 方法和 `unapply` 方法。
`apply` 方法是用于在构造对象时，减少 `new` 关键字；而 unapply 方法则是为模式匹配所服务。
这两个方法可以看做两个相反的行为，apply 是构造(工厂模式)，unapply 是分解(解构模式)。  

` case class` 在暴露了它的构造方式，所以要注意应用场景：当我们想要把某个类型暴露给客户，但又想要隐藏其数据表征时不适宜。
  
2. 抽取器(extractor)  

抽取器是指定义了 `unapply` 方法的 `object`。在进行模式匹配的时候会调用该方法。
`unapply`方法接受一个数据类型，返回另一数据类型，表示可以把入参的数据解构为返回的数据。


# Task 3

## 1. 什么是纯函数 
函数本身不会修改参数的值也不会修改函数外的变量，无论执行多少次，同样的输入都会有同样的输出。

## 2. 简述函数式编程和指令式以及面向对象的关联、区别  

指令式语言更适合批处理脚本的编写，面向对象语言更适合GUI界面的处理，函数式语言则更适合大量数据的并行处理。

### 函数式 vs 指令式  

- 函数式减少了可变量(Immutable Variable)的声明，程序更为安全  

- 相比命令式编程，少了非常多的状态变量的声明与维护，适合高并发多现成并行计算等任务  

- 代码更为简洁，可读性更强  

### 函数式 vs 面向对象  

函数式编程语法更加自由，面向对象的健壮性更好。在 Scala 中，函数被封装到对象里（成为方法），所以可以像使用函数一样使用对象。

## 3. 什么是高阶函数，高阶类型   

### 高阶函数  

  高阶函数有以下几种表现形式

> 1. 一个或多个参数是函数，并返回一个值。
  2. 返回一个函数，但没有参数是函数。
  3. 上述两者叠加：一个或多个参数是函数，并返回一个函数。

  常见高阶函数：`map`，`filter`，`zip`，`flatten`，`flatMap`

### 高阶类型

高阶类型接受其他类型作为参数构造出来新类型的类型。

高阶类型可以有一个或者多个其他类型作为参数。在 Scala 里可以用 `type` 关键字来构造高阶类型。

ex.  

```
// 定义
type Callback[T] =Function[T,Unit]

val x:Callback[Int] = y => println(y+2)

x(1)  //3

``` 

如上代码所示，这里定义了一个叫做 `callback` 的高阶函数类型。`callback` 类型接受一个类型参数， 创建一个新的 `function` 类型，在参数化之前，`callback` 不是一个完整类型。 这里定义的 `callback` 可以用来简化那种接受一个参数且无返回值的函数的标签名字。


## 4. 什么是 non-strict/strict

### strict(严格):

>  a strict function always evaluates its arguments. 也就是说在调用函数时，会先把参数进行计算再传入。

例如：

```  
def strictFun(i: Int) = {
      println("strictFun in body")
      (i, i)
    }

    strictFun {
      println("strictFun outside")
      2
    }
```  

控制台输出：

```
strictFun outside
strictFun in body
```

### non-strict(非严格):

> function may choose not to evaluate one or more of its arguments. 在调用函数时，直接把参数表达式传入，并不进行计算。在函数体内被调用的地方进行计算.

例如：

```  
def nonStrictFun(i: => Int) = {
  println("nonStrictFun in body")
  (i, i)
}

nonStrictFun {
  println("nonStrictFun outside")
  2
}
```  

控制台输出：  

```  
nonStrictFun in body
nonStrictFun outside
nonStrictFun outside
```  
## 5. 扩展
- 关键词汇的英文名需要记住
- var类型不能递归调用自己(需要使用lazy)
- non-strict > lazy 
- ...

