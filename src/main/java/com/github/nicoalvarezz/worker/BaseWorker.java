package com.github.nicoalvarezz.worker;

public interface BaseWorker {

    void run();

    void handleSignal();
}
