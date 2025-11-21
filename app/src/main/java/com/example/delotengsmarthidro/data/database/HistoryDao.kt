package com.example.delotengsmarthidro.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(historyEntity: HistoryEntity)

    @Delete
    fun delete(historyEntity: HistoryEntity)

    @Query("SELECT * from history_table ORDER BY timestamp DESC")
    fun getAllHistory(): LiveData<List<HistoryEntity>>

}