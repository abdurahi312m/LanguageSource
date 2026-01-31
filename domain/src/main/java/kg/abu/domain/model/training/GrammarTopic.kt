package kg.abu.domain.model.training

import kg.abu.domain.model.language.DifficultyLevel

data class GrammarTopic(
    val id: String,
    val title: String,
    val description: String,
    val totalTests: Int,
    val completedTests: Int,
    val averageScore: Int,
    val difficulty: DifficultyLevel
) {
    val progress: Float
        get() = if (totalTests == 0) 0f else completedTests.toFloat() / totalTests
}
