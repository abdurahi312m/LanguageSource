package kg.abu.core.navigation

import kotlinx.serialization.Serializable

sealed interface AppDestination

@Serializable
data object LanguageScreen: AppDestination

@Serializable
data object LiteratureScreen: AppDestination

@Serializable
data object LibraryScreen: AppDestination

@Serializable
data object TrainingScreen: AppDestination

@Serializable
data object SettingsScreen: AppDestination