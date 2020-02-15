package com.example.pokedex_kotlin_final

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex_kotlin_final.API.APIService
import com.example.pokedex_kotlin_final.API.RetrofitClientInstance
import com.example.pokedex_kotlin_final.Adapter.PokemonListAdapter
import com.example.pokedex_kotlin_final.Model.APIResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var pokemonAdapter: PokemonListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = RetrofitClientInstance.retrofitInstance?.create(APIService::class.java)

        retrofit!!.getAllPokemon().enqueue(object : Callback<APIResponse> {
            override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                d("thabeyrn","check: ${response.body()!!.count}")
                showData(response.body()!!)


            }

            override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                d("thabeyrn","onFailure")


            }

        })


    }

    private fun showData(body: APIResponse) {
        pokemonlist_recyclerview.apply {
            layoutManager = GridLayoutManager(this@MainActivity,3)
            pokemonAdapter = PokemonListAdapter(body.results)
            adapter = pokemonAdapter
        }

    }
}
