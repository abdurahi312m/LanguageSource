package kg.abu.domain.model.training

import kg.abu.domain.model.language.QuizQuestion

data class OrtReadTask(
    val id: String,
    val title: String, // Заголовок текста
    val text: String,  // Сам длинный текст
    val questions: List<QuizQuestion> // Список вопросов к ЭТОМУ тексту
)

// Типы заданий ОРТ
enum class OrtSection {
    ANALOGY,    // Окшоштуктар
    SENTENCE,   // Сүйлөм толуктоо
    READING     // Окуу жана түшүнүү
}