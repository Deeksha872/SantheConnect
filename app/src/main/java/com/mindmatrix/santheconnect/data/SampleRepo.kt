package com.mindmatrix.santheconnect.data

import androidx.compose.runtime.mutableStateListOf

object SampleRepo {

    val places = listOf(

        Place(
            "1",
            "Mangaluru Fish Market",
            "Markets",
            "Fresh catch every morning",
            4.5f,
            12.9141,
            74.8560
        ),

        Place(
            "2",
            "Kudla Tiffin Center",
            "Food",
            "Authentic coastal breakfast",
            4.7f,
            12.8703,
            74.8806
        ),

        Place(
            "3",
            "Coorg Homestay",
            "Stays",
            "Coffee plantation getaway",
            4.8f,
            12.3375,
            75.8069
        ),

        Place(
            "4",
            "Channapatna Toy Crafts",
            "Crafts",
            "Traditional wooden toys",
            4.6f,
            12.6510,
            77.2068
        ),

        Place(
            "5",
            "Mysuru Devaraja Market",
            "Markets",
            "Flowers, spices & produce",
            4.4f,
            12.3072,
            76.6497
        ),

        Place(
            "6",
            "Udupi Krishna Bhavan",
            "Food",
            "Classic udupi thali",
            4.6f,
            13.3409,
            74.7421
        ),

        Place(
            "7",
            "Mysuru Silk Art Center",
            "Crafts",
            "Traditional Mysuru silk weaving and crafts",
            4.5f,
            12.2958,
            76.6394
        ),

        Place(
            "8",
            "Bidri Handicrafts",
            "Crafts",
            "Famous Karnataka metal handicrafts",
            4.4f,
            17.9104,
            77.5199
        ),

        Place(
            "9",
            "Coastal Shell Crafts",
            "Crafts",
            "Handmade shell jewelry and decor",
            4.3f,
            12.9141,
            74.8560
        ),

        Place(
            "10",
            "Mysuru Palace Stay",
            "Stays",
            "Luxury heritage stay near palace",
            4.7f,
            12.3051,
            76.6551
        ),

        Place(
            "11",
            "Madikeri Coffee Cafe",
            "Food",
            "Coffee and homemade snacks",
            4.5f,
            12.4244,
            75.7382
        ),

        Place(
            "12",
            "Karkala Local Market",
            "Markets",
            "Fresh vegetables and local goods",
            4.2f,
            13.2143,
            74.9923
        )
    )

    val events = listOf(

        SantheEvent(
            "e1",
            "Mangaluru Santhe",
            "Mangaluru",
            "Every Friday"
        ),

        SantheEvent(
            "e2",
            "Madikeri Weekly Santhe",
            "Madikeri",
            "Every Friday"
        ),

        SantheEvent(
            "e3",
            "Karkala Santhe",
            "Karkala",
            "Every Sunday"
        )
    )

    val posts = mutableStateListOf(

        TravelerPost(
            "p1",
            "Akshata",
            "Coorg",
            "Loved the misty mornings and filter coffee!"
        ),

        TravelerPost(
            "p2",
            "Ravi",
            "Udupi",
            "Best ghee roast dosa I ever had."
        ),

        TravelerPost(
            "p3",
            "Meera",
            "Channapatna",
            "Picked up gorgeous handmade toys."
        )
    )
}