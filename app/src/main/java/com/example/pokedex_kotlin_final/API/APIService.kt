package com.example.pokedex_kotlin_final.API

import com.example.pokedex_kotlin_final.Model.APIResponse
import com.example.pokedex_kotlin_final.Model.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface APIService {


    @GET("pokemon?&limit=807")
    fun getAllPokemon(): Call<APIResponse>

    @GET("pokemon/{id}/")
    fun getPokemon(@Path("id") id:String): Call<Pokemon>
}