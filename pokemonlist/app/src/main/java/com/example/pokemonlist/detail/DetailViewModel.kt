package com.example.pokemonlist.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.pokemonlist.detail.DetailFragment
import com.example.pokemonlist.network.PokemonApi
import com.example.pokemonlist.network.PokemonProperty

/**
 * The [ViewModel] that is associated with the [DetailFragment].
 */
class DetailViewModel(@Suppress("UNUSED_PARAMETER")pokemonProperty: PokemonProperty, app: Application) : AndroidViewModel(app) {

}
