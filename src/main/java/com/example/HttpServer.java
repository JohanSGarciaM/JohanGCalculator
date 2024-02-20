package com.example;

import java.net.ServerSocket;
import java.net.Socket;

import java.io.*;
import org.json.JSONObject;

public class HttpServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(36000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 36000.");
            System.exit(1);
        }

        Boolean running = true;
        while (running) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine;
            boolean firstLine = true;
            String name = "";
            String path = null;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                if (firstLine) {
                    firstLine = false;
                    path = inputLine.split(" ")[1];
                }
                if (inputLine.contains("GET") && inputLine.contains("=")) {
                    name = inputLine.split("=")[1].split(" ")[0];
                }
                if (!in.ready()) {
                    break;
                }
            }
            outputLine = "HTTP/1.1 200 OK \r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n";

            System.out.println(path);
            if (path.startsWith("/calculadora")) {
                outputLine += getResponse();
            } else {
                outputLine += calculate(path);
            }

            out.println(outputLine);

            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();

        /**
         * outputLine = "HTTP/1.1 200 OK\r\n"
         * + "Content-Type: text/html\r\n"
         * + "\r\n"
         * + "<!DOCTYPE html>\n"
         * + "<html>\n"
         * + "<head>\n"
         * + "<meta charset=\"UTF-8\">\n"
         * + "<title>Title of the document</title>\n"
         * + "</head>\n"
         * + "<body>\n"
         * + "
         * <h1>Mi propio mensaje</h1>\n"
         * + "</body>\n"
         * + "</html>\n";
         **/


    }

    private static String calculate(String path) throws IOException {
        ServiceFacade fachada = ServiceFacade.getInstance();
        String info = fachada.calculate(path);
        JSONObject resp = new JSONObject(info);
        String response = "Content-Type: text/json \r\n"
                + "\r\n";
                

        return response;
    }

    public static String getResponse() {

        String response = "<!DOCTYPE html>\r\n" + //
                "<html>\r\n" + //
                "    <head>\r\n" + //
                "        <title>Form Example</title>\r\n" + //
                "        <meta charset=\"UTF-8\">\r\n" + //
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + //
                "    </head>\r\n" + //
                "    <body>\r\n" + //
                "        <h1>Calculadora</h1>\r\n" + //
                "        <form action=\"/hello\">\r\n" + //
                "            <label for=\"operation\">Operation : </label><br>\r\n" + //
                "            <input type=\"text\" id=\"operation\" name=\"operation\" value=\"\"><br><br>\r\n" + //
                "            <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\r\n" + //
                "        </form> \r\n" + //
                "        <div id=\"getrespmsg\"></div>\r\n" + //
                "\r\n" + //
                "        <script>\r\n" + //
                "            function loadGetMsg() {\r\n" + //
                "                let operator = document.getElementById(\"operation\").value;\r\n" + //
                "                let numbers = document.getElementById(\"operation\").value;\r\n" + //
                "                const xhttp = new XMLHttpRequest();\r\n" + //
                "                xhttp.onload = function() {\r\n" + //
                "                    document.getElementById(\"getrespmsg\").innerHTML =\r\n" + //
                "                    this.responseText;\r\n" + //
                "                }\r\n" + //
                "                xhttp.open(\"GET\", \"/hello?operation=\"+operator);\r\n" + //
                "                xhttp.send();\r\n" + //
                "            }\r\n" + //
                "        </script>\r\n" + //
                "    </body>\r\n" + //
                "</html>";

        return response;
    }
}
