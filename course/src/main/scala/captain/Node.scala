package captain

/**
  * 节点, 一个节点是一棵树，他有一个元素，左子树，右子树
  */
case class Node[A](data: A, left: Tree[A], right: Tree[A]) extends Tree[A]
