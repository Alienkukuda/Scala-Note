### person comprehension

---

首先先看看`flatmap`,`foreach`, `filter`, `withFilter`这些词的定义

**flatmap**结合了映射[mapping]和扁平化[flattening]，简单来说需要一个处理嵌套列表的函数，然后将结果串连起来，如下面例子所示

```scala
scala> val nest = List(List(1, 2), List(3, 4))
nestedNumbers: List[List[Int]] = List(List(1, 2), List(3, 4))

scala> nest.flatMap(x => x.map(_ * 2))
res0: List[Int] = List(2, 4, 6, 8)
```

**foreach**很像map，但没有返回值。

```scala
scala> val doubled = numbers.foreach((i: Int) => i * 2)
doubled: Unit = ()
```

**filter**只传入任传入函数计算结果为true的元素。

```scala
scala> numbers.filter((i: Int) => i % 2 == 0)
res0: List[Int] = List(2, 4)
```

**yield** 

scala中for 循环中的 yield 会把当前的元素记下来，保存在集合中，循环结束后将返回该集合。

```scala
scala> val a = Array(1, 2, 3, 4, 5)
a: Array[Int] = Array(1, 2, 3, 4, 5)

scala> for (e <- a) yield e
res19: Array[Int] = Array(1, 2, 3, 4, 5)

scala> for (e <- a) yield e * 2
res20: Array[Int] = Array(2, 4, 6, 8, 10)
```

和for-yield之间的关系

```scala
for(x <- c1; y <- c2; z <-c3) {...}
//相当于
c1.foreach(x => c2.foreach(y => c3.foreach(z => {...})))
```

```scala
for(x <- c1; y <- c2; z <- c3) yield {...}
//相当于
c1.flatMap(x => c2.flatMap(y => c3.map(z => {...})))
```

```scala
for(x <- c; if cond) yield {...}
//相当于
c.filter(x => cond).map(x => {...})
```

```scala
for(x <- c; y = ...) yield {...}
//相当于
c.map(x => (x, ...)).map((x,y) => {...})
```

