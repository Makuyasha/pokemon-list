package com.example.pokemonlist.network

import com.example.pokemonlist.model.Pokemon

data class PokemonProperty (
    val count: Int,
    val results: List<Pokemon>
)
