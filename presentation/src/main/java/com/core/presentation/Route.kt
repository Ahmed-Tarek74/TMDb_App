package com.core.presentation

import kotlinx.serialization.Serializable

sealed class Route {

    @Serializable
    data object MoviesHomeScreen : Route()

    @Serializable
    data class MovieDetailsScreen(val movieId: Long) : Route()
}