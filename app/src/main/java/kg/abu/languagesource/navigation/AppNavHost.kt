package kg.abu.languagesource.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kg.abu.feature_language.LanguageScreen
import kg.abu.feature_library.LibraryScreen
import kg.abu.feature_literature.LiteratureScreen
import kg.abu.feature_profile.ProfileScreen
import kg.abu.feature_splash.OnBoardingScreen
import kg.abu.feature_splash.SplashScreen
import kg.abu.feature_training.TrainingScreen

@Composable
fun AppNavHost() {
    RootNavGraph()

}