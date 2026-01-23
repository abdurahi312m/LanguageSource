package kg.abu.feature_auth

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import kg.abu.core.navigation.Routes
import kg.abu.data.AppPreferences
import kotlinx.coroutines.launch

@Composable
fun AuthScreen(
    navController: NavController,
    context: Context
) {
    val appPreferences = remember { AppPreferences(context) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Авторизация")

        Button(
            onClick = {
                scope.launch {
                    appPreferences.saveOnBoardingCompleted()
                    appPreferences.saveLoginSuccess()
                navController.navigate(Routes.LANGUAGE) {
                    popUpTo(Routes.AUTH) }
            }}
        ) {
            Text("Войти")
        }
    }

}