package kg.abu.languagesource

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import kg.abu.languagesource.navigation.RootNavGraph
import kg.abu.languagesource.ui.theme.LanguageSourceTheme
    
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LanguageSourceTheme {
                RootNavGraph()
            }
        }
    }
}
