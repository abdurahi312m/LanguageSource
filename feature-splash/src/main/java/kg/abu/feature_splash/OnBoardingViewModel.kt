package kg.abu.feature_splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kg.abu.core.AppPreferences
import kotlinx.coroutines.launch

class OnBoardingViewModel(
    private val appPreferences: AppPreferences
): ViewModel() {

    fun completeOnBoarding() {
        viewModelScope.launch {
            appPreferences.saveOnBoardingCompleted()
        }
    }

}