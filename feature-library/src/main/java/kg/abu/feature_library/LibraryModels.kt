package kg.abu.feature_library

import androidx.compose.ui.graphics.Color

enum class LiteraryGenre(val title: String, val color: Color) {
    DRAMA("Драма", Color(0xFFE1BEE7)),
    POEM("Поэма", Color(0xFFC5CAE9)),
    NOVEL("Роман", Color(0xFFB2DFDB)),
    STORY("Аңгеме", Color(0xFFFFCCBC)),
    SCHOOL("Мектеп", Color(0xFFF0F4C3)),
    ORT("ЖРТ/ОРТ", Color(0xFFFFE082))
}

data class Book(
    val id: String,
    val title: String,
    val author: String,
    val genre: LiteraryGenre,
    val description: String,
    val grade: Int? = null,
    val progress: Float = 0f, // 0.0 - 1.0
    val coverUrl: String? = null,
    val isAudioAvailable: Boolean = false
)