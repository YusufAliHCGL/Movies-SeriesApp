package com.example.moviesseriesapp.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.moviesseriesapp.presentation.Screen
import com.example.moviesseriesapp.util.Constants.IMDB_ID


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(navController: NavController) {
    TopAppBar(title = {
        Text(text = "Movies&Series", fontSize = 26.sp, fontWeight = FontWeight.W500, color = Color(0,0,255))},
        colors = TopAppBarDefaults.topAppBarColors(
        Color.Green
    ), actions = {

        val currentDestinationState by navController.currentBackStackEntryAsState()
        val currentDestination = currentDestinationState?.destination?.route
            if(currentDestination != Screen.MovieDetailsScreen.route+"/{${IMDB_ID}}") {
            Icon(imageVector = Icons.Default.Home, contentDescription = "ListScreen",
                tint = Color.Red, modifier = Modifier
                    .background(
                        if (currentDestination == Screen.MoviesScreen.route) Color.LightGray else Color.Green,
                        shape = CircleShape
                    )
                    .clickable {
                        if (currentDestination != Screen.MoviesScreen.route) {
                            navController.navigate(route = Screen.MoviesScreen.route) {
                                popUpTo(Screen.MoviesScreen.route) { inclusive = false }
                                launchSingleTop = true
                            }
                        }
                    }
                    .padding(6.dp)
            )

            Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorites",
                tint = Color.Red, modifier = Modifier
                    .background(
                        if (currentDestination == Screen.FavoritesScreen.route) Color.LightGray else Color.Green,
                        shape = CircleShape
                    )
                    .clickable {
                        if (currentDestination != Screen.FavoritesScreen.route) {
                            navController.navigate(route = Screen.FavoritesScreen.route) {
                                popUpTo(Screen.FavoritesScreen.route) { inclusive = false }
                                launchSingleTop = true
                            }
                        }
                    }
                    .padding(6.dp)
            )
        }

    })
}

