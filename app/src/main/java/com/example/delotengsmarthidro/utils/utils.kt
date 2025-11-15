package com.example.delotengsmarthidro.utils

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.suspendCancellableCoroutine
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

private const val FILENAME_FORMAT = "yyyyMMdd_HHmmss"
private val timeStamp: String = SimpleDateFormat(FILENAME_FORMAT, Locale.US).format(Date())

suspend fun <T> Task<T>.await(): T = suspendCancellableCoroutine { cont ->
    addOnSuccessListener { result ->
        cont.resume(result)
    }
    addOnFailureListener { exception ->
        cont.resumeWithException(exception)
    }
}

fun formDate(dateString: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val outputFormat = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale("id"))
    val date = inputFormat.parse(dateString)
    return outputFormat.format(date!!)
}

fun formatDateInDay(dateString: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val outputFormat = SimpleDateFormat("EEEE", Locale("id")) // Format hanya hari
    val date = inputFormat.parse(dateString)
    val day = outputFormat.format(date!!)
    return day
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        // SQL untuk menambahkan kolom 'detail' dengan nilai default (misalnya string kosong)
        db.execSQL("ALTER TABLE tutorial_table ADD COLUMN detail TEXT")

        // Catatan: Jika Anda menghapus kolom atau mengubah nama kolom,
        // SQL akan lebih rumit (DROP TABLE, CREATE TABLE baru, dan salin data).
    }
}