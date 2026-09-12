package com.example.mazesolvergame.data.stats

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MazeStatDao {

    @Insert
    suspend fun insert(stat: MazeStat)

    @Query("SELECT * FROM MazeStat")
    suspend fun all(): List<MazeStat>

    @Query("""
        SELECT MIN(moves)
        FROM MazeStat
        WHERE mazeSize = :size AND algorithm = :algo
    """)
    suspend fun bestMoves(
        size: String,
        algo: String
    ): Int?

    @Query("""
        SELECT AVG(runtime)
        FROM MazeStat
        WHERE mazeSize = :size AND algorithm = :algo
    """)
    suspend fun avgRuntime(
        size: String,
        algo: String
    ): Double?
}