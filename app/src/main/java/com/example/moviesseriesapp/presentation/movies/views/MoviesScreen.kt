package com.example.moviesseriesapp.presentation.movies.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.moviesseriesapp.presentation.Screen
import com.example.moviesseriesapp.presentation.movies.FilterEvent
import com.example.moviesseriesapp.presentation.movies.MoviesEvent
import com.example.moviesseriesapp.presentation.movies.MoviesViewModel
import com.example.moviesseriesapp.util.Constants.EMPTY_SEARCH_STRING


@Composable
fun MoviesScreen(paddingValues: PaddingValues, navController: NavController, moviesViewModel: MoviesViewModel) {
    val state = moviesViewModel.state.value
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues)
        ) {
        Box(modifier = Modifier
            .fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize()) {
                MoviesSearchBar(text = state.search, hint = "Type To Search", onTextChanged = {moviesViewModel.onEvent(MoviesEvent.ChangeSearchText(it))}) {search ->
                    moviesViewModel.onEvent(MoviesEvent.Search(search))
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    FilterChip(selected = state.filterEvent == FilterEvent.MoviesFilterEvent,
                        onClick = {
                            moviesViewModel.onEvent(MoviesEvent.ChangeFilter(
                                if(state.filterEvent == FilterEvent.MoviesFilterEvent) FilterEvent.AllEvent
                                else FilterEvent.MoviesFilterEvent
                            ))
                        },
                        label = { Text(
                            text = "Movies", color = Color.Black, fontSize = 22.sp, textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        ) }, border = BorderStroke(1.dp, Color.Black),
                        modifier = Modifier
                            .weight(1f)
                            .padding(vertical = 2.dp, horizontal = 4.dp),
                        colors = FilterChipDefaults.filterChipColors(Color.White)
                    )
                    FilterChip(selected = state.filterEvent == FilterEvent.SeriesFilterEvent,
                        onClick = {
                            moviesViewModel.onEvent(MoviesEvent.ChangeFilter(
                                if(state.filterEvent == FilterEvent.SeriesFilterEvent) FilterEvent.AllEvent
                                else FilterEvent.SeriesFilterEvent
                            ))
                        },
                        label = { Text(text = "Series", color = Color.Black, fontSize = 22.sp, textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()) },
                        border = BorderStroke(1.dp, Color.Black),
                        modifier = Modifier
                            .weight(1f)
                            .padding(vertical = 2.dp, horizontal = 4.dp),
                        colors = FilterChipDefaults.filterChipColors(Color.White))
                }
                LazyColumn {
                    val list = when(state.filterEvent) {
                        FilterEvent.AllEvent -> {
                            state.movies
                        }
                        FilterEvent.MoviesFilterEvent -> {
                            state.movies.filter {
                                it.type.lowercase() == "movie"
                            }
                        }
                        FilterEvent.SeriesFilterEvent -> {
                            state.movies.filter {
                                it.type.lowercase() == "series"
                            }
                        }
                    }
                    items(list) {movie ->
                        MoviesListRow(movie) {imdbId ->
                            navController.navigate(route = Screen.MovieDetailsScreen.route+"/${imdbId}") {
                                popUpTo(Screen.MoviesScreen.route) {
                                    inclusive = false
                                }
                                launchSingleTop = true
                            }
                        }
                    }
                }
            }
            if(state.isLoading) {
                CircularProgressIndicator(color = Color.Black, trackColor = Color.Yellow,
                    modifier = Modifier.align(Alignment.Center))
            }
            if(state.error.isNotEmpty()) {
                val color: Color = if (state.error == EMPTY_SEARCH_STRING) Color.Blue else Color.Red
                Text(text = state.error, modifier = Modifier.align(Alignment.Center), color = color, fontSize = 36.sp, lineHeight = 38.sp, textAlign = TextAlign.Center
                    , fontWeight = FontWeight.W600)
            }
        }
    }
}

@Composable
fun MoviesSearchBar(text: String, hint: String, onTextChanged: (String) -> Unit, onSearch: (String) -> Unit) {
    Box(modifier = Modifier.padding(10.dp)) {
        var isHintDisplayed by remember {
            mutableStateOf(false)
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(value = text ,onValueChange = {
                onTextChanged(it)
            },  keyboardActions = KeyboardActions(onDone = {
                onSearch(text.trim())
            }),
                singleLine = true,
                maxLines = 1,
                textStyle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.W500),
                modifier = Modifier
                    .weight(1f)
                    .onFocusChanged {
                        isHintDisplayed = text.isEmpty() && it.isFocused != true
                    }
                    .clip(CircleShape))
            IconButton(
                onClick = {
                    onSearch(text.trim())
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Movies or Series"
                )
            }

        }
        if (isHintDisplayed) {
            Text(text = hint, color = Color(157, 166, 164), textAlign = TextAlign.Start, modifier = Modifier
                .align(
                    alignment = Alignment.CenterStart
                )
                .padding(start = 10.dp), fontSize = 18.sp)
        }
    }
}