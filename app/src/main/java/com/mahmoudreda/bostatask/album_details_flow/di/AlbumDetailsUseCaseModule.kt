package com.mahmoudreda.bostatask.album_details_flow.di

import com.mahmoudreda.bostatask.album_details_flow.domain.repository.AlbumDetailsRepository
import com.mahmoudreda.bostatask.album_details_flow.domain.usecase.GetPhotosUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AlbumDetailsUseCaseModule {

    @Provides
    @Singleton
    fun provideGetPhotoUseCase(repository: AlbumDetailsRepository):GetPhotosUseCase{
        return GetPhotosUseCase(repository)
    }
}