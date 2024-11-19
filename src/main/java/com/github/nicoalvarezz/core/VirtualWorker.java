package com.github.nicoalvarezz.core;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class VirtualWorker implements BaseWorker {

    SocketChannel clientChannel;

    /**
     * Buffer size for reading requests and writing responses
     */
    private static final int BUFFER_SIZE = 8192;

    public VirtualWorker(SocketChannel socket) {
        this.clientChannel = socket;
    }

    @Override
    public void run() {
        System.out.println("Handling request on thread: " + Thread.currentThread());
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

        try {
            int bytesRead = clientChannel.read(buffer);
            if (bytesRead == -1) {
                System.out.println("Client closed connection.");
                return;
            }

            buffer.flip(); // prepare buffer for reading
            String clientRequest = new String(buffer.array(), 0, buffer.limit());
            System.out.printf("Received request: %s%n", clientRequest);

            // Simple response (this can be extended for proper request routing and proxying)
            String httpResponse = "HTTP/1.1 200 OK\r\nContent-Length: 13\r\n\r\nHello, world!";
            buffer.clear(); // Clear the buffer
            buffer.put(httpResponse.getBytes());
            buffer.flip(); // Prepare buffer for writing
            clientChannel.write(buffer); // Send response
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void handleSignal() {

    }
}
