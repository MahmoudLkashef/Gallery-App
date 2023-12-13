package com.mahmoudreda.bostatask.core.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.mahmoudreda.bostatask.R

@Composable
fun ImageLoader(url: String, modifier: Modifier = Modifier) {
    SubcomposeAsyncImage(
        modifier = modifier,
        contentScale = ContentScale.Fit,
        model = url,
        contentDescription = "",
        loading = {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.padding_32))
                )
            }

        },
        error = { painterResource(id = R.drawable.error_icon) },
        success = { SubcomposeAsyncImageContent() }
    )

}