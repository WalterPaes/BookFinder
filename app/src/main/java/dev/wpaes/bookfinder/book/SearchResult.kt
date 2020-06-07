package dev.wpaes.bookfinder.book

import com.google.gson.annotations.SerializedName

data class SearchResult(
    @SerializedName("totalItems") val totalItems : Int,
    @SerializedName("items") val items : List<Book>
)