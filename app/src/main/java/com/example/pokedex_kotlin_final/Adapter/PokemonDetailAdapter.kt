package com.example.pokedex_kotlin_final.Adapter



import android.content.Intent
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.pokedex_kotlin_final.Model.Moves
import com.example.pokedex_kotlin_final.Model.Results
import com.example.pokedex_kotlin_final.R
import com.example.pokedex_kotlin_final.pokemon_detail
import kotlinx.android.synthetic.main.activity_pokemon_detail.view.*
import kotlinx.android.synthetic.main.pokemon_layout.view.*
import kotlinx.android.synthetic.main.pokemon_moves_layout.view.*
import kotlinx.coroutines.withContext

class PokemonDetailAdapter(private val moves_list:List<Moves>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.pokemon_moves_layout,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return moves_list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is MovesViewHolder ->{
                holder.bind(moves_list.get(position))}

        }


    }


}

class MovesViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){
    val pokemon_move = itemView.moves_name
    fun bind(moves: Moves){
        println(moves.move.name)
        pokemon_move.text =  moves.move.name

    }


}