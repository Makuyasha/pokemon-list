package com.example.pokemonlist.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemonlist.model.Pokemon
import com.example.pokemonlist.network.PokemonApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {

    private val _response = MutableLiveData<List<Pokemon>>()
    val response: LiveData<List<Pokemon>>
        get() = _response

    private var viewModelJob = Job()

    private var coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getPokemonList()
    }

    private fun getPokemonList() {
        coroutineScope.launch {
            var getPropertiesDeferred = PokemonApi.retrofitService.getPokemonList()
            try {
                val listResult = getPropertiesDeferred.await()
                _response.value = listResult.results
            } catch (e: Exception) {

            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
