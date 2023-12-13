package com.mahmoudreda.bostatask.profile_flow.domain.usecase

import com.mahmoudreda.bostatask.profile_flow.domain.repository.ProfileRepository

class GetAlbumsUseCase(private val repository: ProfileRepository)  {
    suspend operator fun invoke(userId:Int)=repository.getAlbums(userId)
}