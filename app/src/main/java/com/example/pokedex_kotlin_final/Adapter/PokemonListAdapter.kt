package com.example.pokedex_kotlin_final.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.pokedex_kotlin_final.Model.Results
import com.example.pokedex_kotlin_final.R
import com.example.pokedex_kotlin_final.pokemon_detail
import kotlinx.android.synthetic.main.pokemon_layout.view.*

class PokemonListAdapter(private val pokemon_list:List<Results>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PokemonViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.pokemon_layout,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return pokemon_list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is PokemonViewHolder ->{
                holder.bind(pokemon_list.get(position))}

        }
        holder.itemView.setOnClickListener() {
            val context=holder.itemView.context
            val intent = Intent( context, pokemon_detail::class.java)
            intent.putExtra("target_pokemon_id", (position+1).toString())
            println("target pokemon_id"+(position+1).toString())
            context.startActivity(intent)
        }

    }


}

class PokemonViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){
    val pokemon_name = itemView.pokemon_name
    val pokemon_image = itemView.pokemon_image
    fun bind(results: Results){
        var temp = results.url.split('/').get(6) //6 is the index of the pokemon id

        pokemon_name.setText(results.name)
        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Glide.with(itemView.context)
            .applyDefaultRequestOptions(requestOptions).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + temp.toString() + ".png")
            .into(pokemon_image)



    }


}