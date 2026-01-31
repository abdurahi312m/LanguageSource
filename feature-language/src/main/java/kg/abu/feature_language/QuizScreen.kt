package kg.abu.feature_language

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kg.abu.domain.model.language.QuizRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    categoryId: String,
    categoryTitle: String,
    onBackClick: () -> Unit
) {
    val questions = remember { QuizRepository.getQuestionsByCategory(categoryId) }

    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    var selectedOptionIndex by remember { mutableStateOf<Int?>(null) }
    var score by remember { mutableIntStateOf(0) }
    var isQuizFinished by remember { mutableStateOf(false) }

    if (questions.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Бул бөлүмдө тесттер азырынча жок.")
        }
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(categoryTitle, fontSize = 18.sp) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Назад")
                    }
                }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).padding(16.dp).fillMaxSize()) {
            if (!isQuizFinished) {
                QuizContent(
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
                QuizResultContent(
                    score = score,
                    total = questions.size,
                    onRetry = {
                        currentQuestionIndex = 0
                        selectedOptionIndex = null
                        score = 0
                        isQuizFinished = false
                    },
                    onBack = onBackClick
                )
            }
        }
    }
}