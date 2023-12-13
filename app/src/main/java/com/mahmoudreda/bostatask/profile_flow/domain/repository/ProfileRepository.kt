package com.mahmoudreda.bostatask.profile_flow.domain.repository

import com.mahmoudreda.bostatask.core.DataResult
import com.mahmoudreda.bostatask.profile_flow.data.model.AlbumsItem
import com.mahmoudreda.bostatask.profile_flow.domain.model.UserUIModel

interface ProfileRepository {
    suspend fun getUsers(): DataResult<UserUIModel>
    suspend fun getAlbums(userId: Int): DataResult<List<AlbumsItem>>
}