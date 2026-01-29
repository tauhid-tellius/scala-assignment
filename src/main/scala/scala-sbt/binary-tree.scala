/**
  * Implement Binary tree with the following logic
        Calculate the height of Binary tree
        Number of leaf nodes
        preorder traversal of tree
    Scala
        Should use case classes to represent the binary tree
        Should use recursion to implement the above 2 functions
        Should not use var or mutable data structures

    Example Binary Tree 

        10
       /  \
      5   15
     /   /  \
    25  12  20


  */

object Main extends App {
  
    case class Node[A](
        value: A,
        left: Option[Node[A]] = None,
        right: Option[Node[A]] = None
    )

    type BinaryTree[A] = Option[Node[A]]
    
    // leaf node
    val n12 = Node(12)
    val n20 = Node(20)
    val n25 = Node(25)

    // intermediate nodes
    val n15 = Node(15, left = Some(n12), right = Some(n20))
    val n5 = Node(5, left = Some(n25))

    // root
    val tree: BinaryTree[Int] = Some( Node(10, left = Some(n5), right = Some(n15)))

    def heightOfTree[A](root : BinaryTree[Int]) : Int = 
        root match
            case None => 0
            case Some(node) =>
                1 + math.max(heightOfTree(node.left), heightOfTree(node.right))

    
    def numberOfLeafNode[A](root : BinaryTree[Int]) : Int = 
        root match
            case None => 0
            case Some(node)
                if (node.left == None && node.right == None) => 1 
            case Some(node) => numberOfLeafNode(node.left) + numberOfLeafNode(node.right)

    
    def inOrder[A](root : BinaryTree[Int]) : Unit = 
        root match
            case None => return
            case Some(node) =>
                inOrder(node.left)
                println(node.value)
                inOrder(node.right)
        

                
                
    println("InOrder Traversal of Tree")
    println(inOrder(tree))

    println("Height of Tree")
    println(heightOfTree(tree))

    println("Number of leaf node")
    println(numberOfLeafNode(tree))

}
