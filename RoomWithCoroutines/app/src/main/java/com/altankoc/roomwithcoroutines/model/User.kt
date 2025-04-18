package com.altankoc.roomwithcoroutines.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
data class User(
    @ColumnInfo(name = "ad")
    var ad: String,

    @ColumnInfo(name = "yas")
    var yas: Int
)
{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}