package kg.abu.feature_training

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kg.abu.domain.model.training.TrainingCategory
import kg.abu.domain.model.training.UserRole
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrainingScreen(
    modifier: Modifier = Modifier,
    viewModel: TrainingViewModel = koinViewModel(),
    onNavigateToCategory: (TrainingCategory) -> Unit = {},
    onNavigateToCreateTest: () -> Unit = {}
) {
    val state by viewModel.state.collectAsState()
    val scrollState = rememberScrollState()

    Scaffold(
        floatingActionButton = {
            if (state.isTeacher) {
                ExtendedFloatingActionButton(
                    onClick = { onNavigateToCreateTest() },
                    icon = { Icon(Icons.Default.Add, "Create") },
                    text = { Text("Создать тест") },
                    containerColor = MaterialTheme.colorScheme.primary
                )
            }
        }
    ) { padding ->
        Column(
            modifier = modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Тренировка",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Выберите режим обучения",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            TrainingSectionCard(
                category = TrainingCategory.GRAMMAR,
                onClick = { onNavigateToCategory(TrainingCategory.GRAMMAR) }
            )

            TrainingSectionCard(
                category = TrainingCategory.ORT,
                onClick = { onNavigateToCategory(TrainingCategory.ORT) }
            )

            TrainingSectionCard(
                category = TrainingCategory.ANALYTICS,
                onClick = { onNavigateToCategory(TrainingCategory.ANALYTICS) }
            )

            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}