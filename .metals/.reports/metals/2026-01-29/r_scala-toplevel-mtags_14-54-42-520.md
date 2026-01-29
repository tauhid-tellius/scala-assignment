error id: file://<WORKSPACE>/src/main/scala/scala-sbt/generic-fn.scala:[292..299) in Input.VirtualFile("file://<WORKSPACE>/src/main/scala/scala-sbt/generic-fn.scala", "/**
  * Implement a generic function for retrying a block of code
    Take a function as a parameter containing the block of code to retry
    Take max number of times to retry as a parameter
    Optional - Implement a backoff timeout for each failure

  */
import java.util.Random


object  extends App {

    def genearteRandomIntAndCheckPrime() : Boolean = 
        val random = Random()

        val randomNumber = random.nextInt(100)
        println(s"The random number is $randomNumber")

        if (randomNumber == 0 || randomNumber == 1) false
        else if (randomNumber == 2) true
        else {
            var i = 2;
            while (i * i <= randomNumber) {
                if (randomNumber % i == 0) false
                i += 1
            }
            true
        }

    
    def genericFunctionWithRetry[A, B](f : A => B, maxRetry : Int) : Unit = 
        val ok : Boolean = f()
        var retryCount = 0

        if (ok == true) {
            println("We got Prime Number ...")
            println("Stop the code")
        } else {
            
            if (retryCount == maxRetry) {
                println("Stoping code ... MaxRetry exceeded ....")
            } else {

                val system = ActorSystem("simpleActor")
                println("Did not find Prime ! Try again ...")
                genericFunctionWithRetry.scheduler
            }

        }
}
")
file://<WORKSPACE>/file:<WORKSPACE>/src/main/scala/scala-sbt/generic-fn.scala
file://<WORKSPACE>/src/main/scala/scala-sbt/generic-fn.scala:11: error: expected identifier; obtained extends


Current stack trace:
java.base/java.lang.Thread.getStackTrace(Thread.java:1619)
scala.meta.internal.mtags.ScalaToplevelMtags.failMessage(ScalaToplevelMtags.scala:1250)
scala.meta.internal.mtags.ScalaToplevelMtags.$anonfun$reportError$1(ScalaToplevelMtags.scala:1236)
scala.meta.internal.metals.StdReporter.$anonfun$create$1(ReportContext.scala:148)
scala.util.Try$.apply(Try.scala:217)
scala.meta.internal.metals.StdReporter.create(ReportContext.scala:143)
scala.meta.pc.reports.Reporter.create(Reporter.java:10)
scala.meta.internal.mtags.ScalaToplevelMtags.reportError(ScalaToplevelMtags.scala:1233)
scala.meta.internal.mtags.ScalaToplevelMtags.newIdentifier(ScalaToplevelMtags.scala:1107)
scala.meta.internal.mtags.ScalaToplevelMtags.emitMember(ScalaToplevelMtags.scala:788)
scala.meta.internal.mtags.ScalaToplevelMtags.loop(ScalaToplevelMtags.scala:263)
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

object  extends App {
        ^
#### Short summary: 

expected identifier; obtained extends