package com.example.mazesolvergame.ui.stats

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mazesolvergame.data.stats.MazeDatabase
import com.example.mazesolvergame.data.stats.MazeStat
import com.example.mazesolvergame.data.stats.MazeStatDao
import kotlinx.coroutines.launch

class StatsViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = MazeDatabase.getDatabase(application).statDao()

    var stats by mutableStateOf<List<MazeStat>>(emptyList())
        private set

    fun load() {
        viewModelScope.launch {
            stats = dao.all()
        }
    }
}