package com.kovko;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

/**
 * Hello world!
 *
 */
public class App 
{

    private static final String ENDPOINT = "/hello";
    private static final int PORT = 8080;

    public static void main(String[] args ) throws IOException {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(PORT);
        HttpServer server = HttpServer.create(inetSocketAddress, 0);
        server.createContext(ENDPOINT, new HelloHandler());
        server.start();
    }
}

class HelloHandler implements HttpHandler {

    private static final String RESPONSE_TEMPLATE = "<h1>Hello there!!!</h1><br><h1>%d</h1>";
    private int counter = 0;

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        ++counter;
        String response = String.format(RESPONSE_TEMPLATE, counter);
        OutputStream responseBody = httpExchange.getResponseBody();
        httpExchange.sendResponseHeaders(200, response.getBytes().length);
        responseBody.write(response.getBytes());
        responseBody.close();
    }
}