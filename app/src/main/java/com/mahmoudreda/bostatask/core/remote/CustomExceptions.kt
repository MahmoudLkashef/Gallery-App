package com.mahmoudreda.bostatask.core.remote

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahmoudreda.bostatask.R
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Throwable.getCustomException(context: Context): Throwable {
    return when (this) {
        is HttpException,
        is SocketTimeoutException,
        is UnknownHostException,
        is UnsatisfiedLinkError,
        is ConnectException -> {
            NetworkException(context)
        }

        else -> {
            UnknownException(context)
        }
    }
}

class NetworkException(private val context: Context) : Exception() {
    override val message: String
        get() = "Please make sure you are connected to the internet and try again"
}

class UnknownException(private val context: Context) : Exception() {
    override val message: String
        get() = "Something went wrong"
}

@Composable
fun CircularProgressCustomDialog() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black.copy(alpha = 0.3f) // Semi-transparent background
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(48.dp)
            )
        }
    }
}

@Composable
fun ErrorScreen(
    customError: Throwable,
    errorIcon: Painter,
    errorMessageTitle: String = "Oops!",
    backgroundColor: Color = Color(0xff6D2A97),
    buttonColor: Color = Color(0xFFAA6CD0),
    retryAction: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(
            painter = errorIcon,
            contentDescription = "Error state icon",
            tint = Color.Unspecified
        )
        Text(
            text = errorMessageTitle,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = customError.message ?: customError.localizedMessage,
            fontSize = 18.sp,
            color = Color.White,
            textAlign = TextAlign.Center
        )
        Button(
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.padding_200))
                .padding(
                    vertical = dimensionResource(id = R.dimen.padding_32)
                ),
            onClick = { retryAction() },
            colors = ButtonDefaults.buttonColors(buttonColor)
        ) {
            Text(
                text = "Retry",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun Context.HandleError(
    throwable: Throwable,
    errorIcon:Painter= painterResource(id = R.drawable.ic_launcher_background),
    retryAction: () -> Unit
) {
    throwable.printStackTrace()
    val customError = throwable.getCustomException(this)
        ErrorScreen(
            customError = customError,
            errorIcon = errorIcon,
            retryAction = {
                retryAction()
            }
        )
}

