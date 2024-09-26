package com.core.domain.models

data class Movie(
    val id :Long,
    val title: String,
    val description: String,
    val popularity: Double,
    val posterPath: String?,
    private val _releaseDate: String,
    private val _rate: Double,
) {
    // Lazy calculation for rating percentage
    val ratingPercentage: Int by lazy {
        ((_rate / 10) * 100).toInt()
    }
    val releaseDate: String
        get() = _releaseDate.ifEmpty {
            "Release date not available"
        }
    val rate: Float
        get() = (_rate/10).toFloat()
}