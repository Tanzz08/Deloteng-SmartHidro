package com.example.delotengsmarthidro.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.delotengsmarthidro.data.repository.MainRepository
import kotlinx.coroutines.CoroutineScope

@Database(entities = [HistoryEntity::class], version = 1, exportSchema = false)
abstract class HistoryDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao

    companion object {
        @Volatile
        private var INSTANCE: HistoryDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context, applicationScope: CoroutineScope): HistoryDatabase {
            if (INSTANCE == null){
                synchronized(MainRepository::class.java){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        HistoryDatabase::class.java, "DbHistory")
                        .build()
                }
            }
            return INSTANCE as HistoryDatabase
        }
    }
}