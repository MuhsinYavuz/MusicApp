package com.muhsinyavuz.musicapplication.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.muhsinyavuz.musicapplication.model.DetailModel
import com.muhsinyavuz.musicapplication.model.Response


@Database(entities = arrayOf(DetailModel::class), version = 1)
abstract class MusicDatabase : RoomDatabase() {
 abstract fun musicDao() : MusicDao

 // singleton yok ise olustur var ise olanı kullan
 companion object{
     @Volatile private var instance : MusicDatabase? = null // volatile farklı threadlere görünür hale getirir .

     private val lock = Any()
     operator  fun invoke(context: Context) = instance ?: synchronized(lock){//instansce var mı yokmu kontrol edecek
         // synchronized kullarısak 2 farklı thread aynı anda geleme  farklı zamanlarda gelebilir
        instance ?: makeDatabase(context).also {
            instance = it
        }
     }
        private fun makeDatabase(context : Context) = Room.databaseBuilder(
            context.applicationContext,MusicDatabase::class.java,"musicdatabase"
        ).build()
 }

}