package com.mahmoudreda.bostatask.album_details_flow.presentation.image_viewer

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat.startActivity
import com.mahmoudreda.bostatask.R
import com.mahmoudreda.bostatask.core.components.ImageLoader

@Composable
fun ImageViewerScreen(imageViewerViewModel: ImageViewerViewModel) {

    var scale by remember {
        mutableStateOf(1f)
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                }, contentAlignment = Alignment.Center
        ) {
            val state = rememberTransformableState { zoomChange, panChange, rotationChange ->
                scale = (scale * zoomChange).coerceIn(1f, 5f)
            }
            imageViewerViewModel.getImageUrl()?.let { imageUrl ->
                ImageLoader(
                    imageUrl,
                    Modifier
                        .fillMaxSize()
                        .transformable(state)
                )
            }
        }


        Box(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_16))
                .align(Alignment.BottomEnd)
        ) {
            val context= LocalContext.current
            IconButton(
                onClick = {
                    imageViewerViewModel.getImageUrl()?.let { url-> shareImageUrl(url, context) }
                },
                modifier = Modifier.background(color = Color.Gray,shape = CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = stringResource(R.string.share_icon),
                    tint = Color.White
                )
            }
        }

    }

}

fun shareImageUrl(url:String,context: Context){
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, url)
        type = "text/plain"
    }

    val activity= context as Activity

    val shareIntent = Intent.createChooser(sendIntent, "Check out This Image!!")

    activity.startActivity(shareIntent)
}

