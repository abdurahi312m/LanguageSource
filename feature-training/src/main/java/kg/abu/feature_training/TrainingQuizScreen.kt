package kg.abu.feature_training

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kg.abu.domain.model.language.DifficultyLevel
import kg.abu.domain.model.language.QuizQuestion
import kg.abu.domain.model.language.QuizRepository
import kg.abu.domain.model.training.OrtRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrainingQuizScreen(
    topicId: String,
    difficulty: DifficultyLevel,
    onBackClick: () -> Unit
) {

    val allQuestions = remember {
        if (topicId == "ort_analogy") {
            OrtRepository.getAnalogies() + OrtRepository.getSentenceCompletions()
        } else {
            QuizRepository.getQuestionsByCategory(topicId)
        }
    }

    val questions = remember(allQuestions) {
        if (allQuestions.isEmpty()) emptyList() else allQuestions.shuffled().take(10)
    }

    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    var selectedOptionIndex by remember { mutableStateOf<Int?>(null) }
    var score by remember { mutableIntStateOf(0) }
    var isQuizFinished by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            "Тест: ${getTopicName(topicId)}",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(difficulty.name, style = MaterialTheme.typography.labelSmall)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Назад")
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            if (questions.isEmpty()) {
                Text("Суроолор жок (Вопросов нет)", color = Color.Gray)
            } else if (!isQuizFinished) {
                TrainingQuestionContent(
                    question = questions[currentQuestionIndex],
                    currentStep = currentQuestionIndex + 1,
                    totalSteps = questions.size,
                    selectedOption = selectedOptionIndex,
                    onOptionSelected = { selectedOptionIndex = it },
                    onNextClick = {
                        if (selectedOptionIndex == questions[currentQuestionIndex].correctAnswerIndex) {
                            score++
                        }

                        if (currentQuestionIndex < questions.size - 1) {
                            currentQuestionIndex++
                            selectedOptionIndex = null
                        } else {
                            isQuizFinished = true
                        }
                    }
                )
            } else {
                TrainingResultContent(
                    score = score,
                    total = questions.size,
                    onBack = onBackClick,
                    onRetry = {
                        currentQuestionIndex = 0
                        selectedOptionIndex = null
                        score = 0
                        isQuizFinished = false
                    }
                )
            }
        }
    }
}

fun getTopicName(id: String): String {
    return when (id) {
        "noun" -> "Зат атооч"
        "adjective" -> "Сын атооч"
        "numeral" -> "Сан атооч"
        "pronoun" -> "Ат атооч"
        "verb" -> "Этиш"
        else -> id
    }
}

@Composable
fun TrainingQuestionContent(
    question: QuizQuestion,
    currentStep: Int,
    totalSteps: Int,
    selectedOption: Int?,
    onOptionSelected: (Int) -> Unit,
    onNextClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        LinearProgressIndicator(
            progress = { currentStep.toFloat() / totalSteps.toFloat() },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .padding(vertical = 8.dp),
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Вопрос $currentStep из $totalSteps",
            style = MaterialTheme.typography.labelMedium,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = question.question,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        question.options.forEachIndexed { index, option ->
            val isSelected = selectedOption == index
            OutlinedButton(
                onClick = { onOptionSelected(index) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else Color.Transparent,
                    contentColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                ),
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    width = if (isSelected) 2.dp else 1.dp,
                    brush = androidx.compose.ui.graphics.SolidColor(
                        if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray
                    )
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${('A' + index)}.",
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(text = option, fontSize = 16.sp)
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onNextClick,
            enabled = selectedOption != null,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = if (currentStep == totalSteps) "Завершить" else "Далее",
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun TrainingResultContent(
    score: Int,
    total: Int,
    onBack: () -> Unit,
    onRetry: () -> Unit
) {
    val percentage = (score.toFloat() / total.toFloat()) * 100

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = if (percentage >= 50) Icons.Default.EmojiEvents else Icons.Default.CheckCircle,
            contentDescription = null,
            tint = if (percentage >= 80) Color(0xFFFFD700) else MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = if (percentage >= 80) "Мыкты жыйынтык!" else "Жакшы!",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Сиздин балл: $score / $total",
            style = MaterialTheme.typography.titleLarge,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = onRetry,
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Кайра тапшыруу")
        }

        TextButton(onClick = onBack) {
            Text("Менюга кайтуу")
        }
    }
}