package kg.abu.languagesource

import android.app.Application
import kg.abu.core.networkModule
import kg.abu.feature_auth.authModule
import kg.abu.feature_language.languageModule
import kg.abu.feature_library.libraryModule
import kg.abu.feature_literature.literatureModule
import kg.abu.feature_profile.profileModule
import kg.abu.feature_splash.splashModule
import kg.abu.feature_training.trainingModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class LanguageSourceApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@LanguageSourceApp)

            modules(
                networkModule,
                authModule,
                profileModule,
                libraryModule,
                literatureModule,
                trainingModule,
                languageModule,
                splashModule,
            )
        }
    }
}