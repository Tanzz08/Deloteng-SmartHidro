package com.example.delotengsmarthidro.data.repository

import android.app.Application
import com.example.delotengsmarthidro.data.database.HistoryDao
import com.example.delotengsmarthidro.data.database.HistoryDatabase
import com.example.delotengsmarthidro.data.database.HistoryEntity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainRepository(application: Application) {
    private val executorsService: ExecutorService = Executors.newSingleThreadExecutor()
    private val historyDao: HistoryDao

    init {
        val db = HistoryDatabase.getDatabase((application))
        historyDao = db.historyDao()
    }

    fun insert(history: HistoryEntity){
        executorsService.execute { historyDao.insert(history) }
    }

    fun delete(history: HistoryEntity){
        executorsService.execute { historyDao.delete(history)}
    }
}