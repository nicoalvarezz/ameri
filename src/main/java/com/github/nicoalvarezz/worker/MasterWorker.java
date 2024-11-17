package com.github.nicoalvarezz.worker;

import com.github.nicoalvarezz.config.Config;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MasterWorker implements BaseWorker {
    private final Config config;

    public MasterWorker(Config config) {
        this.config = config;
    }

    @Override
    public void run() {
        System.out.println("Starting MasterWorker on port: " + config.getPort());

        try (ServerSocket serverSocket = new ServerSocket(config.getPort())) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                if (clientSocket != null) {
                    System.out.println("Connection accepted: " + clientSocket);
                    Thread.ofVirtual().start(() -> forkRequest(clientSocket));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleSignal() {

    }

    private void forkRequest(Socket socket) {
        BaseWorker worker = new Worker(socket);
        worker.run();
    }
}
