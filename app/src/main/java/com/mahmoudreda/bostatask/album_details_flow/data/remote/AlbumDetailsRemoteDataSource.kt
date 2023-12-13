package com.mahmoudreda.bostatask.album_details_flow.data.remote

import com.mahmoudreda.bostatask.album_details_flow.data.model.Photos
import com.mahmoudreda.bostatask.core.DataResult

interface AlbumDetailsRemoteDataSource {
    suspend fun getPhotos(albumId: Int): DataResult<Photos>
}