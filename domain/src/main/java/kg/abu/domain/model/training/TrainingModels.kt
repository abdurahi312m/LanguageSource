package kg.abu.domain.model.training

import kg.abu.domain.model.language.DifficultyLevel

enum class UserRole {
    STUDENT,
    TEACHER
}

enum class TrainingCategory(val id: String, val title: String) {
    GRAMMAR("grammar", "Грамматика"),
    ORT("ort", "Подготовка к ОРТ"),
    ANALYTICS("analytics", "Прогресс и аналитика")
}

enum class OrtSubCategory(val id: String, val title: String) {
    READING("reading", "Окуу жана түшүнүү"),
    ANALOGIES("analogies", "Окшоштуктар жана сүйлөм толуктоо")
}

data class TestMetadata(
    val id: String,
    val title: String,
    val description: String,
    val category: TrainingCategory,
    val difficulty: DifficultyLevel,
    val durationMinutes: Int? = null,
    val questionsCount: Int,
    val authorId: String? = null
)

data class CreateTestDraft(
    val topic: String = "",
    val grade: Int = 9,
    val questionCount: Int = 10,
    val difficulty: DifficultyLevel = DifficultyLevel.BASIC,
    val isAiGenerated: Boolean = false
)