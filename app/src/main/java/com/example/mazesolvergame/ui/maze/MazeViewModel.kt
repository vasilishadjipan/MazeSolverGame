package com.example.mazesolvergame.ui.maze

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mazesolvergame.data.maze.Maze
import com.example.mazesolvergame.data.maze.MazeCell
import com.example.mazesolvergame.data.solver.SolveResult
import com.example.mazesolvergame.domain.model.AlgorithmType
import com.example.mazesolvergame.domain.model.MazeSize
import com.example.mazesolvergame.domain.usecase.GenerateMazeUseCase
import com.example.mazesolvergame.domain.usecase.SolveMazeUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MazeViewModel(
    val mazeSize: MazeSize,
    val algorithm: AlgorithmType,
    private val generateMazeUseCase: GenerateMazeUseCase,
    private val solveMazeUseCase: SolveMazeUseCase
) : ViewModel() {

    private val _maze = MutableStateFlow(generateMazeUseCase(mazeSize.size, mazeSize.size))
    val maze: StateFlow<Maze?> = _maze

    private val _explored = MutableStateFlow<List<MazeCell>>(emptyList())
    val explored: StateFlow<List<MazeCell>> = _explored

    private val _solution = MutableStateFlow<List<MazeCell>>(emptyList())
    val solution: StateFlow<List<MazeCell>> = _solution

    private var solveJob: Job? = null

    fun generateMaze() {

        // cancel running solver
        solveJob?.cancel()

        _maze.value = generateMazeUseCase(mazeSize.size, mazeSize.size)
        _explored.value = emptyList()
        _solution.value = emptyList()
    }

    fun solveMaze() {

        // cancel previous solve
        solveJob?.cancel()

        solveJob = viewModelScope.launch {

            _explored.value = emptyList()
            _solution.value = emptyList()

            val result = solveMazeUseCase(_maze.value, algorithm)

            val exp = mutableListOf<MazeCell>()

            for (cell in result.explored) {

                exp.add(cell)
                _explored.value = exp.toList()

                delay(15)
            }

            _solution.value = result.path
        }
    }
}