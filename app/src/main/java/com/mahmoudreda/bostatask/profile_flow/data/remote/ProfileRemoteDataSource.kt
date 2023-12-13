package com.mahmoudreda.bostatask.profile_flow.data.remote

import com.mahmoudreda.bostatask.core.DataResult
import com.mahmoudreda.bostatask.profile_flow.data.model.AlbumsItem
import com.mahmoudreda.bostatask.profile_flow.data.model.Users

interface ProfileRemoteDataSource {
    suspend fun getUsers(): DataResult<Users>
    suspend fun getAlbums(userId: Int): DataResult<List<AlbumsItem>>
}