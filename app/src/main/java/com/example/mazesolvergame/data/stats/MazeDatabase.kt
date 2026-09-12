package com.example.mazesolvergame.data.stats

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [MazeStat::class],
    version = 1,
    exportSchema = false
)
abstract class MazeDatabase : RoomDatabase() {

    abstract fun statDao(): MazeStatDao

    companion object {
        @Volatile
        private var INSTANCE: MazeDatabase? = null

        fun getDatabase(context: Context): MazeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MazeDatabase::class.java,
                    "maze_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}