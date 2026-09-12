package com.example.mazesolvergame.data.solver

import com.example.mazesolvergame.data.maze.Maze
import com.example.mazesolvergame.data.maze.MazeCell

class BfsSolver {
    fun solve(maze: Maze): SolveResult {
        val start = maze.grid[0][0]
        val end = maze.grid[maze.rows - 1][maze.cols - 1]

        val queue = ArrayDeque<MazeCell>()
        val cameFrom = mutableMapOf<MazeCell, MazeCell?>()
        val explored = mutableListOf<MazeCell>()

        queue.add(start)
        cameFrom[start] = null

        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            explored.add(current)
            if (current == end) break

            for (n in maze.neighbors(current)) {
                if (!cameFrom.containsKey(n)) {
                    queue.add(n)
                    cameFrom[n] = current
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
}