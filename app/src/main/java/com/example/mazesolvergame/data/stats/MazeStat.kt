package com.example.mazesolvergame.data.stats

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MazeStat(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val mazeSize: String,

    val algorithm: String,

    val moves: Int,

    val runtime: Long
)