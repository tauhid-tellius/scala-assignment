/**
  * Write a simple Logger Actor with support for following messages
        Log warn messages to one file
        Log info messages to one file
        Renaming a file to work as a rolling file appender
  */
import akka.actor._

object Logger extends App {

    val system = ActorSystem("loggingActor")

    object ActorWithLogging {
        sealed trait LogMessage
        case class Info(msg: String)  extends LogMessage
        case class Warn(msg: String)  extends LogMessage
        case class Error(msg: String) extends LogMessage
        case class Debug(msg: String) extends LogMessage
    }

    class ActorWithLogging extends Actor with ActorLogging {
        import ActorWithLogging._
        override def receive: Receive = 
            case Info(str)  => log.info(str.toString())
            case Warn(str)  => log.warning(str.toString())
            case Error(str) => log.error(str.toString())
            case Debug(str) => log.debug(str.toString())
            case _          => log.warning("Unknown Message")

    }

    import ActorWithLogging._
    val actorLogger = system.actorOf(Props[ActorWithLogging](), "ActorLogger")
    
    actorLogger ! Info("Info ...")
    actorLogger ! Warn("Warning ...")
    actorLogger ! Error("Error ...")
    actorLogger ! Debug("Debug ...")

    actorLogger ! "Other ..."

    
}
