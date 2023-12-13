package com.mahmoudreda.bostatask.profile_flow.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mahmoudreda.bostatask.R
import com.mahmoudreda.bostatask.core.DataResult
import com.mahmoudreda.bostatask.core.components.HandleState
import com.mahmoudreda.bostatask.profile_flow.data.model.AlbumsItem
import com.mahmoudreda.bostatask.profile_flow.domain.model.UserUIModel

@Composable
fun ProfileScreen(profileViewModel: ProfileViewModel, onAlbumClicked: (String,String) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {

        val userState by remember { profileViewModel.usersState }
        val albumState by remember { profileViewModel.albumsState }

        LaunchedEffect(userState) {
            if (userState is DataResult.Success) {
                profileViewModel.getAlbums((userState as DataResult.Success).data.id)
            }
        }

        HandleState(
            state = userState,
            onSuccess = { user ->
                ProfileTopBar(user)
            },
            onRetryAction = { profileViewModel.getRandomUser() }
        )


        HandleState(
            state = albumState,
            onSuccess = { albums ->
                GalleryAlbumsList(albums) { albumId,albumTitle ->
                    onAlbumClicked(albumId,albumTitle)
                }
            },
            onRetryAction = { profileViewModel.getAlbums((userState as DataResult.Success).data.id) }
        )

    }
}

@Composable
fun GalleryAlbumsList(albums: List<AlbumsItem>, onAlbumClicked: (String,String) -> Unit) {
    Column {
        LazyColumn {
            items(albums) { album ->
                AlbumsListItem(album) { clickedAlbumId, clickedAlbumTitle->
                    onAlbumClicked(clickedAlbumId,clickedAlbumTitle)
                }
            }
        }
    }
}

@Composable
fun AlbumsListItem(album: AlbumsItem, onAlbumClicked: (String,String) -> Unit) {
    Column(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.padding_8))
            .clickable { onAlbumClicked(album.id.toString(),album.title) }
    ) {
        Divider(
            Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.padding_1)),
            color = Color.Gray
        )
        Text(
            text = album.title,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.padding(
                horizontal = dimensionResource(id = R.dimen.padding_16),
                vertical = dimensionResource(id = R.dimen.padding_8)
            )
        )
    }
}

@Composable
fun ProfileTopBar(user: UserUIModel) {
    Column {
        Text(
            stringResource(R.string.profile),
            fontSize = 26.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_16))
        )
        Text(
            text = user.name,
            fontSize = 18.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_16))
        )
        Spacer(modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_4)))
        Text(
            text = user.address,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_16))
        )
        Spacer(modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_16)))
        Text(
            text = stringResource(R.string.my_albums),
            fontSize = 18.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_16))
        )
    }
}

