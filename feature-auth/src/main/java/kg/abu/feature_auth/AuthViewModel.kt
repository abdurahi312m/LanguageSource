package kg.abu.feature_auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository,
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _authSuccess = MutableStateFlow(false)
    val authSuccess = _authSuccess.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    fun onGoogleSignIn(idToken: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            repository.signInWithGoogle(idToken)
                .onSuccess {
                    _authSuccess.value = true
                    _isLoading.value = false
                }
                .onFailure { exception ->
                    _error.value = exception.localizedMessage ?: "Google Sing In failed"
                    _isLoading.value = false
                }
        }
    }

    fun onLoginClick() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            val result = repository.signInAnonymously()

            result.onSuccess { success ->
                _authSuccess.value = true // _authSuccess.value = true.toString() в случае ошибки
                _isLoading.value = false
        }.onFailure { exception ->
                _error.value = exception.localizedMessage ?: "Unknown error"
                _isLoading.value = false
            }
        }
    }

}