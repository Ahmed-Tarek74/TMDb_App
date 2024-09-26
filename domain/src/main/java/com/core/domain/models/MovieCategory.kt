package com.core.domain.models

enum class MovieCategory(val tabTitle: String, val category: String) {
    NOW_PLAYING(tabTitle = "Now Playing", category = "now_playing"),
    POPULAR(tabTitle = "Popular", category = "popular"),
    UPCOMING(tabTitle = "Upcoming", category = "upcoming")
}