package com.example.moviesseriesapp.presentation.movie_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesseriesapp.domain.use_case.get_movie_details.GetMovieDetailsUseCase
import com.example.moviesseriesapp.util.Constants.IMDB_ID
import com.example.moviesseriesapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    stateHandle: SavedStateHandle)
    : ViewModel() {

    private var _state = mutableStateOf(MovieDetailsState())
    val state: State<MovieDetailsState>
        get() = _state

    init {
        stateHandle.get<String>(IMDB_ID)?.let {imdbId ->
            getMovieDetails(imdbId = imdbId)
        }
    }

    private fun getMovieDetails(imdbId: String) {
        getMovieDetailsUseCase.executeGetMovieDetails(imdbId).onEach {resource ->
            when(resource) {
                is Resource.Error -> {
                    _state.value = MovieDetailsState(error = resource.message ?: "Error")
                }
                is Resource.Loading -> {
                    _state.value = MovieDetailsState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = MovieDetailsState(movieDetails = resource.data)
                }
            }
        }.launchIn(viewModelScope)
    }

}