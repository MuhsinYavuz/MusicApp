package com.muhsinyavuz.musicapplication.model

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DetailModel(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "artistId")
    val artistId : String?,
    @ColumnInfo(name = "resultsName")
    val resultsName : String?,
    @ColumnInfo(name = "artworkUrl100")
    val artworkUrl100 : String?

)

