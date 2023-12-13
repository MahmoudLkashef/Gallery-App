package com.mahmoudreda.bostatask.album_details_flow.presentation.album_details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mahmoudreda.bostatask.R
import com.mahmoudreda.bostatask.core.components.HandleState
import com.mahmoudreda.bostatask.core.components.ImageLoader
import com.mahmoudreda.bostatask.core.components.SearchComponent

@Composable
fun AlbumDetailsScreen(
    albumDetailsViewModel: AlbumDetailsViewModel,
    onImageClicked: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        val albumState by remember { albumDetailsViewModel.albumsState }
        var searchQuery by remember { mutableStateOf("") }

        HandleState(
            state = albumState,
            onSuccess = { albumPhotosList ->
                AlbumTopBar(albumTitle = albumDetailsViewModel.getAlbumTitle(), onTextChanged = { query -> searchQuery = query })
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3)
                ) {
                    items(albumDetailsViewModel.getFilteredList(searchQuery,albumPhotosList)) { photo ->
                        ImageLoader(url = photo.url, modifier = Modifier.clickable { onImageClicked(photo.url) })
                    }
                }
            },
            onRetryAction = { albumDetailsViewModel.getAlbumPhotos() }
        )

    }

}


@Composable
fun AlbumTopBar(albumTitle:String?,onTextChanged: (String) -> Unit) {
    Column {
        Text(
            text = albumTitle ?: "",
            fontSize = 20.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_8))
        )
        Spacer(modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_4)))
        Divider(
            Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.padding_1)),
            color = Color.Gray
        )
        Spacer(modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_4)))

        SearchComponent(
            onSearch = { query -> onTextChanged(query) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}