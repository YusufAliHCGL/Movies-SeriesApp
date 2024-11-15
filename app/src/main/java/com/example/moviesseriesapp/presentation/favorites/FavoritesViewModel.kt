package com.example.moviesseriesapp.presentation.favorites

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesseriesapp.domain.use_case.get_favorites_from_database.GetFavoritesFromDatabaseUseCase
import com.example.moviesseriesapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val getFavoritesFromDatabaseUseCase: GetFavoritesFromDatabaseUseCase)
    : ViewModel() {

    private var _state = mutableStateOf(FavoritesState())
    val state: State<FavoritesState>
        get() = _state

    fun onEvent(event: FavoritesEvent) {
        when(event) {
            is FavoritesEvent.ChangeFilter -> {
                _state.value = state.value.copy(filterEvent = event.filterEvent)
            }
            is FavoritesEvent.GetFavorites -> {
                getFavorites()
            }
        }
    }

    private fun getFavorites() {
        getFavoritesFromDatabaseUseCase.executeGetFavoritesFromDatabase().onEach { resource ->
            when(resource) {
                is Resource.Error -> {
                    _state.value = state.value.copy(
                        error = resource.message ?: "Error!",
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
                    _state.value = state.value.copy(
                        movies = resource.data ?: emptyList(),
                        error = "",
                        isLoading = false
                    )
                }
            }

        }.launchIn(viewModelScope)
    }

}