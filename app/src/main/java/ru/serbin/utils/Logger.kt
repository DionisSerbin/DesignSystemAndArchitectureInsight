package ru.serbin.utils

import android.util.Log

class Logger(private val tag: String) {

    private companion object {
        private const val prefix = "SERBIN "
    }
    fun d(message: String) {
        Log.d(prefix + tag, message)
    }
}