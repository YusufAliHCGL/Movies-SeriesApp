package com.example.moviesseriesapp.presentation.movie_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesseriesapp.domain.model.Favorite
import com.example.moviesseriesapp.domain.use_case.delete_from_favorite_database.DeleteFromFavoriteDatabaseUseCase
import com.example.moviesseriesapp.domain.use_case.get_by_id_from_database.GetByIdFromDatabaseUseCase
import com.example.moviesseriesapp.domain.use_case.get_movie_details.GetMovieDetailsUseCase
import com.example.moviesseriesapp.domain.use_case.insert_to_favorite_database.InsertToFavoriteDatabaseUseCase
import com.example.moviesseriesapp.util.Constants.DATA_ERROR
import com.example.moviesseriesapp.util.Constants.IMDB_ID
import com.example.moviesseriesapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
                                                private val insertToFavoriteDatabaseUseCase: InsertToFavoriteDatabaseUseCase,
                                                private val getByIdFromDatabaseUseCase: GetByIdFromDatabaseUseCase,
                                                private val deleteFromFavoriteDatabaseUseCase: DeleteFromFavoriteDatabaseUseCase,
                                                stateHandle: SavedStateHandle)
    : ViewModel() {

    private var _favoriteState = mutableStateOf(FavoriteState())
    val favoriteState: State<FavoriteState>
        get() = _favoriteState

    private var _controlState = mutableStateOf(ControlState())
    val controlState: State<ControlState>
        get() = _controlState

    private var _state = mutableStateOf(MovieDetailsState())
    val state: State<MovieDetailsState>
        get() = _state

    private var favoriteToDelete: Favorite? = null

    init {
        stateHandle.get<String>(IMDB_ID)?.let {imdbId ->
            getMovieDetails(imdbId = imdbId)
            controlDataById(imdbId = imdbId)
        }
    }

    fun deleteFromData() {
            deleteFromFavoriteDatabaseUseCase.executeDeleteFromDatabase(favoriteToDelete!!).onEach { resource ->
                when(resource) {
                    is Resource.Error -> {
                        _favoriteState.value = FavoriteState(error = resource.message ?: "Error")
                    }
                    is Resource.Loading -> {
                        _favoriteState.value = FavoriteState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _controlState.value = ControlState(isThere = false)
                    }

                }
            }.launchIn(viewModelScope)
    }


    private fun controlDataById(imdbId: String) = viewModelScope.launch {
        val resource = getByIdFromDatabaseUseCase.executeGetByIdFromDatabase(imdbId = imdbId)
        when(resource) {
            is Resource.Error -> {
                if (resource.message == DATA_ERROR) {
                    _controlState.value = ControlState(isThere = false)
                }else {
                    _controlState.value = ControlState(error = resource.message ?: "Error")
                }
            }
            is Resource.Loading -> {}
            is Resource.Success -> {
                _controlState.value = ControlState(isThere = true)
                favoriteToDelete = resource.data
            }
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

    fun insertToDatabase() {
        val detail = _state.value.movieDetails!!
        detail.apply {
            val favorite = Favorite(genre, imdbID, imdbRating, poster, title, type, year)
            insertToFavoriteDatabaseUseCase.executeInsertToFavoriteDatabase(favorite).onEach { resource ->
                when(resource) {
                    is Resource.Error -> {
                        _favoriteState.value = FavoriteState(error = resource.message ?: "Error")
                    }
                    is Resource.Loading -> {
                        _favoriteState.value = FavoriteState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _controlState.value = ControlState(isThere = true)
                        favoriteToDelete = favorite
                        favoriteToDelete!!.id = resource.data!!.toInt()
                    }
                }
            }.launchIn(viewModelScope)
        }

    }

}