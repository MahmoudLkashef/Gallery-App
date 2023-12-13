package com.mahmoudreda.bostatask.album_details_flow.di

import com.mahmoudreda.bostatask.album_details_flow.data.remote.AlbumDetailsRemoteDataSource
import com.mahmoudreda.bostatask.album_details_flow.data.remote.AlbumDetailsRemoteDataSourceImp
import com.mahmoudreda.bostatask.album_details_flow.data.remote.AlbumDetailsService
import com.mahmoudreda.bostatask.album_details_flow.data.repository.AlbumDetailsRepositoryImp
import com.mahmoudreda.bostatask.album_details_flow.domain.repository.AlbumDetailsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class AlbumDetailsModule{

    @Binds
    abstract fun bindAlbumDetailsRemoteDataSource(albumDetailsRemoteDataSourceImp: AlbumDetailsRemoteDataSourceImp):AlbumDetailsRemoteDataSource

    @Binds
    abstract fun bindAlbumDetailsRepository(albumDetailsRepositoryImp: AlbumDetailsRepositoryImp):AlbumDetailsRepository
}


@InstallIn(SingletonComponent::class)
@Module
class AlbumDetailsApiService{

    @Provides
    @Singleton
    fun provideAlbumDetailsService(retrofit: Retrofit):AlbumDetailsService{
        return retrofit.create(AlbumDetailsService::class.java)
    }
}
