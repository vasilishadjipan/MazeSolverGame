package com.example.mazesolvergame.domain.usecase

import com.example.mazesolvergame.data.maze.Maze
import com.example.mazesolvergame.data.solver.AStarSolver
import com.example.mazesolvergame.data.solver.BfsSolver
import com.example.mazesolvergame.data.solver.DfsSolver
import com.example.mazesolvergame.data.solver.SolveResult
import com.example.mazesolvergame.domain.model.AlgorithmType

class SolveMazeUseCase {

    operator fun invoke(maze: Maze, algorithm: AlgorithmType): SolveResult {
        return when (algorithm) {
            AlgorithmType.BFS -> BfsSolver().solve(maze)
            AlgorithmType.DFS -> DfsSolver().solve(maze)
            AlgorithmType.ASTAR -> AStarSolver().solve(maze)
        }
    }
}