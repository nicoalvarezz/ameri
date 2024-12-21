package com.github.nicoalvarezz

import com.github.nicoalvarezz.core.ConfigurationManager
import com.github.nicoalvarezz.core.MasterWorker

fun main() {
    val masterWorker = MasterWorker(ConfigurationManager())
    masterWorker.start()
}
