package com.vitaliidiadchenko.mykotkinapplication.screens.actorDetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitaliidiadchenko.mykotkinapplication.data.Actor
import com.vitaliidiadchenko.mykotkinapplication.network_module.MovieApi
import com.vitaliidiadchenko.mykotkinapplication.network_module.dto.convertActorDetailDtoToActor
import kotlinx.coroutines.launch
import java.lang.Exception

class ActorDetailViewModel(private val movieApi: MovieApi): ViewModel () {

    private var _actor = MutableLiveData<Actor>()
    val actor: MutableLiveData<Actor> get() = _actor

    fun getActor(actorId: Int){
        viewModelScope.launch {
            try{
                val actorDto = movieApi.getActor(actorId)
                val actor = convertActorDetailDtoToActor(actorDto)
                _actor.postValue(actor)
            } catch (e: Exception) {
                Log.i("Error getting actor", e.message.toString())
            }
        }
    }
}