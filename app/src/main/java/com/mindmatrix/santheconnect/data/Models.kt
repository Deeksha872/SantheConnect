package com.mindmatrix.santheconnect.data

data class Place(
    val id: String,
    val name: String,
    val category: String,
    val description: String,
    val rating: Float,
    val lat: Double,
    val lng: Double
)

data class SantheEvent(
    val id: String,
    val name: String,
    val place: String,
    val date: String
)

data class TravelerPost(
    val id: String,
    val author: String,
    val place: String,
    val message: String
)
