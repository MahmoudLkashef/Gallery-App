package com.mahmoudreda.bostatask.album_details_flow.domain.usecase

import com.mahmoudreda.bostatask.album_details_flow.domain.repository.AlbumDetailsRepository

class GetPhotosUseCase(private val repository: AlbumDetailsRepository) {
    suspend operator fun invoke(albumId:Int)=repository.getPhotos(albumId)
}