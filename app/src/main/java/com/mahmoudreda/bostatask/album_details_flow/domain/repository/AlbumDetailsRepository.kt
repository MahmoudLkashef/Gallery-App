package com.mahmoudreda.bostatask.album_details_flow.domain.repository

import com.mahmoudreda.bostatask.album_details_flow.domain.model.PhotoUIModel
import com.mahmoudreda.bostatask.core.DataResult

interface AlbumDetailsRepository {
    suspend fun getPhotos(albumId: Int): DataResult<List<PhotoUIModel>>
}