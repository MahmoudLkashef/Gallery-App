package com.mahmoudreda.bostatask.profile_flow.data.remote

import com.mahmoudreda.bostatask.core.constants.EndPoints
import com.mahmoudreda.bostatask.profile_flow.data.model.Albums
import com.mahmoudreda.bostatask.profile_flow.data.model.Users
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProfileService {

    @GET(EndPoints.USERS)
    suspend fun getUsers():Response<Users>

    @GET(EndPoints.ALBUMS)
    suspend fun getAlbums(
        @Query("userId") userId:Int
    ):Response<Albums>
}