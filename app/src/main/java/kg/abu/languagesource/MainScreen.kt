package kg.abu.languagesource

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kg.abu.feature_language.LanguageScreen
import kg.abu.feature_library.LibraryScreen
import kg.abu.feature_literature.LiteratureScreen
import kg.abu.feature_profile.ProfileScreen
import kg.abu.feature_training.TrainingScreen
import kg.abu.languagesource.ui.BottomBar

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            BottomBar(
                currentRoute = currentRoute,
                onLanguage = {
                    navController.navigate("language") {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                    }
                },
                onLibrary = {
                    navController.navigate("library") {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                    }
                },
                onLiterature = {
                    navController.navigate("literature") {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                    }
                },
                onTraining = {
                    navController.navigate("training") {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                    }
                },
                onProfile = {
                    navController.navigate("profile") {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                    }
                },
                onAuth = {
                    navController.navigate("auth") {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                    }
                }
            )
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "language",
            modifier = Modifier.padding(padding)
        ) {
            composable("language") { LanguageScreen() }
            composable("library") { LibraryScreen() }
            composable("literature") { LiteratureScreen() }
            composable("training") { TrainingScreen() }
            composable("profile") { ProfileScreen() }
//            composable("auth") { AuthScreen() }
        }
    }
}
