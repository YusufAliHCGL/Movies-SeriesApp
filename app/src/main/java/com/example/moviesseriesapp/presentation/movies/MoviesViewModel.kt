package com.example.moviesseriesapp.presentation.movies
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesseriesapp.domain.use_case.get_movies.GetMoviesUseCase
import com.example.moviesseriesapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val getMoviesUseCase: GetMoviesUseCase)
    : ViewModel() {

        private var _state = mutableStateOf(MoviesState())
        val state: State<MoviesState>
            get() = _state



    init {
        getMovies(_state.value.search)
    }

    private fun getMovies(search: String) {
        getMoviesUseCase.executeGetMovies(search).onEach {resource ->
            when(resource) {
                is Resource.Error -> {
                    _state.value = MoviesState(error = resource.message ?: "Error")
                }
                is Resource.Loading -> {
                    _state.value = MoviesState(isLoading = true)
                }
                is Resource.Success -> {
                    val list = resource.data!!.sortedBy { it.title.lowercase() }
                    _state.value = MoviesState(movies = list ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: MoviesEvent) {
        when(event) {
            is MoviesEvent.Search -> {
                getMovies(event.search)
            }
        }
    }

}