package com.example.pokemonlist.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemonlist.network.PokemonApi
import com.example.pokemonlist.network.PokemonProperty
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

    init {
        getPokemonList()
    }

    private fun getPokemonList() {
        PokemonApi.retrofitService.getPokemonList().enqueue( object : Callback<PokemonProperty> {

            override fun onFailure(call: Call<PokemonProperty>, t: Throwable) {
                _response.value = "Failure: " + t.message
            }

            override fun onResponse(call: Call<PokemonProperty>, response: Response<PokemonProperty>
            ) {
                _response.value = "Success: ${response.body()?.count} Pokemon properties retrieved"
            }
        })
    }
}
