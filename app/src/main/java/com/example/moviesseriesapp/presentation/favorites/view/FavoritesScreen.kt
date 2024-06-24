package com.example.moviesseriesapp.presentation.favorites.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.moviesseriesapp.presentation.Screen
import com.example.moviesseriesapp.presentation.favorites.FavoritesViewModel

@Composable
fun FavoritesScreen(paddingValues: PaddingValues, navController: NavController,favoritesViewModel: FavoritesViewModel = hiltViewModel()) {
    val state = favoritesViewModel.state.value
    val refresh = remember {
        System.currentTimeMillis()
    }
    LaunchedEffect(key1 = refresh) {
        favoritesViewModel.getFavorites()
    }
    BoxWithConstraints(modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues)) {
        val gradientBackground = Brush.linearGradient(
            colors = listOf(Color(0,0,0), Color(0, 14,124)),
            start = Offset(0f,0f),
            end = Offset(maxWidth.value, maxHeight.value)
        )

        Box(modifier = Modifier
            .fillMaxSize()
            .background(brush = gradientBackground)) {
            if (state.movies.isNotEmpty()) {
                LazyColumn {
                    items(state.movies) {favorite ->
                        FavoritesListRow(favorite = favorite, onClick = {imdbId ->
                            navController.navigate(Screen.MovieDetailsScreen.route+"/${imdbId}") {
                                popUpTo(Screen.FavoritesScreen.route) {
                                    inclusive = false
                                }
                                launchSingleTop = true
                            }
                        })
                    }
                }
            }
            if (state.error.isNotEmpty()) {
                Text(text = state.error, modifier = Modifier.align(Alignment.Center), color = Color.Red, fontSize = 36.sp, lineHeight = 38.sp, textAlign = TextAlign.Center
                    , fontWeight = FontWeight.W600)
            }
            if (state.isLoading) {
                CircularProgressIndicator(color = Color.Black, trackColor = Color.Yellow, modifier = Modifier.align(
                    Alignment.Center))
            }
        }

    }
}