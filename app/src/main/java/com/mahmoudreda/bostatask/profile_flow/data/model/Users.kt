package com.mahmoudreda.bostatask.profile_flow.data.model

import com.mahmoudreda.bostatask.profile_flow.domain.model.UserUIModel

class Users : ArrayList<UserItem>()

data class UserItem(
    val id: Int,
    val name: String,
    val address: Address
)

data class Address(
    val city: String,
    val street: String,
    val suite: String,
    val zipcode: String
)

fun UserItem.toUserUIModel():UserUIModel{
    return UserUIModel(
        id = id,
        name = name,
        address = "${address.street}, ${address.suite}, ${address.city}, ${address.zipcode}"
    )
}