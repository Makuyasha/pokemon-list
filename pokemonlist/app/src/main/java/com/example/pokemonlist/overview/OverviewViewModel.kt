package com.example.pokemonlist.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemonlist.network.PokemonApi
import com.example.pokemonlist.network.PokemonProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {

    // The internal MutableLiveData String that stores the most recent response
    private val _response = MutableLiveData<String>()

    // The external immutable LiveData for the response String
    val response: LiveData<String>
        get() = _response

    private var viewModelJob = Job()

    private var coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getPokemonList()
    }

    private fun getPokemonList() {
        coroutineScope.launch {
            var getPropertiesDeferred = PokemonApi.retrofitService.getPokemonList()
            var listResult = getPropertiesDeferred.await()
            try {
                _response.value = "Success: ${listResult.count} Mars properties retrieved"
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
