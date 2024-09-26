package com.core.data.mappers

import com.core.data.entities.MovieEntity
import com.core.data.remote.MovieDto
import com.core.domain.models.Movie

fun MovieDto.toMovieEntity(): MovieEntity {
    // Use `takeIf` to handle `posterPath` transformation more succinctly
    val formattedPosterPath = posterPath
        ?.takeIf { it.isNotEmpty() }
        ?.let { "https://www.themoviedb.org/t/p/w1280$it" }

    return MovieEntity(
        id = id,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = formattedPosterPath,
        releaseDate = releaseDate,
        title = title,
        rating = rating,
        page = page
    )
}

fun List<MovieDto>.toMovieRecords(): List<MovieEntity> {
    val movies = this.map {
        it.toMovieEntity()
    }
    return movies
}

fun MovieEntity.toMovie(): Movie {
    return Movie(
        id = this.id,
        title = this.title,
        description = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        _releaseDate = this.releaseDate,
        _rate = this.rating
    )
}
