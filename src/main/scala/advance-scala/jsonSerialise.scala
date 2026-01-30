import scala.annotation.implicitAmbiguous
/**
  * Write a JSON serializer using Implicits and Type classes
        a. A new case class be able to serialised into and from JSON using the serializer

  */
import JSONSerialise.JsonSyntax.JsonOps
import JSONSerialise.JsonDeserializationSyntax.JsonOps
import JSONSerialise.JsonDeserializerInstances._

object JSONSerialise extends App {

    sealed trait Json
    case class JsonObject(fields: Map[String, Json]) extends Json
    case class JsonString(value: String) extends Json
    case class JsonNumber(value: Int) extends Json
    case class JsonBoolean(value: Boolean) extends Json
    case object JsonNull extends Json

    trait JsonSerializer[A] {
        def toJson(values : A) : Json
    }

    object JsonSyntax {
        implicit class JsonOps[A](value: A) {
            def toJson(implicit serializer: JsonSerializer[A]): Json =
            serializer.toJson(value)
        }
    }

    object JsonSerializerInstances {
        implicit val stringSerializer: JsonSerializer[String] =
            (value: String) => JsonString(value)

        implicit val intSerializer: JsonSerializer[Int] =
            (value: Int) => JsonNumber(value)

        implicit val booleanSerializer: JsonSerializer[Boolean] =
            (value: Boolean) => JsonBoolean(value)
    }

    case class User(name : String, age : Int, isAdmin : Boolean)

    implicit val userSerialiser : JsonSerializer[User] =
        (user : User) =>
            JsonObject(
                Map(
                    "name" -> JsonString(user.name),
                    "age"  -> JsonNumber(user.age),
                    "isAdmin" -> JsonBoolean(user.isAdmin)
                )
            )

    
    val user = User("Alice", 21, true)
    val json : Json = user.toJson
    println(json)

    // Deserialise

    trait JsonDeserializer[A] {
        def fromJson(json: Json): Either[String, A]
    }

    object JsonDeserializationSyntax {
        implicit class JsonOps(json: Json) {
            def as[A](implicit deserializer: JsonDeserializer[A]): Either[String, A] =
            deserializer.fromJson(json)
        }
    }

    object JsonDeserializerInstances {

        implicit val stringDeserializer: JsonDeserializer[String] = {
            case JsonString(value) => Right(value)
            case _ => Left("Expected JSON string")
        }

        implicit val intDeserializer: JsonDeserializer[Int] = {
            case JsonNumber(value) => Right(value)
            case _ => Left("Expected JSON number")
        }

        implicit val booleanDeserializer: JsonDeserializer[Boolean] = {
            case JsonBoolean(value) => Right(value)
            case _ => Left("Expected JSON boolean")
        }
    }

    implicit val userDeserializer: JsonDeserializer[User] =
    new JsonDeserializer[User] {

        def fromJson(json: Json): Either[String, User] =
        json match {
            case JsonObject(fields) =>
            for {
                nameJson  <- fields.get("name").toRight("Missing field: name")
                ageJson   <- fields.get("age").toRight("Missing field: age")
                adminJson <- fields.get("isAdmin").toRight("Missing field: isAdmin")

                name  <- nameJson.as[String]
                age   <- ageJson.as[Int]
                admin <- adminJson.as[Boolean]
            } yield User(name, age, admin)

            case _ =>
            Left("Expected JSON object")
        }
    }

    val jsonSample : Json =
    JsonObject(
        Map(
        "name" -> JsonString("Alice"),
        "age" -> JsonNumber(30),
        "isAdmin" -> JsonBoolean(true)
        )
    )

    val result: Either[String, User] = jsonSample.as[User]

    println(result)

}

