package com.mahmoudreda.bostatask.album_details_flow.data.remote

import com.mahmoudreda.bostatask.album_details_flow.data.model.Photos
import com.mahmoudreda.bostatask.core.DataResult
import com.mahmoudreda.bostatask.core.getDataResult
import javax.inject.Inject

class AlbumDetailsRemoteDataSourceImp @Inject constructor(
    private val albumDetailsService:AlbumDetailsService
):AlbumDetailsRemoteDataSource {

    override suspend fun getPhotos(albumId: Int): DataResult<Photos> {
        return try {
            albumDetailsService.getPhotos(albumId).getDataResult()
        } catch (t: Throwable) {
            DataResult.Error(t)
        }
    }

}