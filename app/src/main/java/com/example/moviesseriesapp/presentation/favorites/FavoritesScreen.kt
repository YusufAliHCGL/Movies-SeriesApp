package com.example.moviesseriesapp.presentation.favorites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun FavoritesScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Favorites", modifier = Modifier.align(Alignment.Center))
    }
}