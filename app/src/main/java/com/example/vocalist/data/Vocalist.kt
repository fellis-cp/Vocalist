package com.example.vocalist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Vocalist(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "fullname")
    val fullname: String,

    @ColumnInfo(name = "picture")
    val picture: Int,

    @ColumnInfo(name = "band")
    val band: String,

    @ColumnInfo(name = "birthdate")
    val birthdate: String,

    @ColumnInfo(name = "genre")
    val genre: String,

    @ColumnInfo(name = "description")
    val description: String
)