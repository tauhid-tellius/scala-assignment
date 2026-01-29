error id: BD6B0153BCD5FC51B0E1BE58C89AFEB8
file://<WORKSPACE>/src/main/scala/scala-sbt/generic-fn.scala
### java.lang.IllegalArgumentException: Comparison method violates its general contract!

occurred in the presentation compiler.



action parameters:
offset: 1533
uri: file://<WORKSPACE>/src/main/scala/scala-sbt/generic-fn.scala
text:
```scala
/**
  * Implement a generic function for retrying a block of code
    Take a function as a parameter containing the block of code to retry
    Take max number of times to retry as a parameter
    Optional - Implement a backoff timeout for each failure

  */
import java.util.Random
import akka.actor.ActorSystem
import akka.actor.Cancellable


object GenericFunction extends App {

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

    
    def genericFunctionWithRetry[B](f : ? => B, maxRetry : Int, retryCount) : Unit = 
        val ok : Boolean = f()

        if (ok == true) {
            println("We got Prime Number ...")
            println("Stop the code")
        } else {
            
            if (retryCount == maxRetry) {
                println("Stoping code ... MaxRetry exceeded ....")
            } else {

                val system = ActorSystem("simpleActor")
                println("Did not find Prime ! Try again ...")
                
                val routine : Cancellable = system.scheduler.scheduleOnce() {
                    genericFunctionWithRetry(f, maxRetry, ret@@)
                }
            }

        }
}

```


presentation compiler configuration:
Scala version: 3.8.1-bin-nonbootstrapped
Classpath:
<WORKSPACE>/.bloop/root/bloop-bsp-clients-classes/classes-Metals-YAMpykjXSkKN5zlst6vZVA== [exists ], <HOME>/Library/Caches/bloop/semanticdb/com.sourcegraph.semanticdb-javac.0.11.2/semanticdb-javac-0.11.2.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala3-library_3/3.8.1/scala3-library_3-3.8.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/akka/akka-actor_3/2.8.8/akka-actor_3-2.8.8.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/akka/akka-testkit_3/2.8.8/akka-testkit_3-2.8.8.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/3.8.1/scala-library-3.8.1.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/config/1.4.2/config-1.4.2.jar [exists ], <HOME>/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/modules/scala-java8-compat_3/1.0.0/scala-java8-compat_3-1.0.0.jar [exists ], <WORKSPACE>/.bloop/root/bloop-bsp-clients-classes/classes-Metals-YAMpykjXSkKN5zlst6vZVA==/META-INF/best-effort [missing ]
Options:
-Xsemanticdb -sourceroot <WORKSPACE> -Ywith-best-effort-tasty




#### Error stacktrace:

```
java.base/java.util.TimSort.mergeLo(TimSort.java:781)
	java.base/java.util.TimSort.mergeAt(TimSort.java:518)
	java.base/java.util.TimSort.mergeForceCollapse(TimSort.java:461)
	java.base/java.util.TimSort.sort(TimSort.java:254)
	java.base/java.util.Arrays.sort(Arrays.java:1233)
	scala.collection.SeqOps.sorted(Seq.scala:732)
	scala.collection.SeqOps.sorted$(Seq.scala:81)
	scala.collection.immutable.List.scala$collection$immutable$StrictOptimizedSeqOps$$super$sorted(List.scala:83)
	scala.collection.immutable.List.scala$collection$immutable$StrictOptimizedSeqOps$$super$sorted(List.scala:83)
	scala.collection.immutable.StrictOptimizedSeqOps.sorted(StrictOptimizedSeqOps.scala:85)
	scala.collection.immutable.StrictOptimizedSeqOps.sorted$(StrictOptimizedSeqOps.scala:24)
	scala.collection.immutable.List.sorted(List.scala:83)
	dotty.tools.pc.completions.Completions.completions(Completions.scala:154)
	dotty.tools.pc.completions.CompletionProvider.completions(CompletionProvider.scala:139)
	dotty.tools.pc.ScalaPresentationCompiler.complete$$anonfun$1(ScalaPresentationCompiler.scala:197)
	scala.meta.internal.pc.CompilerAccess.withSharedCompiler(CompilerAccess.scala:149)
	scala.meta.internal.pc.CompilerAccess.$anonfun$1(CompilerAccess.scala:93)
	scala.meta.internal.pc.CompilerAccess.onCompilerJobQueue$$anonfun$1(CompilerAccess.scala:210)
	scala.meta.internal.pc.CompilerJobQueue$Job.run(CompilerJobQueue.scala:153)
	java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1136)
	java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:635)
	java.base/java.lang.Thread.run(Thread.java:840)
```
#### Short summary: 

java.lang.IllegalArgumentException: Comparison method violates its general contract!