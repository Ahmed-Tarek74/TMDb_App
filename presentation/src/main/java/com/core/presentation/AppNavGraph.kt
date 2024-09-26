package com.core.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.core.presentation.Route.*
import com.core.presentation.viewModels.MovieDetailsViewModel
import com.core.presentation.viewModels.MoviesViewModel
import com.core.presentation.views.composeScreens.MovieDetailsScreen
import com.core.presentation.views.composeScreens.MoviesHomeScreen

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: Route = MoviesHomeScreen,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<MoviesHomeScreen> {
            val viewModel: MoviesViewModel = hiltViewModel()
            MoviesHomeScreen(
                movies = viewModel.movies.collectAsLazyPagingItems(),
                setIntent = viewModel::setIntent,
                tabIndex = viewModel.tabIndex.collectAsStateWithLifecycle(),
                onMovieSelected = { movieId ->
                    navController.navigate(MovieDetailsScreen(movieId))
                }
            )
        }
        composable<MovieDetailsScreen>
        {
            val viewModel: MovieDetailsViewModel = hiltViewModel()
            MovieDetailsScreen(viewState = viewModel.viewState.collectAsStateWithLifecycle(),
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}

