package kg.abu.feature_splash

import android.window.SplashScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onFinished: (isFirstLaunch: Boolean) -> Unit
) {
    LaunchedEffect(Unit) {
        delay(1500)
        val isFirstLaunch  = true
        onFinished(isFirstLaunch)
    }
}