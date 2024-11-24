package com.github.nicoalvarezz.core;

import java.nio.channels.SocketChannel;

public class VirtualWorker {
    private final SocketChannel clientChannel;

    public VirtualWorker(SocketChannel clientChannel) {
        this.clientChannel = clientChannel;
        // basic config stuff
        // buffer pool
        // metrics
        // timeout
    }

    public void run() {
        // here we will load balance the connection and pick an upstream server
        // for now is a simple architecture, so will only have one upstream connection
        processRequest();
    }

    private void processRequest() {
        while(!Thread.currentThread().isInterrupted()) {
            // read request from the client --- extract request context

        }
    }

    private void readRequest() {

    }

    private void forwardRequest() {

    }

    private void sendResponse() {

    }
}
