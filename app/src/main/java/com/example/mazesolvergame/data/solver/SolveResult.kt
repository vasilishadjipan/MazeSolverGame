package com.example.mazesolvergame.data.solver

import com.example.mazesolvergame.data.maze.MazeCell

data class SolveResult(
    val explored: List<MazeCell>, // cells visited during solving
    val path: List<MazeCell>      // final solution path
)