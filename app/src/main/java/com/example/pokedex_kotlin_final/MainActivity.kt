package com.example.pokedex_kotlin_final

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.util.Log.d
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex_kotlin_final.API.APIService
import com.example.pokedex_kotlin_final.API.RetrofitClientInstance
import com.example.pokedex_kotlin_final.Adapter.PokemonListAdapter
import com.example.pokedex_kotlin_final.Model.APIResponse
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.find
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

        swipe_refresh_layout.setOnRefreshListener {
            Handler().postDelayed({
                pokemonAdapter.notifyDataSetChanged()
                swipe_refresh_layout.isRefreshing = false
            }, 1500)
        }


    }

    private fun showData(body: APIResponse) {
        pokemonlist_recyclerview.apply {
            layoutManager = GridLayoutManager(this@MainActivity,3)
            pokemonAdapter = PokemonListAdapter(body.results)
            adapter = pokemonAdapter
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        val item = menu!!.findItem(R.id.action_search)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }


        })

        return super.onCreateOptionsMenu(menu)
    }

}

