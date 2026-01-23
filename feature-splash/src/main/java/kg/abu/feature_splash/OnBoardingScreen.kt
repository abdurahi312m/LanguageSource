package kg.abu.feature_splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import kg.abu.core.navigation.Routes

@Composable
fun OnBoardingScreen(
    navController: NavController
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Обучающий экран")

        Button(
            onClick = { navController.navigate(Routes.AUTH) {
                popUpTo(Routes.ON_BOARDING) { inclusive = true }
            }
            }
        ) {
            Text("Далее")
        }
    }

}