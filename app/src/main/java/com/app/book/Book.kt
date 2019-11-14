package com.app.book

data class Book(
    val isbn: String,
    val title: String,
    val price: String,
    val cover: String,
    val synopsis: List<String>
) {
    val priceText: String
        get() = "$price €"
}
