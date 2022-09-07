package com.muhsinyavuz.musicapplication.service

import com.muhsinyavuz.musicapplication.model.Response
import io.reactivex.Single
import retrofit2.http.GET

interface MusicAPI {
    @GET("api/v2/us/music/most-played/50/albums.json")
    fun getMusics(): Single<Response>
}