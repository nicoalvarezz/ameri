package com.github.nicoalvarezz.core

import java.net.InetSocketAddress
import java.nio.channels.ServerSocketChannel
import java.nio.channels.SocketChannel
import java.util.concurrent.atomic.AtomicBoolean

class MasterWorker(config: ConfigurationManager) {
    private val serverChannel: ServerSocketChannel = ServerSocketChannel.open();
    private val isRunning = AtomicBoolean(true)

    init {
        val proxyConfig = config.getConfig();
        serverChannel.apply {
            bind(InetSocketAddress(proxyConfig.host, proxyConfig.port), 50)
            configureBlocking(true) // Configuring blocking IO since we are using virtual threads
        }
    }

    fun start() {
        println("Starting Master Worker on ${serverChannel.localAddress}")

        while (isRunning.get()) {
            try {
                val clientChannel = serverChannel.accept()
                handleNewConnection(clientChannel)
            } catch (e: Exception) {
                println("Error accepting new connection: ${e.message}")
            }
        }
    }

    fun shutdown() {
        isRunning.set(false);
        serverChannel.close()

        // gracefully shut down active virtual workers
    }

    fun getLocalAddress() = serverChannel.localAddress

    private fun handleNewConnection(clientChannel: SocketChannel) {
        // create a new virtual thread to handle new connection
        println("Connected: ${clientChannel.remoteAddress}")
    }
}