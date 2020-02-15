package com.example.pokedex_kotlin_final.Model


data class APiResponse(
    val name : String,
    val url: String
)

data class pokemon(
    val name: String,
    val id : Int,
    val height: Int,
    val weight: Int,
    val base_experience: Int,
    val types : List<Types>,
    val moves: List<Moves>,
    val sprites: List<Sprites>

)
data class Types(
    val type : List<Typename>
)
data class Typename(
    val type_name: String
)

data class Moves(
    val move: List<Movename>
)
data class Movename(
    val move_name: String
)

data class Sprites(
    val front_default : String,
    val front_shiny : String
)