package com.example.moviesseriesapp.presentation.movies.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import coil.compose.rememberAsyncImagePainter
import com.example.moviesseriesapp.domain.model.Movie

@Composable
fun MoviesListRow(movie: Movie, onClick: (String) -> Unit) {
    val gradient = Brush.horizontalGradient(
        colors = listOf(Color(255, 0, 102), Color(255, 153, 0)),
        startX = 0f,
        endX = 1000f
    )

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(vertical = 5.dp)
        ) {



        Row( modifier = Modifier.fillMaxWidth().padding(4.dp).clickable {
            onClick(movie.imdbID)
        }) {
            Image(painter = rememberAsyncImagePainter(model = movie.poster), contentDescription = movie.title, modifier = Modifier.size(220.dp, 220.dp).clip(
                RoundedCornerShape(40.dp)
            ).border(width = 2.dp, brush = gradient, shape = RoundedCornerShape(40.dp)), contentScale = ContentScale.Crop)
            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                Text(text = movie.title, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(), overflow = TextOverflow.Ellipsis, fontSize = 32.sp, lineHeight = 32.sp, fontWeight = FontWeight.W600)
                Text(text = movie.year, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth(), overflow = TextOverflow.Ellipsis, fontSize = 28.sp, fontWeight = FontWeight.W500)
            }
        }
    }
}