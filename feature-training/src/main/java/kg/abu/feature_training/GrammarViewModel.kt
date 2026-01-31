package kg.abu.feature_training

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kg.abu.domain.model.language.DifficultyLevel
import kg.abu.domain.model.training.GrammarTopic
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GrammarViewModel : ViewModel() {

    private val _topics = MutableStateFlow<List<GrammarTopic>>(emptyList())
    val topics = _topics.asStateFlow()

    init {
        loadTopics()
    }

    private fun loadTopics() {
        viewModelScope.launch {
            _topics.value = listOf(
                GrammarTopic(
                    "noun", "Зат атооч (Имя существительное)",
                    "Жөндөмөлөр, түрлөрү",
                    10, 3, 85, DifficultyLevel.BASIC
                ),
                GrammarTopic(
                    "adjective", "Сын атооч (Имя прилагательное)",
                    "Даражалары жана түрлөрү",
                    8, 0, 0, DifficultyLevel.BASIC
                ),
                GrammarTopic(
                    "numeral", "Сан атооч (Имя числительное)",
                    "Эсептик, иреттик",
                    5, 5, 100, DifficultyLevel.GRADES_5_7
                ),
                GrammarTopic(
                    "pronoun", "Ат атооч (Местоимение)",
                    "Жактама, шилтеме",
                    12, 1, 40, DifficultyLevel.GRADES_8_9
                ),
                GrammarTopic(
                    "verb", "Этиш (Глагол)",
                    "Чактар, ыңгайлар",
                    20, 0, 0, DifficultyLevel.ADVANCED
                )
            )
        }
    }

}