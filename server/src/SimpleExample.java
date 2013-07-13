import static spark.Spark.*;

import spark.Request;
import spark.Response;
import spark.Route;

/**
 * A simple example just showing some basic functionality
 */
public class SimpleExample {

  public static void main(String[] args) {

    //  setPort(5678); <- Uncomment this if you wan't spark to listen on a port different than 4567.

    get(new Route("/hello") {
      @Override
      public Object handle(Request request, Response response) {
        return "Hello World!";
      }
    });

    post(new Route("/hello") {
      @Override
      public Object handle(Request request, Response response) {
        return "Hello World: " + request.body();
      }
    });

    get(new Route("/private") {
      @Override
      public Object handle(Request request, Response response) {
        response.status(401);
        return "Go Away!!!";
      }
    });

    get(new Route("/users/:name") {
      @Override
      public Object handle(Request request, Response response) {
        return "Selected user: " + request.params(":name");
      }
    });

    get(new Route("/news/:section") {
      @Override
      public Object handle(Request request, Response response) {
        response.type("text/xml");
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><news>" + request.params("section") + "</news>";
      }
    });

    get(new Route("/protected") {
      @Override
      public Object handle(Request request, Response response) {
        halt(403, "I don't think so!!!");
        return null;
      }
    });

    get(new Route("/redirect") {
      @Override
      public Object handle(Request request, Response response) {
        response.redirect("/news/world");
        return null;
      }
    });

    get(new Route("/") {
      @Override
      public Object handle(Request request, Response response) {
        return "root";
      }
    });

  }
}