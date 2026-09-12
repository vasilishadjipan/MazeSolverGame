package com.example.mazesolvergame.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mazesolvergame.domain.model.AlgorithmType
import com.example.mazesolvergame.domain.model.MazeSize
import com.example.mazesolvergame.ui.maze.MazeScreen
import com.example.mazesolvergame.ui.maze.MazeViewModel
import com.example.mazesolvergame.ui.menu.MainMenuScreen
import com.example.mazesolvergame.ui.maze.MazeViewModelFactory

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "menu") {

        composable("menu") {
            MainMenuScreen(navController = navController)
        }

        composable(
            route = "maze/{size}/{algorithm}",
            arguments = listOf(
                navArgument("size") { type = NavType.IntType },
                navArgument("algorithm") { type = NavType.StringType }
            )
        ) { backStackEntry ->

            val sizeInt = backStackEntry.arguments?.getInt("size") ?: 10
            val mazeSize = MazeSize.entries.find { it.size == sizeInt } ?: MazeSize.SMALL
            val algorithmStr = backStackEntry.arguments?.getString("algorithm") ?: "BFS"
            val algorithm = AlgorithmType.valueOf(algorithmStr)

            val viewModel: MazeViewModel = viewModel(
                factory = MazeViewModelFactory(mazeSize, algorithm)
            )

            MazeScreen(viewModel, navController)
        }
    }
}