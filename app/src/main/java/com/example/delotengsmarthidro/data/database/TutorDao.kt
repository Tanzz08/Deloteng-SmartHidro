package com.example.delotengsmarthidro.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TutorDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllTutorials(tutorialSteps: List<TutorialEntity>)

    @Query("SELECT * FROM tutorial_table")
    fun getAllTutorial(): LiveData<List<TutorialEntity>>
}