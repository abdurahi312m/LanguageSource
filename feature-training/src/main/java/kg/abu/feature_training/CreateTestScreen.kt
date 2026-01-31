package kg.abu.feature_training

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTestScreen(
    onBackClick: () -> Unit
) {
    var topic by remember { mutableStateOf("") }
    var questionCount by remember { mutableFloatStateOf(5f) }
    var difficulty by remember { mutableStateOf("Орто (Средний)") }

    var isGenerating by remember { mutableStateOf(false) }
    var isSuccess by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Тест түзүү (AI)") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) { Icon(Icons.Default.ArrowBack, null) }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isSuccess) {
                Icon(Icons.Default.AutoAwesome,
                    null, modifier = Modifier.size(80.dp),
                    tint = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.height(16.dp))
                Text("Тест даяр!", style = MaterialTheme.typography.headlineMedium)
                Text("Сиздин окуучулар эми бул тестти көрө алышат.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(modifier = Modifier.height(32.dp))
                Button(onClick = onBackClick) { Text("Бүттү") }
            } else if (isGenerating) {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(16.dp))
                Text("Жасалма интеллект суроолорду түзүп жатат...", style = MaterialTheme.typography.bodyLarge)
            } else {
                Text(
                    "Параметрлерди тандаңыз, AI өзү түзүп берет.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(24.dp))

                OutlinedTextField(
                    value = topic,
                    onValueChange = { topic = it },
                    label = { Text("Тема (мисалы: Зат атооч)") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text("Суроолордун саны: ${questionCount.toInt()}")
                Slider(
                    value = questionCount,
                    onValueChange = { questionCount = it },
                    valueRange = 3f..20f,
                    steps = 16
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text("Кыйындыгы")
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    FilterChip(selected = difficulty == "Оңой",
                        onClick = { difficulty = "Оңой" }, label = { Text("Оңой") })
                    FilterChip(selected = difficulty == "Орто",
                        onClick = { difficulty = "Орто" }, label = { Text("Орто") })
                    FilterChip(selected = difficulty == "Татаал",
                        onClick = { difficulty = "Татаал" }, label = { Text("Татаал") })
                }

                Spacer(modifier = Modifier.height(48.dp))

                Button(
                    onClick = {
                        isGenerating = true
                    },
                    enabled = topic.isNotEmpty(),
                    modifier = Modifier.fillMaxWidth().height(56.dp)
                ) {
                    Icon(Icons.Default.AutoAwesome, null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Генерациялоо (Создать)")
                }

                LaunchedEffect(isGenerating) {
                    if (isGenerating) {
                        delay(3000)
                        isGenerating = false
                        isSuccess = true
                    }
                }
            }
        }
    }
}