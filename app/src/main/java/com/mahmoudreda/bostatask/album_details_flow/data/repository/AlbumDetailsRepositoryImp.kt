package com.mahmoudreda.bostatask.album_details_flow.data.repository

import com.mahmoudreda.bostatask.album_details_flow.data.model.toPhotoUIModel
import com.mahmoudreda.bostatask.album_details_flow.data.remote.AlbumDetailsRemoteDataSource
import com.mahmoudreda.bostatask.album_details_flow.domain.repository.AlbumDetailsRepository
import com.mahmoudreda.bostatask.core.DataResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AlbumDetailsRepositoryImp @Inject constructor(
    private val albumDetailsRemoteDataSource: AlbumDetailsRemoteDataSource
):AlbumDetailsRepository {

    override suspend fun getPhotos(albumId: Int) = withContext(Dispatchers.IO) {
        val dataResult=albumDetailsRemoteDataSource.getPhotos(albumId)
        when(dataResult){
            is DataResult.Success->{
                DataResult.Success(dataResult.data.map { it.toPhotoUIModel() })
            }
            is DataResult.Error->{
                DataResult.Error(throwable = dataResult.throwable)
            }
            is DataResult.Loading->{
                DataResult.Loading
            }
            is DataResult.Idle->{
                DataResult.Idle
            }
        }
    }
}