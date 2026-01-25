package kg.abu.feature_auth

import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {

    single { FirebaseAuth.getInstance() }

    single<AuthRepository> { AuthRepositoryImpl(get()) }

    viewModel { AuthViewModel(repository = get()) }
}