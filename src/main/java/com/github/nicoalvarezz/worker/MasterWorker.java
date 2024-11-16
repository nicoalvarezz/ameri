package com.github.nicoalvarezz.worker;

import com.github.nicoalvarezz.config.Config;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.nio.ByteBuffer;

public class MasterWorker implements BaseWorker {
    private final Config config;
    private final ServerSocketChannel socket;

    public MasterWorker(Config config) {
        this.config = config;
        this.socket = listener();
    }

    @Override
    public void handleSignal() {

    }

    @Override
    public void start() {
        while (true) {
            try {
                SocketChannel clientSocket = socket.accept();
                ExecutorService executor = Executors.newFixedThreadPool(config.getNumWorkers());
                if (clientSocket != null) {
                    System.out.println("hello?");
                    executor.submit(() -> handleRequest(clientSocket));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void cleanup() {

    }

    private ServerSocketChannel listener() {
        try {
            ServerSocketChannel socket = ServerSocketChannel.open();
            socket.configureBlocking(false); // Non-blocking from the start
            socket.bind(new InetSocketAddress(config.getAddress(), config.getPort()));
            return socket;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return socket;
    }

    private void handleRequest(SocketChannel clientSocket) {
        ByteBuffer buffer = ByteBuffer.wrap("HTTP/1.1 200 OK\r\n\r\nHello, World!".getBytes());
        try {
            clientSocket.write(buffer);
        } catch (IOException e){
            System.err.println("Error handling request: " + e.getMessage());
        }
    }
}
