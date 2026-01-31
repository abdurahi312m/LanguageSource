package kg.abu.domain.model.language

data class QuizQuestion(
    val id: Int,
    val question: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)

data class QuizResult(
    val totalQuestions: Int,
    val correctAnswers: Int,
    val scorePercentage: Int
)