package kg.abu.languagesource.ui


import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Person

@Composable
fun BottomBar(
    currentRoute: String?,
    onLanguage: () -> Unit,
    onLibrary: () -> Unit,
    onLiterature: () -> Unit,
    onTraining: () -> Unit,
    onProfile: () -> Unit,
    onAuth: () -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            selected = currentRoute == "Language",
            onClick = onLanguage,
            label = { Text("Язык") },
            icon = { Icon(Icons.Filled.Menu, null) }
        )

        NavigationBarItem(
            selected = currentRoute == "library",
            onClick = onLibrary,
            label = { Text("Библиотека") },
            icon = { Icon(Icons.Filled.Menu, null) }
        )

        NavigationBarItem(
            selected = currentRoute == "literature",
            onClick = onLiterature,
            label = { Text("Литература") },
            icon = { Icon(Icons.Filled.Book, null) }
        )

        NavigationBarItem(
            selected = currentRoute == "training",
            onClick = onTraining,
            label = { Text("Тренировка") },
            icon = { Icon(Icons.Filled.School, null) }
        )

        NavigationBarItem(
            selected = currentRoute == "profile",
            onClick = onProfile,
            label = { Text("Профиль") },
            icon = { Icon(Icons.Filled.Person, null) }
        )
    }
}