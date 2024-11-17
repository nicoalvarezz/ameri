package com.github.nicoalvarezz.worker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Worker implements BaseWorker {

    Socket socket;

    public Worker(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("Handling request on thread: " + Thread.currentThread());
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
            OutputStream output = socket.getOutputStream();

            String line;
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                System.out.println("Request: " + line);
            }

            String httpResponse = """
                    HTTP/1.1 200 OK
                    Content-Type: text/plain
                    Content-Length: 13

                    Hello, World!
                    """;

            output.write(httpResponse.getBytes());
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleSignal() {

    }
}
