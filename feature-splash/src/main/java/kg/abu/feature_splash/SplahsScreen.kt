package kg.abu.feature_splash

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import kg.abu.core.navigation.Routes
import kg.abu.data.AppPreferences
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first

@Composable
fun SplashScreen(
    navController: NavController,
    context: Context
) {
    val appPreferences = remember { AppPreferences(context) }

    LaunchedEffect(key1 = true) {
        delay(2000)

        val isCompleted = appPreferences.isOnBoardingCompleted.first()

        if (isCompleted) {
            navController.navigate(Routes.LANGUAGE)
        } else {
            navController.navigate(Routes.ON_BOARDING) {
                popUpTo(Routes.SPLASH) { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("SPLASH LOGO")
    }
}