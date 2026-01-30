package kg.abu.core.navigation

object Routes {

    const val SPLASH = "splash"
    const val ON_BOARDING = "on_boarding"

    const val AUTH = "auth"
    const val LANGUAGE = "language"
    const val LIBRARY = "library"
    const val TRAINING = "training"
    const val PROFILE = "profile"

    const val READER = "reader/{bookId}"

    fun getReaderRoute(bookId: String) = "reader/$bookId"

}