package com.example.moviesseriesapp.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviesseriesapp.presentation.Screen
import com.example.moviesseriesapp.presentation.favorites.FavoritesViewModel
import com.example.moviesseriesapp.presentation.favorites.view.FavoritesScreen
import com.example.moviesseriesapp.presentation.movie_details.views.MovieDetailsScreen
import com.example.moviesseriesapp.presentation.movies.MoviesViewModel
import com.example.moviesseriesapp.presentation.movies.views.MoviesScreen
import com.example.moviesseriesapp.presentation.ui.theme.MoviesSeriesAppTheme
import com.example.moviesseriesapp.util.Constants.IMDB_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val moviesViewModel: MoviesViewModel = hiltViewModel()
            val favoritesViewModel: FavoritesViewModel = hiltViewModel()
            MoviesSeriesAppTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize(), topBar = { AppBar(navController = navController) }) { innerPadding ->
                    NavHost(navController = navController, startDestination = Screen.MoviesScreen.route) {
                        composable(route = Screen.MoviesScreen.route) {
                                MoviesScreen(paddingValues = innerPadding, navController = navController, moviesViewModel = moviesViewModel)
                        }
                        composable(route = Screen.MovieDetailsScreen.route+"/{${IMDB_ID}}") {
                               MovieDetailsScreen(paddingValues = innerPadding)
                        }
                        composable(route = Screen.FavoritesScreen.route) {
                                FavoritesScreen(paddingValues = innerPadding, navController = navController, favoritesViewModel = favoritesViewModel)
                        }
                    }
                }
            }
        }
    }
}

