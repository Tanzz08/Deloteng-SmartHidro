package com.example.delotengsmarthidro.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.delotengsmarthidro.data.repository.MainRepository
import kotlinx.coroutines.CoroutineScope

@Database(
    entities = [HistoryEntity::class],
    version = 4,
    exportSchema = false
) // UBAH VERSION JADI 2
abstract class HistoryDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao

    companion object {
        @Volatile
        private var INSTANCE: HistoryDatabase? = null

        // LOGIKA MIGRASI: Menambahkan kolom baru tanpa menghapus tabel
        private val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE history_table ADD COLUMN confidenceScore REAL")
            }
        }

        @JvmStatic
        fun getDatabase(context: Context, applicationScope: CoroutineScope): HistoryDatabase {
            if (INSTANCE == null) {
                synchronized(MainRepository::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        HistoryDatabase::class.java,
                        "DbHistory"
                    )
                        .addMigrations(MIGRATION_3_4) // GUNAKAN MIGRASI DI SINI
                        .fallbackToDestructiveMigration() // TAMBAHKAN BARIS INI
                        .build()
                }
            }
            return INSTANCE as HistoryDatabase
        }
    }
}