package com.mahmoudreda.bostatask.profile_flow.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahmoudreda.bostatask.core.DataResult
import com.mahmoudreda.bostatask.profile_flow.data.model.AlbumsItem
import com.mahmoudreda.bostatask.profile_flow.domain.model.UserUIModel
import com.mahmoudreda.bostatask.profile_flow.domain.usecase.ProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase
) : ViewModel() {

    private val _usersState = mutableStateOf<DataResult<UserUIModel>>(DataResult.Idle)
    val usersState: State<DataResult<UserUIModel>> = _usersState

    private val _albumsState = mutableStateOf<DataResult<List<AlbumsItem>>>(DataResult.Idle)
    val albumsState: State<DataResult<List<AlbumsItem>>> = _albumsState

    init {
        getRandomUser()
    }

    fun getRandomUser() {
        viewModelScope.launch {
            _usersState.value = DataResult.Loading
            _usersState.value = profileUseCase.getUsersUseCase()
        }
    }

    fun getAlbums(userId:Int) {
        viewModelScope.launch {
            _albumsState.value = DataResult.Loading
            _albumsState.value = profileUseCase.getAlbumsUseCase(userId)
        }
    }
}