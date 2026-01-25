package kg.abu.feature_splash

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashModule = module {

    viewModel { SplashViewModel(appPreferences = get(), authRepository = get()) }

    viewModel { OnBoardingViewModel(appPreferences = get()) }

}