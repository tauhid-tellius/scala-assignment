/**
  * Write a function that takes implicit parameter and use the parameter inside the function
  */


object Implicit extends App {

    implicit val prefix : String = "Hello"

    def greet(name : String)(implicit prefix : String) : String = {
        s"$prefix , $name !!"
    }

    println(greet("Tauhid")(using "Hi"))

}
