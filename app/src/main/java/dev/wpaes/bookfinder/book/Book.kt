package dev.wpaes.bookfinder.book

import com.google.gson.annotations.SerializedName

data class Book(
    @SerializedName("selfLink") val selfLink : String,
    @SerializedName("volumeInfo") val volumeInfo : VolumeInfo
)