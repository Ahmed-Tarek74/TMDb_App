package com.core.presentation.views.composeScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.core.domain.models.MovieCategory
import com.core.domain.models.MovieCategory.NOW_PLAYING
import com.core.domain.models.MovieCategory.POPULAR
import com.core.domain.models.MovieCategory.UPCOMING
import com.core.presentation.Intents.MoviesHomeIntent
import com.core.presentation.Intents.MoviesHomeIntent.*
import com.core.presentation.R
import com.core.presentation.models.MovieUIModel
import com.core.presentation.theme.black
import com.core.presentation.theme.light_blue
import com.core.presentation.theme.light_gray
import com.core.presentation.theme.primary_dark_blue
import com.core.presentation.theme.white
import com.core.presentation.views.composables.MoviesListBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesHomeScreen(
    movies: LazyPagingItems<MovieUIModel>,
    setIntent: (MoviesHomeIntent) -> Unit,
    tabIndex: State<Int>,
    onMovieSelected: (Long) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarColors(
                    containerColor = colorScheme.black,
                    scrolledContainerColor = colorScheme.white,
                    navigationIconContentColor = colorScheme.white,
                    titleContentColor = colorScheme.white,
                    actionIconContentColor = colorScheme.white
                ),
                title = {
                    Text(
                        text = stringResource(id = R.string.top_app_bar_title),
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.padding(4.dp)
                    )
                })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
        )
        {
            TabRow(selectedTabIndex = tabIndex.value) {
                MovieCategory.entries.forEachIndexed { index, tab ->
                    Tab(
                        selectedContentColor = colorScheme.primary_dark_blue,
                        unselectedContentColor = colorScheme.light_blue,
                        modifier = Modifier.background(colorScheme.light_gray),
                        text = {
                            Text(
                                tab.tabTitle,
                                style = MaterialTheme.typography.headlineSmall.copy(
                                    color = colorScheme.primary_dark_blue,
                                    fontSize = 16.sp
                                )
                            )
                        },
                        selected = (tabIndex.value) == index,
                        onClick = { setIntent(TabChanged(index)) }
                    )
                }
            }
            MoviesListBox(
                movies = movies,
                onMovieSelected = onMovieSelected
            )
            when (tabIndex.value) {
                0 -> setIntent(LoadMovies(NOW_PLAYING))
                1 -> setIntent(LoadMovies(POPULAR))
                2 -> setIntent(LoadMovies(UPCOMING))
            }
        }

    }

}