package kg.abu.languagesource.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kg.abu.core.navigation.Routes
import kg.abu.feature_auth.AuthScreen
import kg.abu.feature_splash.OnBoardingScreen
import kg.abu.feature_splash.SplashScreen

@Composable
fun RootNavGraph() {

    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {
        composable(Routes.SPLASH) {
            SplashScreen(navController = navController, context = context)
        }

        composable(Routes.ON_BOARDING) {
            OnBoardingScreen(navController = navController)
        }

        composable(Routes.AUTH) {
            AuthScreen(
                navController = navController, context = context
            )
        }

        composable(Routes.LANGUAGE) {
            MainNavGraph()
        }
    }
}