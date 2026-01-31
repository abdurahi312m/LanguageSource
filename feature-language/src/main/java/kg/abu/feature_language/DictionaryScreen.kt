package kg.abu.feature_language

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kg.abu.domain.model.language.WordModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DictionaryScreen(
    viewModel: LanguageViewModel = koinViewModel(),
    onBackClick: () -> Unit,
    onWordClick: (WordModel) -> Unit
) {
    val dictState by viewModel.dictState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Сөздүк") },
                navigationIcon = { IconButton(onClick = onBackClick)
                { Icon(Icons.Default.ArrowBack, "Назад") } }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            OutlinedTextField(
                value = dictState.query,
                onValueChange = { viewModel.onSearchQueryChange(it) },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Сөздү издөө...") },
                leadingIcon = { Icon(Icons.Default.Search, null) },
                trailingIcon = {
                     if(dictState.query.isNotEmpty()) {
                         IconButton(onClick = { viewModel.onSearchQueryChange("") })
                         { Icon(Icons.Default.Clear, null) }
                     }
                },
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (dictState.query.isEmpty()) {
                if (dictState.history.isNotEmpty()) {
                    Text("Акыркы суроолор (История)",
                        style = MaterialTheme.typography.titleSmall, color = Color.Gray)
                    Spacer(modifier = Modifier.height(8.dp))
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(dictState.history) { word ->
                            Card(onClick = { onWordClick(word) },
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))) {
                                Row(Modifier.padding(12.dp).fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.History, null,
                                        tint = Color.Gray, modifier = Modifier.size(20.dp))
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Text(word.term, fontWeight = FontWeight.Medium)
                                }
                            }
                        }
                    }
                } else {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Сөздүккө кош келиңиз!", color = Color.Gray)
                    }
                }
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(dictState.searchResults) { word ->
                        WordItemCard(word = word, onClick = {
                            viewModel.addToHistory(word)
                            onWordClick(word)
                        })
                    }
                }
            }
        }
    }
}