package com.core.presentation.mappers

import com.core.domain.models.Movie
import com.core.presentation.models.MovieUIModel

fun Movie.toUiModel():MovieUIModel = MovieUIModel(
    movieId = this.id,
    title = this.title,
    overview = this.description,
    _posterPath = this.posterPath,
    releaseDate = this.releaseDate,
    rating = this.rate,
    _ratingPercentage = this.ratingPercentage
)