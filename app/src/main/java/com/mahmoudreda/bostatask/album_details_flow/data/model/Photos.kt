package com.mahmoudreda.bostatask.album_details_flow.data.model

import com.mahmoudreda.bostatask.album_details_flow.domain.model.PhotoUIModel

class Photos : ArrayList<PhotosItem>()


data class PhotosItem(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)

fun PhotosItem.toPhotoUIModel():PhotoUIModel{
    return PhotoUIModel(
        title = title,
        url = url
    )
}