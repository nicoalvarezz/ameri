package com.github.nicoalvarezz.core;

import com.github.nicoalvarezz.config.ConfigManager;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class MasterWorker {
    private final ServerSocketChannel serverChannel;
    private final ConfigManager config;
    private volatile boolean running;
//    private final Selector selector;

    public MasterWorker(ConfigManager config) throws IOException {
        this.config = config;
        this.serverChannel = ServerSocketChannel.open();
        this.serverChannel.bind(new InetSocketAddress(config.getPort()));
        this.serverChannel.configureBlocking(false);
//        this.selector = Selector.open();
//        this.serverChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void start() {
        Thread.startVirtualThread(() -> {
            while (running) {
                try {
                    SocketChannel clientChannel = serverChannel.accept();
                    Thread.startVirtualThread(() -> handelConnection(clientChannel));
                } catch (IOException e) {
                    if (running) {
                        // log error and continue
                    }
                }
            }
        });
    }

    private void handelConnection(SocketChannel clientChannel) {
        // we create a new virtual worker per new request
        VirtualWorker worker = new VirtualWorker(clientChannel);
        worker.run();
    }
}
