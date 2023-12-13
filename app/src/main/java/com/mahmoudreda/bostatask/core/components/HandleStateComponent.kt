package com.mahmoudreda.bostatask.core.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.mahmoudreda.bostatask.R
import com.mahmoudreda.bostatask.core.DataResult
import com.mahmoudreda.bostatask.core.remote.CircularProgressCustomDialog
import com.mahmoudreda.bostatask.core.remote.HandleError

@Composable
fun <T> HandleState(
    state: DataResult<T>,
    onSuccess: @Composable (T) -> Unit,
    onRetryAction: () -> Unit
) {

    when (state) {
        is DataResult.Success -> {
            onSuccess(state.data)
        }

        is DataResult.Error -> {
            val context = LocalContext.current
            context.HandleError(
                throwable = state.throwable,
                errorIcon = painterResource(id = R.drawable.no_internet_icon),
                retryAction = { onRetryAction() }
            )
        }

        is DataResult.Loading -> {
            CircularProgressCustomDialog()
        }

        is DataResult.Idle -> {}
    }

}
