package captain

import java.io.{File, PrintWriter}

/**
  * Created by dripower on 2018/7/18.
  */
trait Tree[+A] {
  def scanLeft[R](init: R)(f: (R, A) => R): R ={
    this match {
      case TNil => init
      case Node(data,left,right) => left.scanLeft(f(init, data))(f);right.scanLeft(f(init, data))(f)
      case Node(data,left,TNil) => left.scanLeft(f(init, data))(f)
      case Node(data,TNil,right) => right.scanLeft(f(init, data))(f)
    }
  }
  /**
    * 获取当前node的值
    *
    * @return
    */
  def getValue(): String = {
    this match {
      case Node(value, left, right) => value.toString
      case _ => ""
    }
  }
  /**
    * 获得树的.dot格式字符串
    */
  def getTreeSeq(seqstr: String): String = {
    var str = seqstr
    this match {
      case TNil =>
      case Node(value, left, right) => {
        if (!left.getValue().equals("")) {
          println(value + " -> " + left.getValue())
          str = str + value + " -> " + left.getValue() + "\n"
        }
        if (!right.getValue().equals("")) {
          println(value + " -> " + right.getValue())
          str = str + value + " -> " + right.getValue() + "\n"
        }
        left.getTreeSeq(str)
        right.getTreeSeq(str)
      }
    }
    str
  }
  /**
    * 输出成一个简单的.dot格式文件
    */
  def printDot(): Unit = {
    val writer = new PrintWriter(new File("tree.dot"))
    writer.write("digraph tree {\n")
    writer.write(getTreeSeq(""))
    writer.write("}")
    writer.close()
  }
  /**
    * 左右反转
    */
  def reverse[A](tree: Tree[A]): Tree[A] = {
    tree match {
      case Node(value, left, right) => {
        Node(value, reverse(right), reverse(left))
      }
      case _ => TNil
    }
  }

//  def reverse(): Tree[A] = {
//    this match {
//      case Node(data, left, right) => Node(data, right.reverse(), left.reverse())
//      case _ =>TNil
//    }
//  }
}
