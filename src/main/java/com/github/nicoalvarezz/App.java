package com.github.nicoalvarezz;

import com.github.nicoalvarezz.worker.MasterWorker;
import com.github.nicoalvarezz.config.Config;

public class App {
    public static void main(String[] args) {
        MasterWorker masterWorker = new MasterWorker(new Config(8080, "192.168.68.103"));
        masterWorker.run();
    }
}
