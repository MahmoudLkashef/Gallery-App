package com.mahmoudreda.bostatask.profile_flow.data.model

class Albums : ArrayList<AlbumsItem>()

data class AlbumsItem(
    val id: Int,
    val title: String,
    val userId: Int
)