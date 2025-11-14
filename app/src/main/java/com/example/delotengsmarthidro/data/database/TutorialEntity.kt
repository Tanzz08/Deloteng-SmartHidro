package com.example.delotengsmarthidro.data.database

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tutorial_table")
@Parcelize
data class TutorialEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "desc")
    var desc: String? = null,

    @ColumnInfo(name = "icon")
    @DrawableRes var icon: Int? = null,
) : Parcelable