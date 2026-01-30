package kg.abu.domain.model

data class WordModel(
    val id: String,
    val term: String,
    val definition: String,
    val modernEquivalent: String?,
    val wordType: WordType,
    val difficultyLevel: DifficultyLevel,

    val root: String,
    val partOfSpeech: String,
    val suffixes: String = "жок",
    val forms: List<String>,

    val grammarTopic: String? = null,
    val grammarRule: String? = null,
    val caseTable: Map<String, String>? = null,

    val examples: List<String>,
    var isFavorite: Boolean = false
)
