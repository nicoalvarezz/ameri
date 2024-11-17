package com.github.nicoalvarezz.config;

public class Config {
    private final int port;
    private final String address;

    public Config(int port, String address) {
        this.port = port;
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public String getAddress() {
        return address;
    }
}
