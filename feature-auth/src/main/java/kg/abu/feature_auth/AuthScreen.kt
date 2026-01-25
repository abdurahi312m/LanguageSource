package kg.abu.feature_auth

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import kg.abu.core.navigation.Routes
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthScreen(
    navController: NavController,
    viewModel: AuthViewModel = koinViewModel()
) {
    val context = LocalContext.current

    val isLoading by viewModel.isLoading.collectAsState()
    val authSuccess by viewModel.authSuccess.collectAsState()
    val error by viewModel.error.collectAsState()

    val gso = remember {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(("76585342443-00jtcj5d75fdr7feo2cf3i29r02431uf.apps.googleusercontent.com"))
            .requestEmail()
            .build()
    }

    val googleSignInClient = remember {
        GoogleSignIn.getClient(context, gso)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)
                account.idToken?.let { token ->
                    viewModel.onGoogleSignIn(token)
                }
            } catch (e: ApiException) {
                Toast.makeText(
                    context,
                    "Google error: ${e.statusCode}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    LaunchedEffect(authSuccess) {
        if (authSuccess) {
            navController.popBackStack()
            navController.navigate(Routes.LANGUAGE)
        }
    }

    LaunchedEffect(error) {
        error?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Авторизация", style = MaterialTheme.typography.headlineMedium)

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = {
                        launcher.launch(googleSignInClient.signInIntent)
                    }
                ) {
                    Text(text = "Войти через Google")
                }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedButton(onClick = { viewModel.onLoginClick() }) {
                    Text(text = "Войти анонимно")
                }
            }
        }
    }
}