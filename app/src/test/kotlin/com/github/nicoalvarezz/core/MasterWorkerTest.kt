package com.github.nicoalvarezz.core

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.net.InetSocketAddress
import java.net.Socket
import kotlin.test.assertTrue

class MasterWorkerTest {
    private lateinit var configManager: ConfigurationManager
    private lateinit var masterWorker: MasterWorker

    @BeforeEach
    fun setUp() {
        configManager = ConfigurationManager()
        masterWorker = MasterWorker(configManager)
    }

    @AfterEach
    fun cleanUp() {
        masterWorker.shutdown()
    }

    @Test
    fun `test server starts and accepts connections`() {
        val serverThread = Thread {
            masterWorker.start()
        }
        serverThread.start()

        // wait for server to start
        Thread.sleep(1000)

        // Get the actual bound port
        val serverAddress = masterWorker.getLocalAddress() as InetSocketAddress
        val clientSocket = Socket()
        clientSocket.connect(InetSocketAddress("localhost", serverAddress.port))

        assertTrue(clientSocket.isConnected)
        clientSocket.close()
    }
}