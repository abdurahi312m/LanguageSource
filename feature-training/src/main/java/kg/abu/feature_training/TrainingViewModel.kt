package kg.abu.feature_training

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kg.abu.core.AppPreferences
import kg.abu.domain.model.training.TrainingCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class TrainingState(
    val isTeacher: Boolean = false,
    val isLoading: Boolean = false,
    val categories: List<TrainingCategory> = TrainingCategory.entries.toList()
)

class TrainingViewModel(
    private val appPreferences: AppPreferences
) : ViewModel() {

    private val _state = MutableStateFlow(TrainingState())
    val state = _state.asStateFlow()

    init {
        loadUserProfile()
    }

    private fun loadUserProfile() {
        viewModelScope.launch {
            appPreferences.isTeacher.collect { isTeacher ->
                _state.value = _state.value.copy(
                    isTeacher = isTeacher,
                    isLoading = false
                )
            }
        }
    }

    fun onCreateTestClick() {}
}