package kg.abu.domain.repository

interface AuthRepository {
    suspend fun signInAnonymously(): Result<Boolean>

    suspend fun signInWithGoogle(idToken: String): Result<Boolean>

    fun isUserLoggedIn(): Boolean

    fun getUserId(): String?

    fun getUserEmail(): String?

    suspend fun signOut()
}