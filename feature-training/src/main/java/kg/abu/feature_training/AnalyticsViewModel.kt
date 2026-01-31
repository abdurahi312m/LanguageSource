package kg.abu.feature_training

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class AnalyticsState(
    val totalTestsPassed: Int = 0,
    val averageScore: Int = 0,
    val grammarProgress: Float = 0f,
    val ortProgress: Float = 0f,
    val recentActivity: List<ActivityItem> = emptyList()
)

data class ActivityItem(
    val title: String,
    val date: String,
    val score: Int
)

class AnalyticsViewModel : ViewModel() {

    private val _state = MutableStateFlow(AnalyticsState())
    val state = _state.asStateFlow()

    init {
        loadStatistics()
    }

    private fun loadStatistics() {
        viewModelScope.launch {
            _state.value = AnalyticsState(
                totalTestsPassed = 12,
                averageScore = 78,
                grammarProgress = 0.65f, // 65%
                ortProgress = 0.40f,     // 40%
                recentActivity = listOf(
                    ActivityItem("Зат атооч (Тест)", "Бүгүн", 90),
                    ActivityItem("Окуу жана түшүнүү", "Кечээ", 75),
                    ActivityItem("Окшоштуктар", "05.10.2023", 60)
                )
            )
        }
    }
}