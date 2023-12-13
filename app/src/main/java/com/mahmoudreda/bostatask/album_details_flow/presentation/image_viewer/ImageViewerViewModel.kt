package com.mahmoudreda.bostatask.album_details_flow.presentation.image_viewer

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageViewerViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
):ViewModel(){

    private val imageUrl= savedStateHandle.get<String>("imageUrl")

    fun getImageUrl():String?{
        return imageUrl
    }
}