package com.example.mazesolvergame.ui.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.example.mazesolvergame.domain.model.AlgorithmType
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mazesolvergame.domain.model.MazeSize

@Composable
fun MainMenuScreen(navController: NavController) {

    // -----------------------
    // State for selections
    // -----------------------
    var selectedSize by remember { mutableStateOf(MazeSize.SMALL) }
    var selectedAlgorithm by remember { mutableStateOf(AlgorithmType.BFS) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // -----------------------
        // Maze size selection row
        // -----------------------
        Text("Select Maze Size", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            MazeSize.values().forEach { size ->
                SelectButton(
                    text = size.name,
                    selected = selectedSize == size
                ) {
                    selectedSize = size
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        // -----------------------
        // Algorithm selection row
        // -----------------------
        Text("Select Algorithm", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            AlgorithmType.values().forEach { algo ->
                SelectButton(
                    text = algo.name,
                    selected = selectedAlgorithm == algo
                ) {
                    selectedAlgorithm = algo
                }
            }
        }

        Spacer(Modifier.height(32.dp))

        // -----------------------
        // Start Maze button
        // -----------------------
        Button(
            onClick = {
                navController.navigate(
                    "maze/${selectedSize.size}/${selectedAlgorithm.name}"
                )
            },
            modifier = Modifier.fillMaxWidth(0.5f)
        ) {
            Text("Start Maze", style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable
fun SelectButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) Color(0xFF4CAF50) else Color.Gray
        ),
        modifier = Modifier.padding(4.dp)
    ) {
        Text(text, color = Color.White)
    }
}