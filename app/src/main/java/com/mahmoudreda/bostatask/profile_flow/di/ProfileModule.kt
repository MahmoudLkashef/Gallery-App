package com.mahmoudreda.bostatask.profile_flow.di

import com.mahmoudreda.bostatask.profile_flow.data.remote.ProfileRemoteDataSource
import com.mahmoudreda.bostatask.profile_flow.data.remote.ProfileRemoteDataSourceImp
import com.mahmoudreda.bostatask.profile_flow.data.remote.ProfileService
import com.mahmoudreda.bostatask.profile_flow.data.repository.ProfileRepositoryImp
import com.mahmoudreda.bostatask.profile_flow.domain.repository.ProfileRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class ProfileModule {

    @Binds
    abstract fun bindProfileRemoteDataSource(profileRemoteDataSourceImp: ProfileRemoteDataSourceImp): ProfileRemoteDataSource

    @Binds
    abstract fun bindProfileRepository(profileRepositoryImp: ProfileRepositoryImp):ProfileRepository
}


@InstallIn(SingletonComponent::class)
@Module
class ProfileApiServiceModule {

    @Provides
    @Singleton
    fun provideGalleryService(retrofit: Retrofit): ProfileService {
        return retrofit.create(ProfileService::class.java)
    }
}
