package com.jensui;

//import org.glassfish.grizzly.http.server.HttpServer;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;

;

/**
 * Created by christian on 11/6/16.
 */
public class Server {

    private HttpServer server;

    public static final String BASE_URI = "http://localhost:8088/raspi";

    public void start() throws Exception {
        server = HttpServerFactory.create(BASE_URI);
        server.start();
        System.out.println("server started");
    }

    public void shutdown() throws Exception {
        server.stop(0);
        //server.shutdown();
        System.out.println("server shut down");
    }

}
