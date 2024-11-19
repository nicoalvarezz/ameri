package com.github.nicoalvarezz;

import com.github.nicoalvarezz.core.MasterWorker;
import com.github.nicoalvarezz.config.Config;

public class App {
    public static void main(String[] args) {
        MasterWorker masterWorker = new MasterWorker(new Config(8080, "127.0.0.1"));
        masterWorker.run();
    }
}
