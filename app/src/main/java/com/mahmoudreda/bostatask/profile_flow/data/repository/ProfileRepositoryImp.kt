package com.mahmoudreda.bostatask.profile_flow.data.repository

import com.mahmoudreda.bostatask.core.DataResult
import com.mahmoudreda.bostatask.profile_flow.data.model.toUserUIModel
import com.mahmoudreda.bostatask.profile_flow.data.remote.ProfileRemoteDataSource
import com.mahmoudreda.bostatask.profile_flow.domain.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Random
import javax.inject.Inject

class ProfileRepositoryImp @Inject constructor(
    private val profileRemoteDataSource: ProfileRemoteDataSource
) : ProfileRepository {

    override suspend fun getUsers() = withContext(Dispatchers.IO) {
         val dataResult=profileRemoteDataSource.getUsers()
        when(dataResult){
            is DataResult.Success->{
                DataResult.Success(dataResult.data.map { it.toUserUIModel() }[Random().nextInt(dataResult.data.size)])
            }
            is DataResult.Error->{
                DataResult.Error(throwable = dataResult.throwable)
            }
            is DataResult.Loading->{
                DataResult.Loading
            }
            is DataResult.Idle->{
                DataResult.Idle
            }
        }
    }

    override suspend fun getAlbums(userId: Int) = withContext(Dispatchers.IO) {
        profileRemoteDataSource.getAlbums(userId)
    }

}