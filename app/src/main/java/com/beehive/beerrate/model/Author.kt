package com.beehive.beerrate.model

data class Author(
    val id: String,
    val username: String,
    val reviewCount: Int,
    val city: String,
    val Country: Country
)