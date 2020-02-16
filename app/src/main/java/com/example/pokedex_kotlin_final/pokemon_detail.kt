package com.example.pokedex_kotlin_final

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.pokedex_kotlin_final.API.APIService
import com.example.pokedex_kotlin_final.API.RetrofitClientInstance
import com.example.pokedex_kotlin_final.Adapter.PokemonDetailAdapter
import com.example.pokedex_kotlin_final.Model.Pokemon
import com.example.pokedex_kotlin_final.Model.PokemonInfo
import kotlinx.android.synthetic.main.activity_pokemon_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class pokemon_detail : AppCompatActivity() {

    private lateinit var pokemonDetail: PokemonDetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)
        //shiny_switch.isChecked = false
        val id = intent.getStringExtra("target_pokemon_id")
        val retrofit = RetrofitClientInstance.retrofitInstance?.create(APIService::class.java)
        retrofit!!.getPokemon(id).enqueue(object : Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                println("check: "+ response.body()!!.base_experience.toString())
                d("thabeyrn","check: ${response.body()!!.base_experience}")
                shiny_switch.setOnCheckedChangeListener{_, isChecked ->
                    if(isChecked){
                        Glide.with(this@pokemon_detail)
                            .load(response.body()!!.sprites.front_shiny )
                            .into(pokemon_get_image)

                    }
                    else{

                        Glide.with(this@pokemon_detail)
                            .load(response.body()!!.sprites.front_default )
                            .into(pokemon_get_image)


                    }
                }
                showData(response.body()!!)

            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                t.printStackTrace()
                d("thabeyrn","onFailure")

            }

        })




    }


    private fun showData(body: Pokemon) {
        recyclerview_moves.apply {
            layoutManager = LinearLayoutManager(this@pokemon_detail)
            pokemonDetail = PokemonDetailAdapter(body.moves)
            adapter = pokemonDetail
        }


        pokemon_get_base_exp.text = body.base_experience.toString()
        val id = "#"+body.id.toString()
        val id_index = body.id.toString()
        pokemon_id.text=id
        pokemon_name.text = body.name
        pokemon_get_height.text=body.height.toString()
        pokemon_get_weight.text=body.weight.toString()
        pokemon_get_speed.text = body.stats[0].base_stat.toString()
        pokemon_get_special_defense.text = body.stats[1].base_stat.toString()
        pokemon_get_special_attack.text = body.stats[2].base_stat.toString()
        pokemon_get_defense.text = body.stats[3].base_stat.toString()
        pokemon_get_attack.text = body.stats[4].base_stat.toString()
        pokemon_get_hp.text = body.stats[5].base_stat.toString()

        val retrofit = RetrofitClientInstance.retrofitInstance?.create(APIService::class.java)
        retrofit!!.getPokemonInfo(id_index).enqueue(object : Callback<PokemonInfo> {
            override fun onResponse(call: Call<PokemonInfo>, response: Response<PokemonInfo>) {
                val language_size = response.body()!!.flavor_text_entries.size
                for (i in 0..language_size){
                    if(response.body()!!.flavor_text_entries[i].language.name == "en"){
                        pokemon_get_description.text = response.body()!!.flavor_text_entries[i].flavor_text
                        break
                    }
                }

            }

            override fun onFailure(call: Call<PokemonInfo>, t: Throwable) {
                t.printStackTrace()
               

            }

        })



        Glide.with(this)
           .load(body.sprites.front_default )
            .into(pokemon_get_image)


        if(body.types.size !=2) {
            pokemon_get_type1.text = body.types[0].type.name
            pokemon_get_type2.text = " "
        }
        else{
            pokemon_get_type1.text = body.types[0].type.name
            pokemon_get_type2.text = body.types[1].type.name

        }




    }
}
