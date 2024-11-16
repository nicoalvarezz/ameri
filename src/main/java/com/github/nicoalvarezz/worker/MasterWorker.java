package com.github.nicoalvarezz.worker;

import com.github.nicoalvarezz.config.Config;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

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
                if (clientSocket != null) {
                    System.out.println("Accepted connection from " + clientSocket.getRemoteAddress());
                    clientSocket.close(); // For now, we close immediately
                }
                Thread.sleep(100); // Just to prevent a tight loop
            } catch (IOException | InterruptedException e) {
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
}
