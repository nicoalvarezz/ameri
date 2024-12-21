package com.github.nicoalvarezz.core

class ConfigurationManager {
    data class ProxyConfig(
        val port: Int = 80,
        val host: String = "0.0.0.0"
    )

    private val config: ProxyConfig = ProxyConfig()

    fun getConfig() = config
}