package com.example.delotengsmarthidro.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.delotengsmarthidro.data.list.tutorial.DummyTutorialData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [TutorialEntity::class], version = 1, exportSchema = false)
abstract class TutorDatabase: RoomDatabase() {
    abstract fun tutorDao(): TutorDao

    companion object  {
        @Volatile
        private var INSTANCE: TutorDatabase? = null

        @JvmStatic
        fun getTutorDatabase(context: Context, applicationScope: CoroutineScope): TutorDatabase {
            if (INSTANCE == null) {
                synchronized(TutorDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        TutorDatabase::class.java, "tutor_db")
                        .addCallback(object : Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                INSTANCE?.let { database ->
                                    applicationScope.launch {
                                        val tutorDao = database.tutorDao()
                                        tutorDao.insertAllTutorials( DummyTutorialData.getTutorialSteps())
                                    }
                                }
                            }
                        })
                        .build()
                }
            }
            return INSTANCE as TutorDatabase
        }
    }
}