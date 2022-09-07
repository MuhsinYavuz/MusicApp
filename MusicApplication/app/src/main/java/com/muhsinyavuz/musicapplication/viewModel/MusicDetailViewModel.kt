package com.muhsinyavuz.musicapplication.viewModel

import android.app.Application
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.muhsinyavuz.musicapplication.model.DetailModel
import com.muhsinyavuz.musicapplication.model.Response
import com.muhsinyavuz.musicapplication.service.MusicDatabase
import kotlinx.coroutines.launch
import java.util.*

class MusicDetailViewModel(application: Application) : BaseViewModel(application) {

    val musicLiveData = MutableLiveData<DetailModel>()
    fun getDataFromRoom(uid: Int) {
        //  val movie = Responce()
        // movieLiveData.value = movie
        launch {
            val dao = MusicDatabase(getApplication()).musicDao()
            //val music = dao.getMusic(uid)
          //  musicLiveData.value = music
            println("launchhh")

        }

    }
    private fun getDataIntent(){

    }
}



