package com.example.moviesseriesapp.presentation.movie_details.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.moviesseriesapp.domain.model.MovieDetails
import com.example.moviesseriesapp.presentation.movie_details.MovieDetailsViewModel



@Composable
fun MovieDetailsScreen(paddingValues: PaddingValues,  movieDetailsViewModel: MovieDetailsViewModel = hiltViewModel()) {

        val gradient = Brush.verticalGradient(
        colors = listOf(Color.White, Color.Green),
        startY = 0f,
        endY = 2000f
        )
        Box(modifier = Modifier
            .padding(top = paddingValues.calculateTopPadding(), bottom = paddingValues.calculateBottomPadding())
            .fillMaxSize()
            .background(brush = gradient)) {
            val state = movieDetailsViewModel.state.value
            val isShowInfo = remember {
                mutableStateOf(false)
            }
            val scrollState = rememberScrollState()
          
            state.movieDetails?.let {movieDetails ->
            Column(modifier = Modifier.verticalScroll(scrollState)) {

                Image(painter = rememberAsyncImagePainter(movieDetails.poster), contentDescription = movieDetails.title,
                    modifier = Modifier
                        .size(300.dp, 300.dp)
                        .clip(RoundedCornerShape(40.dp))
                        .align(Alignment.CenterHorizontally)
                        .clickable {
                            isShowInfo.value = true
                        },
                    contentScale = ContentScale.Crop)
                Text(text = movieDetails.title,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 25.dp, top = 3.dp),
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 40.sp,
                    fontSize = 40.sp, maxLines = 4,
                    fontWeight = FontWeight.W500,
                    color = Color(102,0,204))

                Text(text = movieDetails.director,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(7.dp),
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 30.sp,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.W500,
                    color = Color(0, 8, 255, 255)
                )
                Text(text = movieDetails.actors,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(7.dp),
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 30.sp,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.W500,
                    maxLines = 2,
                    color = Color(0, 8, 255, 255))
                Text(text = movieDetails.type,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(7.dp),
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 30.sp,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.W500,
                    color = Color(0, 8, 255, 255))
                Text(text = movieDetails.genre,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(7.dp),
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 30.sp,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.W500,
                    color = Color(0, 8, 255, 255))
                Text(text = movieDetails.released,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(7.dp),
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 30.sp,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.W500,
                    color = Color(0, 8, 255, 255))
                Text(text = movieDetails.imdbRating,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(7.dp),
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 30.sp,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.W500,
                    color = Color(0, 8, 255, 255))
            }
                if (isShowInfo.value) {
                    AlertDialog(onDismissRequest = {isShowInfo.value = false}, confirmButton = {}, title = { Text(
                        text = "Extra Details", fontSize = 34.sp, fontWeight = FontWeight.W600, lineHeight = 32.sp, color = Color(
                            255,
                            0,
                            0,
                            255
                        )
                    )},text = {
                              Contents(movieDetails = movieDetails)
                              }
                    , containerColor = Color(245, 245, 245))

                }

            }
            if (state.isLoading) {
                    CircularProgressIndicator(color = Color.Blue, trackColor = Color.Red,
                        modifier = Modifier.align(Alignment.Center))
            }
            if(state.error.isNotEmpty()) {
              Text(text = state.error, modifier = Modifier.align(Alignment.Center))
            }

        }



}

@Composable
fun Contents(movieDetails: MovieDetails) {
    Column {
        Text(
            text = "Plot", fontSize = 26.sp, fontWeight = FontWeight.W500, lineHeight = 26.sp, color = Color(
                26,
                115,
                232,
                255
            ), modifier = Modifier.padding(top = 10.dp, bottom = 5.dp)
        )
        Text(
            text = movieDetails.plot, fontSize = 20.sp, lineHeight = 24.sp, overflow = TextOverflow.Ellipsis, maxLines = 7, color = Color(51,51,51)
        )
        Text(
            text = "Writers", fontSize = 26.sp, fontWeight = FontWeight.W500, lineHeight = 26.sp, color = Color(
                179,
                0,
                255,
                255
            ), modifier = Modifier.padding(top = 10.dp, bottom = 5.dp)
        )
        Text(
            text = movieDetails.writer, fontSize = 20.sp, lineHeight = 24.sp, overflow = TextOverflow.Ellipsis, maxLines = 2, color = Color(51,51,51)
        )

        Text(
            text = "Awards", fontSize = 26.sp, fontWeight = FontWeight.W500, lineHeight = 26.sp, color = Color(
                255,
                98,
                0,
                255
            ), modifier = Modifier.padding(top = 10.dp, bottom = 5.dp)
        )
        Text(
            text = movieDetails.awards, fontSize = 20.sp, lineHeight = 24.sp, overflow = TextOverflow.Ellipsis, maxLines = 3, color = Color(51,51,51)
        )
    }

}