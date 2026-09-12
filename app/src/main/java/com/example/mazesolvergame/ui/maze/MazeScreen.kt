package com.example.mazesolvergame.ui.maze

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun MazeScreen(viewModel: MazeViewModel) {
    val maze by viewModel.maze.collectAsState()
    val explored by viewModel.explored.collectAsState()
    val solution by viewModel.solution.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        maze?.let {
            MazeView(it, explored, solution, Modifier.weight(1f))
        }
        Row {
            Button(onClick = { viewModel.generateMaze() }) { Text("New Maze") }
            Button(onClick = { viewModel.solveMaze() }) { Text("Solve") }
        }
    }
}