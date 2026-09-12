package com.example.mazesolvergame.domain.usecase

import com.example.mazesolvergame.domain.model.AlgorithmType
import com.example.mazesolvergame.domain.model.MazeSize
import com.example.mazesolvergame.data.stats.MazeStat
import com.example.mazesolvergame.data.stats.MazeStatDao

class SaveStatUseCase(
    private val dao: MazeStatDao
) {

    suspend operator fun invoke(
        size: MazeSize,
        algo: AlgorithmType,
        moves: Int,
        runtime: Long
    ) {

        dao.insert(
            MazeStat(
                mazeSize = size.name,
                algorithm = algo.name,
                moves = moves,
                runtime = runtime
            )
        )
    }
}