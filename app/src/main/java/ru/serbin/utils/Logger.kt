package ru.serbin.utils

import android.util.Log

class Logger(private val tag: String) {

    fun d(message: String) {
        Log.d(tag, message)
    }
}