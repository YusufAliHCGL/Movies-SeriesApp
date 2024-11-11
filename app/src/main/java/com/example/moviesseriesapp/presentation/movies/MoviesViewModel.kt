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
                    _state.value = state.value.copy(
                        error = resource.message ?: "Error",
                        isLoading = false,
                        movies = emptyList()
                        )
                }
                is Resource.Loading -> {
                    _state.value = state.value.copy(
                        isLoading = true,
                        error = "",
                        movies = emptyList()
                    )
                }
                is Resource.Success -> {
                    val list = resource.data?.sortedBy { it.title.lowercase() }
                    _state.value = state.value.copy(
                        movies = list ?: emptyList(),
                        error = "",
                        isLoading = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: MoviesEvent) {
        when(event) {
            is MoviesEvent.Search -> {
                getMovies(event.search)
            }
            is MoviesEvent.ChangeFilter -> {
                _state.value = state.value.copy(filterEvent = event.filterEvent)
            }
            is MoviesEvent.ChangeSearchText -> {
                _state.value = state.value.copy(search = event.text)
            }
        }
    }

}