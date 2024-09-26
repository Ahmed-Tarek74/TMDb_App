package com.core.data.remote
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val page: Int,
    @SerializedName(value = "results")
    val movies: List<MovieDto>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
