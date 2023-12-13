package com.mahmoudreda.bostatask.profile_flow.domain.usecase

data class ProfileUseCase(
    val getUsersUseCase: GetUsersUseCase,
    val getAlbumsUseCase: GetAlbumsUseCase,
)
