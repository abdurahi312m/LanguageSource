package kg.abu.core

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single {
        AppPreferences(context = get())
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://your-real-backend-api.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
