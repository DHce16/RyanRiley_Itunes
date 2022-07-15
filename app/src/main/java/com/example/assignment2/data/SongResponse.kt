package com.example.assignment2.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class SongResponse(
    val resultCount: Int,
    val results: List<RandomSong>
)

@Parcelize
data class RandomSong(
    val artistName: String,
    val collectionName: String,
    val artworkUrl100: String,
    val trackPrice: Double,
    val trackName: String,
    val currency: String,
    val previewUrl: String
): Parcelable {
    fun getPrice() = "$trackPrice $currency"
}
