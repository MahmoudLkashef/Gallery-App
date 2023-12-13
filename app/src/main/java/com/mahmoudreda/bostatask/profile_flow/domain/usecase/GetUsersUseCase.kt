package com.mahmoudreda.bostatask.profile_flow.domain.usecase

import com.mahmoudreda.bostatask.profile_flow.domain.repository.ProfileRepository

class GetUsersUseCase(private val repository: ProfileRepository) {
    suspend operator fun invoke()=repository.getUsers()
}