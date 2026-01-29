/**
  * Implement a generic function for retrying a block of code
    Take a function as a parameter containing the block of code to retry
    Take max number of times to retry as a parameter
    Optional - Implement a backoff timeout for each failure

  */
//> using scala "2.13.12"
//> using dep "com.typesafe.akka:akka-actor_2.13:2.8.8"

import java.util.Random
import akka.actor.ActorSystem
import akka.actor.Cancellable
import scala.concurrent.duration._


object GenericFunction extends App {

    def genearteRandomIntAndCheckPrime() : Boolean = {
        val random = new Random()

        val randomNumber = random.nextInt(100)
        println(s"The random number is $randomNumber")

        if (randomNumber == 0 || randomNumber == 1) return false
        else if (randomNumber == 2) return true
        else {
            var i = 2;
            while (i * i <= randomNumber) {
                if (randomNumber % i == 0) return false
                i += 1
            }
            true
        }
    }

    
    def genericFunctionWithRetry[B](f : () => B, maxRetry : Int, retryCount : Int) : Unit = {
        val ok = f()

        if (ok == true) {
            println("We got Prime Number ...")
            println("Stop the code")
        } else {
            
            if (retryCount == maxRetry) {
                println("Stoping code ... MaxRetry exceeded ....")
            } else {

                val system = ActorSystem("simpleActor")
                val delay = math.pow(2, retryCount).toInt.seconds
                println(s"Did not find Prime ! Try again after $delay secons ...")
                import system.dispatcher

                
                val routine = system.scheduler.scheduleOnce(delay) {
                    genericFunctionWithRetry(f, maxRetry, retryCount + 1)
                    system.terminate()
                }
            }

        }
    }
    
    genericFunctionWithRetry(genearteRandomIntAndCheckPrime, 10, 1)
}
