package com.example.mazesolvergame.ui.maze

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mazesolvergame.domain.model.AlgorithmType
import com.example.mazesolvergame.domain.usecase.GenerateMazeUseCase
import com.example.mazesolvergame.domain.usecase.SolveMazeUseCase

class MazeViewModelFactory(
    private val size: Int,
    private val algorithm: AlgorithmType,
    private val generateMazeUseCase: GenerateMazeUseCase = GenerateMazeUseCase(),
    private val solveMazeUseCase: SolveMazeUseCase = SolveMazeUseCase()
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MazeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MazeViewModel(size, algorithm, generateMazeUseCase, solveMazeUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}