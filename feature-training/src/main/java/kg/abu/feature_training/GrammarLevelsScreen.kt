package kg.abu.feature_training

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kg.abu.domain.model.language.DifficultyLevel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GrammarLevelsScreen(
    topicId: String,
    onBackClick: () -> Unit,
    onStartTest: (String, DifficultyLevel) -> Unit
) {
    val levels = listOf(
        DifficultyLevel.BASIC to "Базовый тест",
        DifficultyLevel.GRADES_5_7 to "Орто (5-7 класс)",
        DifficultyLevel.ADVANCED to "Татаал (Сложный)"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Выберите уровень") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Назад")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    "Даярдыгыңызды текшериңиз",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Gray
                )
            }

            items(levels) { (level, title) ->
                LevelCard(
                    title = title,
                    level = level,
                    onClick = { onStartTest(topicId, level) }
                )
            }
        }
    }
}

@Composable
fun LevelCard(title: String, level: DifficultyLevel, onClick: () -> Unit) {
    val (icon, color) = when (level) {
        DifficultyLevel.BASIC -> Icons.Default.School to Color(0xFF81C784)
        DifficultyLevel.GRADES_5_7 -> Icons.Default.Star to Color(0xFF64B5F6)
        DifficultyLevel.ADVANCED -> Icons.Default.Bolt to Color(0xFFE57373)
        else -> Icons.Default.School to Color.Gray
    }

    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)),
        modifier = Modifier.fillMaxWidth().height(80.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(32.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}