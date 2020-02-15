package com.example.pokedex_kotlin_final.Model


data class APIResponse(
    val count: Int,
    val results: List<Results>

)

data class Results(
    val name: String,
    val url: String
)


data class Pokemon(
    val name: String,
    val id : Int,
    val height: Int,
    val weight: Int,
    val base_experience: Int,
    val types : List<Types>,
    val moves: List<Moves>,
    val sprites: Sprites

)
data class Types(
    val type : Type_name
)

data class Type_name(
    val name: String
)

data class Moves(
   val move: Move
)

data class Move(
    val name: String
)

data class Sprites(
    val front_default : String,
    val front_shiny : String
)