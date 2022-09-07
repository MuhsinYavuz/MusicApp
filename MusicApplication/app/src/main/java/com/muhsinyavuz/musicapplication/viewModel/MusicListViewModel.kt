package com.muhsinyavuz.musicapplication.viewModel

import android.app.Application
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.muhsinyavuz.musicapplication.model.DetailModel
import com.muhsinyavuz.musicapplication.model.Response
import com.muhsinyavuz.musicapplication.service.MusicApiService
import com.muhsinyavuz.musicapplication.service.MusicDatabase
import com.muhsinyavuz.musicapplication.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// : BaseViewModel(application)
class MusicListViewModel(application: Application) : BaseViewModel(application) {
    private val musicApiService = MusicApiService()
    private val disposable = CompositeDisposable()
     private var customPreferences = CustomSharedPreferences(getApplication())
    private var refreshTime = 0.02 *60 * 1000 * 1000 *1000L
    val musics = MutableLiveData<Response>()
    val detailModels = MutableLiveData<DetailModel>()
    val musicListError = MutableLiveData<Boolean>()
    val musicLoading = MutableLiveData<Boolean>()

    fun refreshData() {
        musicListError.value = false
        musicLoading.value = false

        val updateTime = customPreferences.getTime()
        if(updateTime != null && updateTime != 0L &&  System.nanoTime() - updateTime < refreshTime){
          //  getDataFromSQLite()
        }else{
            getDataFromApi()
        }
        //getDataFromApi()
    }


    private fun getDataFromApi() {
        musicLoading.value = true

        disposable.add(
            musicApiService.getData()
                .subscribeOn(Schedulers.io()) // bu işlemi farklı bir threadde yapması gerekir .
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Response>(), Disposable {
                    override fun onSuccess(t: Response) {
                        musics.value = t
                        musicListError.value = false
                        musicLoading.value = false
                        // storeInSQLite(t)
                    }
                    override fun onError(e: Throwable) {
                        musicListError.value = true
                        musicLoading.value = false
                        Log.e("Item", "${e.message}")
                        e.printStackTrace()
                    }
                }
                ))


    }
/*
    private fun getDataFromSQLite(){
        launch {
         //   val musics = MusicDatabase(getApplication()).musicDao().getAllMusics()
          //  showMusics(musics)
            Toast.makeText(getApplication(),"FROM SQLITE",Toast.LENGTH_LONG).show()
            println("FROM SQLITE ")

        }
    }

 */
    /*
    private fun showMusics(detailModel: DetailModel){
        //musics.value =
        detailModels.value = detailModel
        musicListError.value = false
        musicLoading.value = false
    }*/
/*
    private fun storeInSQLite(detailModel: DetailModel){
        launch {
            val dao = MusicDatabase(getApplication()).musicDao()
            dao.getAllMusics()
            showMusics(detailModel)
        }
        customPreferences.savaTime(System.nanoTime())
    }
*/


}


