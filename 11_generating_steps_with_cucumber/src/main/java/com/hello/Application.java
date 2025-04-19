package com.hello;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

/**
 * Main application class that serves as the entry point for the web application.
 * This class creates a simple HTTP server using Java's built-in HttpServer.
 */
public class Application {

    /**
     * Main method that starts the application
     * @param args command line arguments
     */
    public static void main(String[] args) throws IOException {
        // Get port from environment or use default
        int port = getPort();
        
        // Create HTTP server
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        
        // Set up context for root path
        server.createContext("/", exchange -> {
            String response = "Welcome to the BDD Testing Demo!";
            exchange.sendResponseHeaders(200, response.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        });
        
        // Start the server
        server.start();
        
        System.out.println("Server is running on port " + port);
        System.out.println("Press Ctrl+C to stop the server");
    }
    
    /**
     * Get the port from environment variable or use default
     * @return port number
     */
    private static int getPort() {
        String portEnv = System.getenv("PORT");
        if (portEnv != null) {
            try {
                return Integer.parseInt(portEnv);
            } catch (NumberFormatException e) {
                System.out.println("Invalid PORT value in environment, using default: 8080");
            }
        }
        return 8080;
    }
}