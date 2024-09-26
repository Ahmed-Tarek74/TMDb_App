package com.core.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApiService {
    companion object {
        const val MOVIES_ENDPOINT = "movie/{movie_category}"
    }

    @GET(MOVIES_ENDPOINT)
    suspend fun getMoviesByCategory(
        @Path("movie_category") movieCategory: String,
        @Query("page") page: Int,
    ): MovieResponse
}