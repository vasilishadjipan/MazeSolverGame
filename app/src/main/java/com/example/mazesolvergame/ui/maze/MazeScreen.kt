package com.example.mazesolvergame.ui.maze

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MazeScreen(viewModel: MazeViewModel, navController: NavController) {
    val maze by viewModel.maze.collectAsState()
    val explored by viewModel.explored.collectAsState()
    val solution by viewModel.solution.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Maze") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text("Size: ${viewModel.mazeSize.text}")
            Text("Solver Algorithm: ${viewModel.algorithm.text}")
            Spacer(Modifier.height(16.dp))
            maze?.let {
                MazeView(it, explored, solution, Modifier.weight(1f))
            }
            Spacer(Modifier.height(16.dp))
            Row(
                Modifier.align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Button(onClick = { viewModel.generateMaze() }) { Text("New Maze") }
                Button(onClick = { viewModel.solveMaze() }) { Text("Solve") }
            }
        }
    }
}