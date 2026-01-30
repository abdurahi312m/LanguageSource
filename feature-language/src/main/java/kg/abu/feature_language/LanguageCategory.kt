package kg.abu.feature_language

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class LanguageCategory(
    val id: String,
    val title: String,
    val subtitle: String,
    val icon: ImageVector,
    val color: Color
)

sealed interface LanguageState {
    object Loading : LanguageState
    data class Success(
        val categories: List<LanguageCategory>
    ) : LanguageState
    data class Error(val message: String) : LanguageState
}
