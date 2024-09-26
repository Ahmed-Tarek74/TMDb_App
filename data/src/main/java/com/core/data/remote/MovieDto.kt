package com.core.data.remote

import com.google.gson.annotations.SerializedName

data class MovieDto(
    val id: Long,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String,
    val title: String,
    @SerializedName("vote_average")
    val rating: Double,
    var page: Int,
)
