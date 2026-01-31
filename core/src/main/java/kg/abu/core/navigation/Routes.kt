package kg.abu.core.navigation

object Routes {

    const val SPLASH = "splash"
    const val ON_BOARDING = "on_boarding"

    const val AUTH = "auth"
    const val LANGUAGE = "language"
    const val LIBRARY = "library"
    const val PROFILE = "profile"

    const val READER = "reader/{bookId}"

    fun getReaderRoute(bookId: String) = "reader/$bookId"

    const val TRAINING_LEVELS = "training/levels/{topicId}"

    fun getTrainingLevelsRoute(topicId: String) = "training/levels/$topicId"

    const val TRAINING_GRAMMAR = "training/grammar"
    const val TRAINING_ORT = "training/ort"
    const val TRAINING_ANALYTICS = "training/analytics"
    const val TRAINING_CREATE = "training/create"
    const val TRAINING = "training_graph"
    const val TRAINING_HOME = "training_home"

}