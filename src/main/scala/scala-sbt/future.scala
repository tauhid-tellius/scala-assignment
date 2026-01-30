/**
  * Combine results from two futures in Scala with proper error logging in case of failures of either of the future.
  */

import java.util.Random
import scala.util.{Failure, Success}
import scala.concurrent.{Future, ExecutionContext}
implicit val ec: ExecutionContext = ExecutionContext.global

object Futures extends App {

    val random = new Random()

     val numberResult = Future {
        val number = random.nextInt(1000)
        if (number % 2 == 0) number
        else throw new RuntimeException("Failure ...")
    }

    val stringResult = Future {
        "Alice"
    }

    val combineResult: Future[(String, Int)] =
        for {
            name   <- stringResult
            number <- numberResult
        } yield (name, number)


   
    combineResult.onComplete {
        case Success(value) => 
            println(s"[SUCCESS] $value")
        case Failure(exception) => 
            println(s"[FAILURE] ${exception.getMessage()}")
    }


}
