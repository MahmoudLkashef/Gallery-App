package com.mahmoudreda.bostatask.navigation

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

const val ALBUM_ID="albumId"
const val ALBUM_TITLE="albumTitle"
const val IMAGE_URL="imageUrl"
sealed class Screens(val route:String){
    object ProfileScreen:Screens("profile_screen")
    object AlbumDetailsScreen:Screens("album_details_screen/{$ALBUM_ID}/{$ALBUM_TITLE}"){
        fun passAlbumData(albumId:String,albumTitle:String):String{
            return AlbumDetailsScreen.route.replace(
                oldValue = "{$ALBUM_ID}",
                newValue = albumId
            ).replace(
                oldValue = "{$ALBUM_TITLE}",
                newValue = albumTitle
            )
        }
    }
    object ImageViewerScreen:Screens("image_viewer_screen/{$IMAGE_URL}"){
        fun passImageUrl(url:String): String {
            return ImageViewerScreen.route.replace(
                oldValue = "{$IMAGE_URL}",
                newValue = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
            )
        }
    }
}
