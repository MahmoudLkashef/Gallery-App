package com.mahmoudreda.bostatask.album_details_flow.presentation.album_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahmoudreda.bostatask.album_details_flow.domain.model.PhotoUIModel
import com.mahmoudreda.bostatask.album_details_flow.domain.usecase.GetPhotosUseCase
import com.mahmoudreda.bostatask.core.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumDetailsViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _albumsState = mutableStateOf<DataResult<List<PhotoUIModel>>>(DataResult.Idle)
    val albumsState: State<DataResult<List<PhotoUIModel>>> = _albumsState

    private val albumId = savedStateHandle.get<String>("albumId")
    private val albumTitle = savedStateHandle.get<String>("albumTitle")

    init {
        getAlbumPhotos()
    }

    fun getAlbumPhotos() {
        viewModelScope.launch {
            _albumsState.value = DataResult.Loading
            albumId?.let { id -> _albumsState.value = getPhotosUseCase(id.toInt()) }
        }
    }

    fun getAlbumTitle(): String? {
        return albumTitle
    }

    fun getFilteredList(query: String, albumPhotosList: List<PhotoUIModel>): List<PhotoUIModel> {
        return if (query.isNotBlank()) {
            albumPhotosList.filter { photoUIModel ->
                photoUIModel.title.contains(query, true)
            }
        } else {
            albumPhotosList
        }
    }
}