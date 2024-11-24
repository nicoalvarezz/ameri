package com.github.nicoalvarezz.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class UpstreamConnection implements AutoCloseable {
    private final SocketChannel channel;

    public UpstreamConnection(UpstreamServer server) throws IOException {
        this.channel = SocketChannel.open();
        this.channel.connect(new InetSocketAddress(server.host(), server.port()));
        this.channel.configureBlocking(true);
    }

    public int writ() {
        return 0;
    }

    public int read(){
        return 0;
    }

    @Override
    public void close() throws Exception {

    }

}

