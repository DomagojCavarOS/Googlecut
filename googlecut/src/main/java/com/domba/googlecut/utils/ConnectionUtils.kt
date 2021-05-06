package com.cavar.papercut.utils

import java.net.InetAddress

const val GOOGLE = "google.com"

fun isInternetAvailable(): Boolean {
    return try {
        val ipAddr = InetAddress.getByName(GOOGLE)
        ipAddr.toString() != ""
    } catch (e: Exception) {
        false
    }
}