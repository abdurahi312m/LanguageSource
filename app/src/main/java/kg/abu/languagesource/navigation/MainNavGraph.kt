package kg.abu.languagesource.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kg.abu.core.navigation.Routes
import kg.abu.feature_language.LanguageScreen
import kg.abu.feature_library.LibraryScreen
import kg.abu.feature_literature.LiteratureScreen
import kg.abu.feature_profile.ProfileScreen
import kg.abu.feature_training.TrainingScreen

@Composable
fun MainNavGraph() {

    val navController = rememberNavController()

    val items = listOf(
        BottomNavItem(
            route = Routes.LANGUAGE,
            label = "Language",
            icon = Icons.Filled.Translate
        ),
        BottomNavItem(
            route = Routes.LIBRARY,
            label = "Library",
            icon = Icons.Filled.Menu
        ),
        BottomNavItem(
            route = Routes.LITERATURE,
            label = "Literature",
            icon = Icons.Filled.Book
        ),
        BottomNavItem(
            route = Routes.TRAINING,
            label = "Training",
            icon = Icons.Filled.School
        ),
        BottomNavItem(
            route = Routes.PROFILE,
            label = "Profile",
            icon = Icons.Filled.Person
        )
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val currentRoute =
                    navController.currentBackStackEntryAsState()
                        .value?.destination?.route

                items.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(
                                    navController.graph.findStartDestination().id
                                ) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.label
                            )
                        },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { padding ->

        NavHost(
            navController = navController,
            startDestination = Routes.LANGUAGE,
            modifier = Modifier.padding(padding)
        ) {
            composable(Routes.LANGUAGE) { LanguageScreen() }
            composable(Routes.LIBRARY) { LibraryScreen() }
            composable(Routes.LITERATURE) { LiteratureScreen() }
            composable(Routes.TRAINING) { TrainingScreen() }
            composable(Routes.PROFILE) { ProfileScreen() }
        }
    }
}