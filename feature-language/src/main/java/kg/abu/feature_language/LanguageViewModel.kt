package kg.abu.feature_language

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Label
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kg.abu.domain.model.language.DifficultyLevel
import kg.abu.domain.model.language.WordModel
import kg.abu.domain.model.language.WordType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class DictionaryState(
    val searchResults: List<WordModel> = emptyList(),
    val history: List<WordModel> = emptyList(),
    val favorites: List<WordModel> = emptyList(),
    val query: String = ""
)

class LanguageViewModel : ViewModel() {

    private val _dictState = MutableStateFlow(DictionaryState())
    val dictState = _dictState.asStateFlow()

    private val _state = MutableStateFlow<LanguageState>(LanguageState.Loading)
    val state = _state.asStateFlow()

    private val _horizontalCategories = MutableStateFlow<List<LanguageCategory>>(emptyList())
    val horizontalCategories = _horizontalCategories.asStateFlow()

    init {
        loadDashboard()
    }

    private val allWords = listOf(
        WordModel(
            id = "1",
            term = "Китеп",
            definition = "Басма түрүндө чыккан жазма чыгарма.",
            modernEquivalent = null,
            wordType = WordType.COMMON,
            difficultyLevel = DifficultyLevel.BASIC,
            root = "китеп",
            partOfSpeech = "зат атооч",
            suffixes = "жок",
            forms = listOf("китептер (мн.ч.)", "китепти (табыш ж.)", "китепке (барыш ж.)"),
            grammarTopic = "Зат атоочтун жөндөлүшү",
            grammarRule = "Жөндөмөлөр зат атоочтун сүйлөмдөгү башка сөздөр менен болгон байланышын билдирет.",
            caseTable = mapOf(
                "Атооч" to "китеп",
                "Илик" to "китептин",
                "Барыш" to "китепке",
                "Табыш" to "китепти"
            ),
            examples = listOf("Мен жаңы китеп окудум.")
        ),
        WordModel(
            id = "2",
            term = "Жарлык",
            definition = "Хандын буйругу.",
            modernEquivalent = "Указ",
            wordType = WordType.ARCHAIC,
            difficultyLevel = DifficultyLevel.GRADES_8_9,
            root = "жарлык",
            partOfSpeech = "зат атооч",
            forms = listOf("жарлыктар", "жарлыкты"),
            examples = listOf("Хан жарлык чачты.")
        )
    )

    fun onSearchQueryChange(newQuery: String) {
        _dictState.update { it.copy(query = newQuery) }
        if (newQuery.isBlank()) {
            _dictState.update { it.copy(searchResults = emptyList()) }
        } else {
            val results = allWords.filter { it.term.contains(newQuery, ignoreCase = true) }
            _dictState.update { it.copy(searchResults = results) }
        }
    }

    fun addToHistory(word: WordModel) {
        _dictState.update { state ->
            val newHistory = (listOf(word) + state.history.filter { it.id != word.id }).take(10)
            state.copy(history = newHistory)
        }
    }

    fun toggleFavorite(word: WordModel) {
        word.isFavorite = !word.isFavorite
        _dictState.update { state -> state.copy(favorites = allWords.filter { it.isFavorite }) }
    }

    private fun loadDashboard() {
    viewModelScope.launch {
        _state.value = LanguageState.Loading

        val mainCategories = listOf(
            LanguageCategory("dictionary", "Сөздүк", "Сөздөрдүн мааниси", Icons.Default.Search, Color(0xFFE8F5E9)),
            LanguageCategory("proverbs", "Макалдар", "Эл акылмандыгы", Icons.Default.Lightbulb, Color(0xFFF3E5F5))
        )
        _state.value = LanguageState.Success(mainCategories)

        val horizontalTests = listOf(
            LanguageCategory("noun", "1.1 Зат атооч", "Существительное", Icons.Default.Label, Color(0xFFE3F2FD)),
            LanguageCategory("adjective", "1.2 Сын атооч", "Прилагательное", Icons.Default.Palette, Color(0xFFFFF3E0)),
            LanguageCategory("numeral", "1.3 Сан атооч", "Числительное", Icons.Default.Numbers, Color(0xFFF1F8E9)),
            LanguageCategory("pronoun", "1.4 Ат атооч", "Местоимение", Icons.Default.Person, Color(0xFFEFEBE9)),
            LanguageCategory("verb", "1.5 Этиш", "Глагол", Icons.Default.PlayArrow, Color(0xFFFCE4EC))
        )
        _horizontalCategories.value = horizontalTests
    }
}

}