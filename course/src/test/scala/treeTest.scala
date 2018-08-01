import captain.{Node, TNil}
import org.scalatest.{FlatSpec, Matchers}
import captain.Tree

/**
  * Created by dripower on 2018/7/19.
  */
class treeTest extends FlatSpec with Matchers {
  "A captain.Tree" should "be same after two reverse operation" in {
    val tree: Node[Int] = Node(1,Node(2,TNil,TNil),Node(3,TNil,TNil))
//    reverse(reverse(tree)) should be(tree)
    tree.reverse(tree.reverse(tree)) should be(tree)
  }
}
