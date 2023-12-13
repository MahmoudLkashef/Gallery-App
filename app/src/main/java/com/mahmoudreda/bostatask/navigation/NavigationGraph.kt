package com.mahmoudreda.bostatask.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mahmoudreda.bostatask.album_details_flow.presentation.album_details.AlbumDetailsScreen
import com.mahmoudreda.bostatask.album_details_flow.presentation.album_details.AlbumDetailsViewModel
import com.mahmoudreda.bostatask.album_details_flow.presentation.image_viewer.ImageViewerScreen
import com.mahmoudreda.bostatask.album_details_flow.presentation.image_viewer.ImageViewerViewModel
import com.mahmoudreda.bostatask.profile_flow.presentation.ProfileScreen
import com.mahmoudreda.bostatask.profile_flow.presentation.ProfileViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screens.ProfileScreen.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screens.ProfileScreen.route) {
            val profileViewModel: ProfileViewModel = hiltViewModel()
            ProfileScreen(
                profileViewModel = profileViewModel,
                onAlbumClicked = { albumId,albumTitle ->
                    navController.navigate(Screens.AlbumDetailsScreen.passAlbumData(albumId,albumTitle))
                }
            )
        }

        composable(
            route = Screens.AlbumDetailsScreen.route,
            arguments = listOf(
                navArgument("albumId") {
                    type = NavType.StringType
                },
                navArgument("albumTitle") {
                    type = NavType.StringType
                }
            )
        ) {
            val albumDetailsViewModel: AlbumDetailsViewModel = hiltViewModel()
            AlbumDetailsScreen(
                albumDetailsViewModel = albumDetailsViewModel,
                onImageClicked = {imageUrl->
                    navController.navigate(Screens.ImageViewerScreen.passImageUrl(imageUrl))
                }
            )
        }

        composable(
            route = Screens.ImageViewerScreen.route,
            arguments = listOf(
                navArgument("imageUrl") {
                    type = NavType.StringType
                }
            )
        ) {
            val imageViewerViewModel:ImageViewerViewModel= hiltViewModel()
            ImageViewerScreen(imageViewerViewModel)
        }
    }
}