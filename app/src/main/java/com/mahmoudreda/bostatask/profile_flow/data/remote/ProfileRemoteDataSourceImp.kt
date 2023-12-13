package com.mahmoudreda.bostatask.profile_flow.data.remote

import com.mahmoudreda.bostatask.core.DataResult
import com.mahmoudreda.bostatask.core.getDataResult
import com.mahmoudreda.bostatask.profile_flow.data.model.AlbumsItem
import com.mahmoudreda.bostatask.profile_flow.data.model.Users
import javax.inject.Inject

class ProfileRemoteDataSourceImp @Inject constructor(
    private val profileService: ProfileService
) : ProfileRemoteDataSource {

    override suspend fun getUsers(): DataResult<Users> {
        return try {
            profileService.getUsers().getDataResult()
        } catch (t: Throwable) {
            DataResult.Error(t)
        }
    }

    override suspend fun getAlbums(userId: Int): DataResult<List<AlbumsItem>> {
        return try {
            profileService.getAlbums(userId).getDataResult()
        } catch (t: Throwable) {
            DataResult.Error(t)
        }
    }



}