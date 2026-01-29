error id: file://<WORKSPACE>/src/main/scala/scala-sbt/binary-tree.scala:[875..876) in Input.VirtualFile("file://<WORKSPACE>/src/main/scala/scala-sbt/binary-tree.scala", "/**
  * Implement Binary tree with the following logic
        Calculate the height of Binary tree
        Number of leaf nodes
        preorder traversal of tree
    Scala
        Should use case classes to represent the binary tree
        Should use recursion to implement the above 2 functions
        Should not use var or mutable data structures

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
    val n15 = Node(15, left = n12, right = n20)
    val n5 = Node(5, left = n25)

    // root
    val tree : BinaryTree[Int] = Some(Node(10, left = Some(Node(n5)), right = Some(Node(n15))))

    def 



}
")
file://<WORKSPACE>/file:<WORKSPACE>/src/main/scala/scala-sbt/binary-tree.scala
file://<WORKSPACE>/src/main/scala/scala-sbt/binary-tree.scala:39: error: expected identifier; obtained rbrace


Current stack trace:
java.base/java.lang.Thread.getStackTrace(Thread.java:1619)
scala.meta.internal.mtags.ScalaToplevelMtags.failMessage(ScalaToplevelMtags.scala:1250)
scala.meta.internal.mtags.ScalaToplevelMtags.$anonfun$reportError$1(ScalaToplevelMtags.scala:1236)
scala.meta.internal.metals.StdReporter.$anonfun$create$1(ReportContext.scala:148)
scala.util.Try$.apply(Try.scala:217)
scala.meta.internal.metals.StdReporter.create(ReportContext.scala:143)
scala.meta.pc.reports.Reporter.create(Reporter.java:10)
scala.meta.internal.mtags.ScalaToplevelMtags.reportError(ScalaToplevelMtags.scala:1233)
scala.meta.internal.mtags.ScalaToplevelMtags.methodIdentifier(ScalaToplevelMtags.scala:1144)
scala.meta.internal.mtags.ScalaToplevelMtags.emitTerm(ScalaToplevelMtags.scala:908)
scala.meta.internal.mtags.ScalaToplevelMtags.$anonfun$loop$16(ScalaToplevelMtags.scala:344)
scala.runtime.java8.JFunction0$mcV$sp.apply(JFunction0$mcV$sp.scala:18)
scala.meta.internal.mtags.MtagsIndexer.withOwner(MtagsIndexer.scala:53)
scala.meta.internal.mtags.MtagsIndexer.withOwner$(MtagsIndexer.scala:50)
scala.meta.internal.mtags.ScalaToplevelMtags.withOwner(ScalaToplevelMtags.scala:49)
scala.meta.internal.mtags.ScalaToplevelMtags.loop(ScalaToplevelMtags.scala:344)
scala.meta.internal.mtags.ScalaToplevelMtags.indexRoot(ScalaToplevelMtags.scala:96)
scala.meta.internal.metals.SemanticdbDefinition$.foreachWithReturnMtags(SemanticdbDefinition.scala:83)
scala.meta.internal.metals.Indexer.indexSourceFile(Indexer.scala:560)
scala.meta.internal.metals.Indexer.$anonfun$reindexWorkspaceSources$3(Indexer.scala:691)
scala.meta.internal.metals.Indexer.$anonfun$reindexWorkspaceSources$3$adapted(Indexer.scala:688)
scala.collection.IterableOnceOps.foreach(IterableOnce.scala:630)
scala.collection.IterableOnceOps.foreach$(IterableOnce.scala:628)
scala.collection.AbstractIterator.foreach(Iterator.scala:1313)
scala.meta.internal.metals.Indexer.reindexWorkspaceSources(Indexer.scala:688)
scala.meta.internal.metals.MetalsLspService.$anonfun$onChange$2(MetalsLspService.scala:936)
scala.runtime.java8.JFunction0$mcV$sp.apply(JFunction0$mcV$sp.scala:18)
scala.concurrent.Future$.$anonfun$apply$1(Future.scala:691)
scala.concurrent.impl.Promise$Transformation.run(Promise.scala:500)
java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1136)
java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:635)
java.base/java.lang.Thread.run(Thread.java:840)

}
^
#### Short summary: 

expected identifier; obtained rbrace