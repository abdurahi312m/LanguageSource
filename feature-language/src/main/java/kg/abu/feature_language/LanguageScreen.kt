package kg.abu.feature_language

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kg.abu.domain.model.language.WordModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun LanguageScreen(
    modifier: Modifier = Modifier,
    viewModel: LanguageViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    val horizontalCategories by viewModel.horizontalCategories.collectAsState()
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    var selectedWord by remember { mutableStateOf<WordModel?>(null) }

    BackHandler(enabled = selectedWord != null || selectedCategory != null) {
        if (selectedWord != null) {
            selectedWord = null
        } else {
            selectedCategory = null
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        when {
            selectedWord != null -> {
                WordDetailScreen(word = selectedWord!!, onBackClick = { selectedWord = null })
            }
            selectedCategory == "dictionary" -> {
                DictionaryScreen(
                    onBackClick = { selectedCategory = null },
                    onWordClick = { word -> selectedWord = word }
                )
            }
            selectedCategory == "proverbs" -> {
                ProverbsScreen(onBackClick = { selectedCategory = null })
            }
            selectedCategory != null -> {
                val categoryTitle = horizontalCategories.find { it.id == selectedCategory }?.title ?: "Тест"

                QuizScreen(
                    categoryId = selectedCategory!!,
                    categoryTitle = categoryTitle,
                    onBackClick = { selectedCategory = null }
                )
            }
            else -> {
                if (state is LanguageState.Success) {
                    LanguageContent(
                        mainCategories = (state as LanguageState.Success).categories,
                        testCategories = horizontalCategories,
                        onCategoryClick = { id -> selectedCategory = id }
                    )
                }
            }
        }
    }
}

















