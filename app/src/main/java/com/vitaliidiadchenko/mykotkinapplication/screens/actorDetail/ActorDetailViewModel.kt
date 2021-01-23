package com.vitaliidiadchenko.mykotkinapplication.screens.actorDetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitaliidiadchenko.mykotkinapplication.data.Actor
import com.vitaliidiadchenko.mykotkinapplication.data.db.Repository
import com.vitaliidiadchenko.mykotkinapplication.network_module.MovieApi
import com.vitaliidiadchenko.mykotkinapplication.network_module.dto.convertActorDetailDtoToActor
import kotlinx.coroutines.launch
import java.lang.Exception

class ActorDetailViewModel(
    private val movieApi: MovieApi,
    private val repository: Repository
) : ViewModel() {

    private var _actor = MutableLiveData<Actor>()
    val actorLiveData: LiveData<Actor> get() = _actor

    fun getActor(actor: Actor, movieId: Int) {
        loadActorFromDb(actor)
        loadActorFromNetwork(actor, movieId)
    }

    private fun loadActorFromNetwork(actor: Actor, movieId: Int) {
        viewModelScope.launch {
            try {
                val actorDto = movieApi.getActor(actor.id)
                _actor.postValue(convertActorDetailDtoToActor(actorDto))
                updateActorIntoDb(actor, movieId)
            } catch (e: Exception) {
                Log.i("Error getting actor", e.message.toString())
            }
        }
    }

    private fun updateActorIntoDb(actor: Actor, movieId: Int) {
        viewModelScope.launch {
            repository.updateActor(actor, movieId)
        }
    }

    private fun loadActorFromDb(actor: Actor) {
        viewModelScope.launch {
            try{
                _actor.postValue(repository.getActorById(actor.id))
            } catch (e: Exception) {
                Log.i("Error getting actorData", e.message.toString())
            }
        }
    }
}