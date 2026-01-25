package kg.abu.feature_splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kg.abu.core.AppPreferences
import kg.abu.core.navigation.Routes
import kg.abu.feature_auth.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SplashViewModel(
    private val appPreferences: AppPreferences,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _startDestination = MutableStateFlow<String?>(null)
    val startDestination: StateFlow<String?> = _startDestination.asStateFlow()

    init {
        checkOnBoarding()
    }

    private fun checkOnBoarding() {
        viewModelScope.launch {
            delay(2000)

            val isCompleted = appPreferences.isOnBoardingCompleted.first()

            if (!isCompleted) {
                _startDestination.value = Routes.ON_BOARDING
            } else {
                if (authRepository.isUserLoggedIn()) {
                    _startDestination.value = Routes.LANGUAGE
                } else {
                    _startDestination.value = Routes.AUTH
                }
            }
        }
    }
}