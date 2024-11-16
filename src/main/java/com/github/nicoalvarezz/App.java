package com.github.nicoalvarezz;

import com.github.nicoalvarezz.config.Config;
import com.github.nicoalvarezz.worker.BaseWorker;
import com.github.nicoalvarezz.worker.MasterWorker;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        BaseWorker master = new MasterWorker(new Config(8080, "127.0.0.1", 2));
        master.start();
    }
}
