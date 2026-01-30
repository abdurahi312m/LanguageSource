package kg.abu.feature_library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class LibraryViewModel : ViewModel() {

    private val _allBooks = MutableStateFlow(mockBooks)

    private val _searchQuery = MutableStateFlow("")
    private val _selectedGenre = MutableStateFlow<LiteraryGenre?>(null)

    val filteredBooks: StateFlow<List<Book>> = combine(
        _allBooks,
        _searchQuery,
        _selectedGenre
    ) { books, query, genre ->
        books.filter { book ->
            val matchesSearch = book.title.contains(query, ignoreCase = true) ||
                    book.author.contains(query, ignoreCase = true)
            val matchesGenre = genre == null || book.genre == genre

            matchesSearch && matchesGenre
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val searchQuery = _searchQuery.asStateFlow()
    val selectedGenre = _selectedGenre.asStateFlow()

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun onGenreSelected(genre: LiteraryGenre?) {
        if (_selectedGenre.value == genre) {
            _selectedGenre.value = null
        } else {
            _selectedGenre.value = genre
        }
    }
}

private val mockBooks = listOf(
    Book("1", "Жамиля", "Чыңгыз Айтматов", LiteraryGenre.NOVEL,
        "Сүйүү баяны", 9, 0.45f, isAudioAvailable = true),
    Book("2", "Биринчи мугалим", "Чыңгыз Айтматов", LiteraryGenre.NOVEL,
        "Дүйшөн жана Алтынай", 7, 0.1f),
    Book("3", "Эрте келген турналар", "Чыңгыз Айтматов", LiteraryGenre.NOVEL,
        "Согуш мезгили", 8, 0.0f),
    Book("4", "Манас (үзүндү)", "Саякбай Каралаев", LiteraryGenre.POEM,
        "Эпос", 5, 0.8f, isAudioAvailable = true),
    Book("5", "Сынган кылыч", "Төлөгөн Касымбеков", LiteraryGenre.NOVEL,
        "Тарыхый роман", 10, 0.2f),
    Book("6", "Кыямат", "Чыңгыз Айтматов", LiteraryGenre.NOVEL,
        "Философиялык роман", 11, 0.0f),
    Book("7", "Атанын тагдыры", "Б. Жакиев", LiteraryGenre.DRAMA,
        "Пьеса", 9, 0.0f)
)