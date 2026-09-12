package com.example.mazesolvergame.ui.maze

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.example.mazesolvergame.data.maze.Maze
import com.example.mazesolvergame.data.maze.MazeCell

@Composable
fun MazeView(
    maze: Maze,
    explored: List<MazeCell>,
    solution: List<MazeCell>,
    modifier: Modifier = Modifier
) {

    Canvas(modifier = modifier.fillMaxSize()) {

        val cellW = size.width / maze.cols
        val cellH = size.height / maze.rows

        // -------------------------
        // Draw maze walls
        // -------------------------
        for (r in 0 until maze.rows) {
            for (c in 0 until maze.cols) {

                val cell = maze.grid[r][c]

                val x = c * cellW
                val y = r * cellH

                if (cell.topWall)
                    drawLine(Color.Black, Offset(x, y), Offset(x + cellW, y), 4f)

                if (cell.bottomWall)
                    drawLine(Color.Black, Offset(x, y + cellH), Offset(x + cellW, y + cellH), 4f)

                if (cell.leftWall)
                    drawLine(Color.Black, Offset(x, y), Offset(x, y + cellH), 4f)

                if (cell.rightWall)
                    drawLine(Color.Black, Offset(x + cellW, y), Offset(x + cellW, y + cellH), 4f)
            }
        }

        // -------------------------
        // Exploration (visited cells)
        // -------------------------
        explored.forEach { cell ->

            val center = Offset(
                x = cell.col * cellW + cellW / 2,
                y = cell.row * cellH + cellH / 2
            )

            drawCircle(
                color = Color.LightGray,
                radius = cellW * 0.20f,
                center = center
            )
        }

        // -------------------------
        // Draw path lines
        // -------------------------
        for (i in 0 until solution.size - 1) {

            val a = solution[i]
            val b = solution[i + 1]

            val start = Offset(
                a.col * cellW + cellW / 2,
                a.row * cellH + cellH / 2
            )

            val end = Offset(
                b.col * cellW + cellW / 2,
                b.row * cellH + cellH / 2
            )

            drawLine(
                color = Color.Green,
                start = start,
                end = end,
                strokeWidth = cellW * 0.15f
            )
        }

        // -------------------------
        // Draw circles on path
        // -------------------------
        solution.forEachIndexed { index, cell ->

            val center = Offset(
                cell.col * cellW + cellW / 2,
                cell.row * cellH + cellH / 2
            )

            val color =
                when (index) {
                    0 -> Color.Blue
                    solution.lastIndex -> Color.Red
                    else -> Color.Green
                }

            drawCircle(
                color = color,
                radius = cellW * 0.30f,
                center = center
            )
        }
    }
}