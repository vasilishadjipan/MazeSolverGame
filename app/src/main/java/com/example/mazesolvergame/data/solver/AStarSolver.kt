package com.example.mazesolvergame.data.solver

import com.example.mazesolvergame.data.maze.Maze
import com.example.mazesolvergame.data.maze.MazeCell
import java.util.PriorityQueue
import kotlin.math.abs

class AStarSolver {

    private data class Node(val cell: MazeCell, val g: Int, val h: Int) : Comparable<Node> {
        val f: Int get() = g + h
        override fun compareTo(other: Node) = f.compareTo(other.f)
    }

    fun solve(maze: Maze): SolveResult {
        val start = maze.grid[0][0]
        val end = maze.grid[maze.rows - 1][maze.cols - 1]

        val openSet = PriorityQueue<Node>()
        val cameFrom = mutableMapOf<MazeCell, MazeCell?>()
        val gScore = mutableMapOf<MazeCell, Int>()
        val explored = mutableListOf<MazeCell>()

        openSet.add(Node(start, 0, heuristic(start, end)))
        gScore[start] = 0
        cameFrom[start] = null

        while (openSet.isNotEmpty()) {
            val current = openSet.poll().cell
            explored.add(current)
            if (current == end) break

            for (n in maze.neighbors(current)) {
                val tentativeG = gScore[current]!! + 1
                if (tentativeG < gScore.getOrDefault(n, Int.MAX_VALUE)) {
                    gScore[n] = tentativeG
                    cameFrom[n] = current
                    openSet.add(Node(n, tentativeG, heuristic(n, end)))
                }
            }
        }

        val path = mutableListOf<MazeCell>()
        var cur: MazeCell? = end
        while (cur != null && cameFrom.containsKey(cur)) {
            path.add(cur)
            cur = cameFrom[cur]
        }

        return SolveResult(explored, path.reversed())
    }

    private fun heuristic(a: MazeCell, b: MazeCell) = abs(a.row - b.row) + abs(a.col - b.col)
}