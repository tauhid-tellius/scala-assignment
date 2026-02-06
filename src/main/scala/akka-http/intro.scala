import spray.json._
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.HttpEntity
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, StatusCodes}
import JSONSerialise.user
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.Http

case class User(id : Int, name : String, email : String)

trait  UserJsonProtocol extends DefaultJsonProtocol {
    implicit val userFormat : RootJsonFormat[User] = jsonFormat3(User.apply)
}

object intro extends App with UserJsonProtocol {
    implicit val system : ActorSystem = ActorSystem("UserHttp")
    implicit val materializer : ActorMaterializer = ActorMaterializer()
    import system.dispatchers

    var Users = List(
        User(1, "Thomas", "thomas.th@gmail.com"),
        User(2, "Alice", "alice@gmail.com"),
        User(3, "Bob", "bob@gmail.com"),
        User(4, "Tim", "tim@gmail.com"),
    )

    /**
      * /POST route to create user POST(api/user)
        /PATCH to update user(ID should be passed as query parameter) PATCH(api/user?id=X)
        /GET to get all the users GET(api/user)
        /GET a specific user(ID is passed as query path parameter GET(api/user?id=X)
      */


    val userRoutes = 
        pathPrefix("api" / "user") {
            get {
                (path(IntNumber) | parameter("id".as[Int])) { id =>
                    complete(
                        HttpEntity(
                            ContentTypes.`application/json`,
                            Users.find(_.id == id).toJson.prettyPrint
                        )
                    )
                } ~
                pathEndOrSingleSlash {
                    complete(
                        HttpEntity(
                            ContentTypes.`application/json`,
                            Users.toJson.prettyPrint
                        )
                    )
                }
            } ~
            (post & pathEndOrSingleSlash) {
                entity(as[User]) { user =>
                    Users = Users :+ user
                    complete(StatusCodes.Created, "User created")
                }
            } ~
            (patch & parameter("id".as[Int])) { id =>
                entity(as[User]) { updatedUser =>
                    val index = Users.indexWhere(_.id == id)
                    if (index != -1) {
                    // Replace the user at that index
                    Users = Users.updated(index, updatedUser.copy(id = id)) 
                    complete(StatusCodes.OK, "User updated")
                    } else {
                    complete(StatusCodes.NotFound, "User not found")
                    }
                }
            }
        }


    Http().bindAndHandle(userRoutes, "localhost", 8080)

}
