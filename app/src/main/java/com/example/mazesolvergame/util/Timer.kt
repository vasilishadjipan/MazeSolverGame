package com.example.mazesolvergame.util

object Timer {

    inline fun measure(
        block: () -> Unit
    ): Long {

        val start = System.nanoTime()

        block()

        return System.nanoTime() - start
    }
}