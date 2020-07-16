package com.example.pokemonlist

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonlist.adapter.PokemonListAdapter
import com.example.pokemonlist.model.Pokemon

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Pokemon>?) {
    val adapter = recyclerView.adapter as PokemonListAdapter
    adapter.submitList(data)
}