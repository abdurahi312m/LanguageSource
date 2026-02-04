package kg.abu.feature_profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kg.abu.core.AppPreferences
import kg.abu.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ProfileState(
    val email: String = "Загрузка...",
    val isTeacher: Boolean = false,
    val isAnonymous: Boolean = false
)

class ProfileViewModel(
    private val appPreferences: AppPreferences,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()

    private val _logoutEvent = MutableStateFlow(false)
    val logoutEvent = _logoutEvent.asStateFlow()

    init {
        loadProfile()
    }

    private fun loadProfile() {
        viewModelScope.launch {
            appPreferences.isTeacher.collect { isTeacher ->
                val userId = authRepository.getUserId()
                val isAnon = userId == null

                val realEmail = authRepository.getUserEmail() ?: "Гость"

                _state.value = _state.value.copy(
                    isTeacher = isTeacher,
                    email = realEmail,
                    isAnonymous = isAnon
                )
            }
        }
    }

    fun onLogoutClick() {
        viewModelScope.launch {
            authRepository.signOut()
            _logoutEvent.value = true
        }
    }
}