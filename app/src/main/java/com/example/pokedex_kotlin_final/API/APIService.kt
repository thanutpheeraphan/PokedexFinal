package com.example.pokedex_kotlin_final.API

import com.example.pokedex_kotlin_final.Model.APiResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {


    @GET("public/coins")
    fun getAllCoins(): Call<APiResponse>
}