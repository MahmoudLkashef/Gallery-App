package com.mahmoudreda.bostatask.album_details_flow.data.remote

import com.mahmoudreda.bostatask.album_details_flow.data.model.Photos
import com.mahmoudreda.bostatask.core.constants.EndPoints
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumDetailsService {
    @GET(EndPoints.PHOTOS)
    suspend fun getPhotos(
        @Query("albumId") albumId:Int
    ): Response<Photos>
}