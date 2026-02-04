package kg.abu.feature_profile

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val profileModule = module {
    viewModel {
        ProfileViewModel(appPreferences = get(), authRepository = get())
    }
}