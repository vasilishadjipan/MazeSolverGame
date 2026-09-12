package com.example.mazesolvergame.data.maze

data class MazeCell(
    val row: Int,
    val col: Int,
    var visited: Boolean = false,
    var topWall: Boolean = true,
    var bottomWall: Boolean = true,
    var leftWall: Boolean = true,
    var rightWall: Boolean = true
)