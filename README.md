# Gallery App

## Overview

A simple Android app that shows user details and albums on one screen and lets you explore album images with a search feature on another. The app features two screens: the Profile Screen and the Album Details Screen.

### Screens

#### 1. Profile Screen

- Displays user name and address at the top.
- Lists all albums for the user.
- Retrieve user albums by making a request to the albums endpoint with the user id as a parameter.

#### 2. Album Details Screen

- Navigated to by selecting an album on the Profile Screen.
- Displays an Instagram-like grid of images from the selected album.
- Includes a search bar to filter images by title within the album.

### Technologies Used

- **Kotlin:** [Official Kotlin Website](https://kotlinlang.org/)
- **Material 3:** [Material Design Documentation](https://material.io/design)
- **Jetpack Compose:** [Jetpack Compose Documentation](https://developer.android.com/jetpack/compose)
- **Retrofit 2:** [Retrofit Documentation](https://square.github.io/retrofit/)
- **Moshi:** [Moshi Documentation](https://github.com/square/moshi)
- **Hilt Dagger:** [Hilt Dagger Documentation](https://dagger.dev/hilt/)
- **Coroutines:** [Coroutines Documentation](https://kotlinlang.org/docs/coroutines-overview.html)
- **MVVM:** [MVVM Architecture](https://developer.android.com/jetpack/guide#recommended-app-arch)
- **Coil:** [Coil Documentation](https://coil-kt.github.io/coil/)
- **Android Lifecycle:** [Android Lifecycle Documentation](https://developer.android.com/topic/libraries/architecture/lifecycle)
- **ViewModel:** [ViewModel Documentation](https://developer.android.com/topic/libraries/architecture/viewmodel)
- **Jetpack Library:** [Jetpack Library Overview](https://developer.android.com/jetpack)

### API Endpoints

- Base URL: [https://jsonplaceholder.typicode.com](https://jsonplaceholder.typicode.com)
  - User: GET /users
  - Albums: GET /albums (userId as a parameter)
  - Photos: GET /photos (albumId as a parameter)

### Bonus Points

- Open any image in a separate image viewer with zooming and sharing functionality implemented.

## Demo

https://github.com/MahmoudLkashef/Gallery-App/assets/76908523/bb08e7b6-09d7-4219-95a2-64c90773a926




