package com.example.mazesolvergame.domain.usecase

import com.example.mazesolvergame.data.maze.Maze
import com.example.mazesolvergame.data.maze.MazeCell

class GenerateMazeUseCase {

    operator fun invoke(rows: Int, cols: Int): Maze {
        val grid = List(rows) { r ->
            List(cols) { c -> MazeCell(r, c) }
        }

        val maze = Maze(rows, cols, grid)
        generateMazeRecursive(maze, 0, 0)
        return maze
    }

    private fun generateMazeRecursive(maze: Maze, row: Int, col: Int) {
        val cell = maze.grid[row][col]
        cell.visited = true

        val directions = listOf("TOP", "BOTTOM", "LEFT", "RIGHT").shuffled()
        for (dir in directions) {
            val (nr, nc) = when (dir) {
                "TOP" -> row - 1 to col
                "BOTTOM" -> row + 1 to col
                "LEFT" -> row to col - 1
                "RIGHT" -> row to col + 1
                else -> row to col
            }

            if (nr in 0 until maze.rows && nc in 0 until maze.cols) {
                val next = maze.grid[nr][nc]
                if (!next.visited) {
                    when (dir) {
                        "TOP" -> { cell.topWall = false; next.bottomWall = false }
                        "BOTTOM" -> { cell.bottomWall = false; next.topWall = false }
                        "LEFT" -> { cell.leftWall = false; next.rightWall = false }
                        "RIGHT" -> { cell.rightWall = false; next.leftWall = false }
                    }
                    generateMazeRecursive(maze, nr, nc)
                }
            }
        }
    }
}