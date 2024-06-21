package com.example.moviesseriesapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviesseriesapp.presentation.movie_details.views.MovieDetailsScreen
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
            MoviesSeriesAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen.MoviesScreen.route) {
                        composable(route = Screen.MoviesScreen.route) {
                                MoviesScreen(paddingValues = innerPadding, navController = navController)
                        }
                        composable(route = Screen.MovieDetailsScreen.route+"/{${IMDB_ID}}") {
                               MovieDetailsScreen(paddingValues = innerPadding)
                        }
                    }
                }
            }
        }
    }
}

