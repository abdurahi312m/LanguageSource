package kg.abu.feature_training

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val trainingModule = module {
    viewModel { TrainingViewModel(get()) }
    viewModel { GrammarViewModel() }
    viewModel { AnalyticsViewModel() }
}