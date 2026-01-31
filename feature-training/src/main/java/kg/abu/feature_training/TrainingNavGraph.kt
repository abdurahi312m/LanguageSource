package kg.abu.feature_training

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import kg.abu.core.navigation.Routes
import kg.abu.domain.model.language.DifficultyLevel
import kg.abu.domain.model.training.OrtSection
import kg.abu.domain.model.training.TrainingCategory

fun NavGraphBuilder.trainingNavGraph(navController: NavController) {
    navigation(
        startDestination = Routes.TRAINING_HOME,
        route = Routes.TRAINING
    ) {
        composable(Routes.TRAINING_HOME) {
            TrainingScreen(
                onNavigateToCategory = { category ->
                    when(category) {
                        TrainingCategory.GRAMMAR -> navController.navigate(Routes.TRAINING_GRAMMAR)
                        TrainingCategory.ORT -> navController.navigate(Routes.TRAINING_ORT)
                        TrainingCategory.ANALYTICS -> navController.navigate(Routes.TRAINING_ANALYTICS)
                    }
                },
                onNavigateToCreateTest = {
                    navController.navigate(Routes.TRAINING_CREATE)
                }
            )
        }

        composable(Routes.TRAINING_GRAMMAR) {
            GrammarScreen(
                onBackClick = { navController.popBackStack() },
                onTopicClick = { topicId ->
                    navController.navigate(Routes.getTrainingLevelsRoute(topicId))
                }
            )
        }

        composable(
            route = Routes.TRAINING_LEVELS,
            arguments = listOf(navArgument("topicId") { type = NavType.StringType })
        ) { backStackEntry ->
            val topicId = backStackEntry.arguments?.getString("topicId") ?: return@composable

            var showTest by remember { mutableStateOf(false) }
            var selectedDifficulty by remember { mutableStateOf(DifficultyLevel.BASIC) }

            if (showTest) {
                TrainingQuizScreen(topicId, selectedDifficulty) { showTest = false }
            } else {
                GrammarLevelsScreen(
                    topicId = topicId,
                    onBackClick = { navController.popBackStack() },
                    onStartTest = { _, level ->
                        selectedDifficulty = level
                        showTest = true
                    }
                )
            }
        }

        composable(Routes.TRAINING_ORT) {
            OrtSelectionScreen(
                onBackClick = { navController.popBackStack() },
                onSectionClick = { section ->
                    when (section) {
                        OrtSection.READING -> navController.navigate("training/ort/reading")
                        else -> navController.navigate("training/ort/analogy")
                    }
                }
            )
        }

        composable("training/ort/analogy") {
            TrainingQuizScreen(
                topicId = "ort_analogy",
                difficulty = DifficultyLevel.ADVANCED,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("training/ort/reading") {
            OrtReadingScreen(
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(Routes.TRAINING_ANALYTICS) {
            AnalyticsScreen()
        }

        composable(Routes.TRAINING_CREATE) {
            CreateTestScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}