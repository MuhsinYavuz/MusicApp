package com.muhsinyavuz.musicapplication.service

import androidx.room.Dao
import androidx.room.Query
import com.muhsinyavuz.musicapplication.model.DetailModel
import com.muhsinyavuz.musicapplication.model.Response


@Dao
interface MusicDao {
    /*
    @Query("SELECT * FROM detailmodel  ")
    suspend fun getAllMusics(): DetailModel

    @Query("SELECT * FROM detailmodel  WHERE uid = :musicId")
    suspend fun getMusic(musicId : Int) :  DetailModel
*/
}




