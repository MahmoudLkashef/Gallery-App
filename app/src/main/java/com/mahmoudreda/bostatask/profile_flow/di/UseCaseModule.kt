package com.mahmoudreda.bostatask.profile_flow.di

import com.mahmoudreda.bostatask.profile_flow.domain.repository.ProfileRepository
import com.mahmoudreda.bostatask.profile_flow.domain.usecase.GetAlbumsUseCase
import com.mahmoudreda.bostatask.profile_flow.domain.usecase.GetUsersUseCase
import com.mahmoudreda.bostatask.profile_flow.domain.usecase.ProfileUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {

    @Provides
    @Singleton
    fun provideProfileUseCase(repository: ProfileRepository): ProfileUseCase {
        return ProfileUseCase(
            getUsersUseCase = GetUsersUseCase(repository),
            getAlbumsUseCase = GetAlbumsUseCase(repository)
        )
    }
}