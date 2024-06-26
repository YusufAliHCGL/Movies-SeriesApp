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

    fun getFavorites(filterEvent: FilterEvent) {
        getFavoritesFromDatabaseUseCase.executeGetFavoritesFromDatabase().onEach { resource ->
            when(resource) {
                is Resource.Error -> {
                    _state.value = FavoritesState(error = resource.message ?: "Error!")
                }
                is Resource.Loading -> {
                    _state.value = FavoritesState(isLoading = true)
                }
                is Resource.Success -> {
                    resource.data?.let {list ->
                        val returnList = when(filterEvent) {
                            FilterEvent.AllEvent -> {
                                list
                            }
                            FilterEvent.MoviesFilterEvent -> {
                                list.filter {
                                    it.type.lowercase() == "movie"
                                }
                            }
                            FilterEvent.SeriesFilterEvent -> {
                                list.filter {
                                    it.type.lowercase() == "series"
                                }
                            }
                        }
                        _state.value = FavoritesState(movies = returnList ?: emptyList())
                    }
                }
            }

        }.launchIn(viewModelScope)
    }

}