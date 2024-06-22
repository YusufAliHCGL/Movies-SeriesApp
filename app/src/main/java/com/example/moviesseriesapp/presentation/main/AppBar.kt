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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moviesseriesapp.presentation.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(navController: NavController) {
    TopAppBar(title = {
        Text(text = "Movies&Series")},
        colors = TopAppBarDefaults.topAppBarColors(
        Color.Green
    ), actions = {
        val screen = remember {
            mutableStateOf(Screen.MoviesScreen.route)
        }
        Icon(imageVector = Icons.Default.Home, contentDescription = "ListScreen",
                tint = Color.Red, modifier = Modifier.
                background(
                    if (screen.value == Screen.MoviesScreen.route) Color.LightGray else Color.Green, shape = CircleShape )
                .clickable {
                    if (screen.value != Screen.MoviesScreen.route) {
                        navController.navigate(route = Screen.MoviesScreen.route)
                        screen.value = Screen.MoviesScreen.route
                    }
                }.padding(6.dp))

        Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorites",
                tint = Color.Red, modifier = Modifier
               .background(
                    if (screen.value == Screen.FavoritesScreen.route) Color.LightGray else Color.Green, shape = CircleShape
               ).clickable {
                   if (screen.value != Screen.FavoritesScreen.route) {
                       navController.navigate(route = Screen.FavoritesScreen.route)
                       screen.value = Screen.FavoritesScreen.route
                   }
               }.padding(6.dp))


    })
}

