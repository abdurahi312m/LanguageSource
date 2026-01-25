package kg.abu.feature_auth

interface AuthRepository {
    suspend fun signInAnonymously(): Result<Boolean>

    suspend fun signInWithGoogle(idToken: String): Result<Boolean>

    fun isUserLoggedIn(): Boolean

    fun getUserId(): String?
}