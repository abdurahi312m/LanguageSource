package kg.abu.feature_language

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val languageModule = module {
    viewModel { LanguageViewModel() }
}