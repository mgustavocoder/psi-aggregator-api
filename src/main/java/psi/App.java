package psi;

import psi.controller.PageSpeedCtrl;
import psi.controller.WebsiteCtrl;
import psi.dto.Response;

import java.net.ConnectException;

import static spark.Spark.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {

        port(getHerokuAssignedPort());

        staticFileLocation("/webapp");

        notFound((request, response) -> new Response(404, "Not Found2").toJson());

        exception(ConnectException.class, (exception, request, response) -> new Response(500, "ConnectException").toJson());

        internalServerError((request, response) -> new Response(500, "Unknown Error").toJson());

        after((request, response) -> response.header("Content-Type", "application/json"));

        after(((request, response) -> response.header("Access-Control-Allow-Origin","*")));

        new WebsiteCtrl();
        new PageSpeedCtrl();

    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 8080; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
