package com.muhsinyavuz.musicapplication.service


import com.google.gson.GsonBuilder
import com.muhsinyavuz.musicapplication.model.Response
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MusicApiService {

    private val baseUrl  = "https://rss.applemarketingtools.com/"

    private var interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()
    private var gson = GsonBuilder()
        .setLenient()
        .create()
    //okHTTP ekleme .client eklenecek
    private val api = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(baseUrl)
        .build()
        .create(MusicAPI::class.java)

    fun getData() : Single<Response> {
        return api.getMusics()
    }

}