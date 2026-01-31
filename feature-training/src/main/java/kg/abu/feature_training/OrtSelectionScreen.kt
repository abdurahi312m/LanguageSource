package kg.abu.feature_training

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.CompareArrows
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.CompareArrows
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kg.abu.domain.model.training.OrtSection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrtSelectionScreen(
    onBackClick: () -> Unit,
    onSectionClick: (OrtSection) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("ОРТга даярдоо") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, "Назад")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                "Бөлүмдү тандаңыз:",
                style = MaterialTheme.typography.titleMedium
            )

            // Карточка Аналогии
            TrainingCard(
                mode = TrainingMode(
                    "Окшоштуктар жана сүйлөм толуктоо",
                    Icons.Default.CompareArrows,
                    Color(0xFFFFCC80)
                )
            ) { onSectionClick(OrtSection.ANALOGY) } // Используем одну карточку для двух типов пока

            // Карточка Чтение
            TrainingCard(
                mode = TrainingMode(
                    "Окуу жана түшүнүү (Текст менен иштөө)",
                    Icons.Default.Book,
                    Color(0xFF80CBC4)
                )
            ) { onSectionClick(OrtSection.READING) }
        }
    }
}