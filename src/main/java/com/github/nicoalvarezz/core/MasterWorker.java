package com.github.nicoalvarezz.core;

import com.github.nicoalvarezz.config.Config;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class MasterWorker implements BaseWorker {
    private final Config config;

    public MasterWorker(Config config) {
        this.config = config;
    }

    @Override
    public void run() {
        System.out.println("Starting MasterWorker on port: " + config.getPort());

        try (ServerSocketChannel serverSocket = ServerSocketChannel.open()) {
            serverSocket.bind(new InetSocketAddress(config.getAddress(), config.getPort()));
            serverSocket.configureBlocking(false); // non-blocking
            while (true) {
                SocketChannel clientSocket = serverSocket.accept();
                if (clientSocket != null) {
                    System.out.println("Connection accepted: " + clientSocket);
                    Thread.ofVirtual().start(() -> handleRequest(clientSocket));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleSignal() {

    }


    private void handleRequest(SocketChannel socket) {
        BaseWorker worker = new VirtualWorker(socket);
        worker.run();
    }
}
