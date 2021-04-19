package com.beehive.beerrate.model

data class Brewer(
    val objectId: String,
    val id: String,
    val name: String,
    val city: String,
    val country: Country
)