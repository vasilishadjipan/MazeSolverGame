package com.example.mazesolvergame.data.solver

import com.example.mazesolvergame.data.maze.Maze

interface MazeSolver {
    val name: String
    fun solve(maze: Maze): SolveResult
}