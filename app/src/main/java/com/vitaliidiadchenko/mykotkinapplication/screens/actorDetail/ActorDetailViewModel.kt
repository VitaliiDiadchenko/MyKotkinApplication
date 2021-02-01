package com.vitaliidiadchenko.mykotkinapplication.screens.actorDetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitaliidiadchenko.mykotkinapplication.data.Actor
import com.vitaliidiadchenko.mykotkinapplication.data.ActorDetail
import com.vitaliidiadchenko.mykotkinapplication.data.db.repository.MovieRepositoryImpl
import com.vitaliidiadchenko.mykotkinapplication.network_module.MovieApi
import com.vitaliidiadchenko.mykotkinapplication.network_module.dto.convertActorDetailDtoToActorDetail
import kotlinx.coroutines.launch
import java.lang.Exception

class ActorDetailViewModel(
    private val movieApi: MovieApi,
    private val repository: MovieRepositoryImpl
) : ViewModel() {

    private var _actor = MutableLiveData<ActorDetail>()
    val actorLiveData: LiveData<ActorDetail> get() = _actor

    fun getActorDetail(actorId: Int) {
        viewModelScope.launch {
            loadActorFromDb(actorId)
            loadActorFromNetwork(actorId)
        }
    }

    private suspend fun loadActorFromNetwork(actorId: Int) {
        try {
            val actorDetailDto = movieApi.getActorDetail(actorId)
            val actorDetail = convertActorDetailDtoToActorDetail(actorDetailDto)
            _actor.postValue(actorDetail)
            updateActorDetailIntoDb(actorDetail)
        } catch (e: Exception) {
            Log.i("Error getting actor", e.message.toString())
        }
    }

    private suspend fun updateActorDetailIntoDb(actorDetail: ActorDetail) {
        repository.updateActorDetailIntoDb(actorDetail)
    }

    private suspend fun loadActorFromDb(actorId: Int) {
        try {
            _actor.postValue(repository.getActorDetailById(actorId))
        } catch (e: Exception) {
            Log.i("Error getting actorData", e.message.toString())
        }
    }
}