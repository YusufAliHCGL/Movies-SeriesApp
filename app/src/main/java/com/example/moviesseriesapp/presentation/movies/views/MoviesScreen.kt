package com.example.moviesseriesapp.presentation.movies.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.moviesseriesapp.presentation.Screen
import com.example.moviesseriesapp.presentation.movies.MoviesViewModel


@Composable
fun MoviesScreen(paddingValues: PaddingValues, navController: NavController, moviesViewModel: MoviesViewModel = hiltViewModel()) {
    val state = moviesViewModel.state.value
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues)) {
        Column {
            MoviesSearchBar(hint = "Batman") {search ->
                moviesViewModel.onEvent(MoviesEvent.Search(search))
            }
            Spacer(modifier = Modifier.padding(5.dp))
            LazyColumn {
                items(state.movies) {movie ->
                    MoviesListRow(movie) {imdbId ->
                        navController.navigate(route = Screen.MovieDetailsScreen.route+"/${imdbId}")
                    }
                }
            }
        }

        if(state.isLoading) {
            CircularProgressIndicator(color = Color.Blue, trackColor = Color.Red,
                modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun MoviesSearchBar(hint: String, onSearch: (String) -> Unit) {
    Box(modifier = Modifier.padding(10.dp)) {
        var text by remember {
            mutableStateOf("")
        }
        var isHintDisplayed by remember {
            mutableStateOf(hint.isNotEmpty())
        }
        TextField(value = text ,onValueChange = {
            text = it
        },  keyboardActions = KeyboardActions(onDone = {
            onSearch(text)
        }),
            singleLine = true,
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    isHintDisplayed = text.isEmpty() && it.isFocused != true
                }.clip(CircleShape))
        if (isHintDisplayed) {
            Text(text = hint, color = Color(157, 166, 164), textAlign = TextAlign.Start, modifier = Modifier.align(
                alignment = Alignment.CenterStart).padding(start = 10.dp))
        }
    }
}