package com.example.delotengsmarthidro.data.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "history_table")
@Parcelize
data class HistoryEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "Label")
    var label: String? = null,

    @ColumnInfo(name = "imageUri")
    var imageUri: String? =  null,

    @ColumnInfo(name = "timestamp")
    var timestamp: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "treatment")
    var treatment: String? = null,

    @ColumnInfo(name = "ciriCiri")
    var ciriCiri: String? = null,
) : Parcelable