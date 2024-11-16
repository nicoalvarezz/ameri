package com.github.nicoalvarezz.worker;

import com.github.nicoalvarezz.config.Config;

import java.io.IOException;

public interface BaseWorker {

    void handleSignal();

    void start();

    void cleanup();
}
