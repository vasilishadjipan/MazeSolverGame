package com.example.mazesolvergame.ui.stats

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun StatsScreen(
    vm: StatsViewModel = viewModel()
) {

    LaunchedEffect(Unit) {
        vm.load()
    }

    LazyColumn {

        items(vm.stats) {

            Text(
                "${it.algorithm} ${it.mazeSize} moves=${it.moves} runtime=${it.runtime}"
            )
        }
    }
}