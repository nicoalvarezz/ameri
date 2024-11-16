package com.github.nicoalvarezz.config;

public class Config {
    private final int port;
    private final String address;
    private final int numWorkers;

    public Config(int port, String address, int numWorkers) {
        this.port = port;
        this.address = address;
        this.numWorkers = numWorkers;
    }

    public int getPort() {
        return port;
    }

    public String getAddress() {
        return address;
    }

    public int getNumWorkers() {
        return numWorkers;
    }
}
