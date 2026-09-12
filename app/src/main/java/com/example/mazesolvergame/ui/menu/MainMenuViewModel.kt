package com.example.mazesolvergame.ui.menu

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mazesolvergame.domain.model.AlgorithmType
import com.example.mazesolvergame.domain.model.MazeSize

class MainMenuViewModel : ViewModel() {

    var selectedSize by mutableStateOf(MazeSize.MEDIUM)

    var selectedAlgorithm by mutableStateOf(
        AlgorithmType.BFS
    )
}