package kg.abu.core

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.datastore by preferencesDataStore("app_preferences")

class AppPreferences(private val context: Context) {

    companion object {
        private val KEY_ONBOARDING_COMPLETED = booleanPreferencesKey("onboarding_completed")
        private val KEY_IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
        private val KEY_IS_TEACHER = booleanPreferencesKey("is_teacher")
    }

    val isTeacher: Flow<Boolean> = context.datastore.data
        .map { preferences ->
            preferences[KEY_IS_TEACHER] ?: false
        }

    suspend fun saveUserRole(isTeacher: Boolean) {
        context.datastore.edit { preferences ->
            preferences[KEY_IS_TEACHER] = isTeacher
        }
    }

    val isOnBoardingCompleted: Flow<Boolean> = context.datastore.data
        .map { preferences ->
            preferences[KEY_ONBOARDING_COMPLETED] ?: false
        }

    val isLoggedIn: Flow<Boolean> = context.datastore.data
        .map { preferences ->
            preferences[KEY_IS_LOGGED_IN] ?: false
        }

    suspend fun saveOnBoardingCompleted() {
        context.datastore.edit { preferences ->
            preferences[KEY_ONBOARDING_COMPLETED] = true
        }
    }

    suspend fun saveLoginSuccess() {
        context.datastore.edit { preferences ->
            preferences[KEY_IS_LOGGED_IN] = true
        }
    }


}