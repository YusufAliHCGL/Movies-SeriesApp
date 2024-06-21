package com.example.moviesseriesapp.presentation.movie_details.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.moviesseriesapp.presentation.movie_details.MovieDetailsViewModel



@Composable
fun MovieDetailsScreen(paddingValues: PaddingValues,  movieDetailsViewModel: MovieDetailsViewModel = hiltViewModel()) {


        Box(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()) {
            val state = movieDetailsViewModel.state.value
            val isShowInfo = remember {
                mutableStateOf(false)
            }
          
            state.movieDetails?.let {movieDetails ->
            Column {

                Image(painter = rememberAsyncImagePainter(movieDetails.poster), contentDescription = movieDetails.title,
                    modifier = Modifier
                        .size(300.dp, 300.dp)
                        .clip(RoundedCornerShape(40.dp))
                        .align(Alignment.CenterHorizontally)
                        ,
                    contentScale = ContentScale.Crop)
                Text(text = movieDetails.title,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 25.dp, top = 3.dp),
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 40.sp,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.W500,
                    color = Color(102, 0, 204))

                Text(text = movieDetails.director,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(7.dp),
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 30.sp,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.W500,
                    color = Color(102, 0, 204))
                Text(text = movieDetails.actors,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(7.dp),
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 30.sp,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.W500,
                    color = Color(102, 0, 204))
                Text(text = movieDetails.type,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(7.dp),
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 30.sp,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.W500,
                    color = Color(102, 0, 204))
                Text(text = movieDetails.genre,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(7.dp),
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 30.sp,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.W500,
                    color = Color(102, 0, 204))
                Text(text = movieDetails.released,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(7.dp),
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 30.sp,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.W500,
                    color = Color(102, 0, 204))
                Text(text = movieDetails.imdbRating,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(7.dp),
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 30.sp,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.W500,
                    color = Color(102, 0, 204))
            }
                if (isShowInfo.value) {
                    AlertDialog(onDismissRequest = {isShowInfo.value = false}, confirmButton = {}, title = { Text(
                        text = movieDetails.plot
                    )})

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
