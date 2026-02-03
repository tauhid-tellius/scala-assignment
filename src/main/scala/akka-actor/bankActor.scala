

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object bankActor extends App{

    val system = ActorSystem("SimpleActor")

    object BankAccount {
    case class Deposit(amount : Int)
    case class Withdraw(amount : Int)
    case object Statement

    case class TransactionSuccess(message : String)
    case class TransactionFailure(message : String)
    }

    class BankAccount extends Actor {
    var amounts = 0;

    import BankAccount._

    override def receive: Receive = {
        case Deposit(amount) =>
        if (amount < 0) sender() ! TransactionFailure("Invalid amount")
        else {
            amounts += amount
            sender() ! TransactionSuccess(s"Account deposited by amount $amount")
        }

        case Withdraw(amount) =>
        if (amount < 0) sender() ! TransactionFailure("Invalid amount")
        else if (amount > amounts) sender() ! TransactionFailure("insufficient amount")
        else {
            amounts -= amount
            sender() ! TransactionSuccess(s"Account withdrew amount $amount")
        }

        case Statement => sender() ! s"Your current amount is $amounts"
    }

    }

    object Person {
    case class LiveTheLife(account: ActorRef)
    }

    class Person extends Actor {
    import Person._
    import BankAccount._

    override def receive: Receive = {
        case LiveTheLife(account) =>
        account ! Deposit(10000)
        account ! Withdraw(90000)
        account ! Withdraw(500)
        account ! Statement

        case message => println(message)
    }
    }

    val bankAccount = system.actorOf(Props[BankAccount](), "bank")
    val person = system.actorOf(Props[Person](), "Alice")

    person ! Person.LiveTheLife(bankAccount)
}
