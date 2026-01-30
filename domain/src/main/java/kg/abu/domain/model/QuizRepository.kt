package kg.abu.domain.model

object QuizRepository {
    fun getQuestionsByCategory(categoryId: String): List<QuizQuestion> {
        return when (categoryId) {
            "noun" -> nounQuestions
            "adjective" -> adjectiveQuestions
            "numeral" -> numeralQuestions
            "pronoun" -> pronounQuestions
            "verb" -> verbQuestions
            else -> emptyList()
        }
    }

    private val nounQuestions = listOf(
        QuizQuestion(1, "Кайсы сөз зат атооч?", listOf("чуркоо", "китеп", "тез", "окуйт"), 1),
        QuizQuestion(2, "Зат атооч эмнени билдирет?", listOf("аракетти", "белгини", "предметти", "санды"), 2),
        QuizQuestion(3, "Кайсы сөз жекелик санда?", listOf("үйлөр", "балдар", "китеп", "аттар"), 2),
        QuizQuestion(4, "Кайсы сөз туура жазылган?", listOf("китеплер", "китептер", "китеплар", "китептар"), 1),
        QuizQuestion(5, "«Адам» сөзү кайсы суроого жооп берет?", listOf("кандай?", "эмне кылды?", "ким?", "канча?"), 2)
    )

    private val adjectiveQuestions = listOf(
        QuizQuestion(1, "Кайсы сөз сын атооч?", listOf("кооз", "окуу", "чурка", "китеп"), 0),
        QuizQuestion(2, "Сын атооч эмнени билдирет?", listOf("предмет", "сан", "белги", "аракет"), 2),
        QuizQuestion(3, "«Узун жол» дегенде сын атооч кайсы?", listOf("узун", "жол", "узун жол", "жок"), 0),
        QuizQuestion(4, "Кайсы сөз сын атооч эмес?", listOf("ак", "чоң", "китеп", "жаңы"), 2),
        QuizQuestion(5, "Сын атооч кайсы суроого жооп берет?", listOf("ким?", "эмне?", "кандай?", "канча?"), 2)
    )

    private val numeralQuestions = listOf(
        QuizQuestion(1, "Кайсы сөз сан атооч?", listOf("үч", "көп", "баары", "тез"), 0),
        QuizQuestion(2, "Сан атооч эмнени билдирет?", listOf("аракет", "сан", "белги", "предмет"), 1),
        QuizQuestion(3, "Кайсы сөз иреттик сан?", listOf("беш", "он", "үчүнчү", "көп"), 2),
        QuizQuestion(4, "«Жети бала» дегенде сан атооч кайсы?", listOf("бала", "жети", "жети бала", "жок"), 1),
        QuizQuestion(5, "Кайсы суроого жооп берет?", listOf("кандай?", "ким?", "канча?", "эмне кылды?"), 2)
    )

    private val pronounQuestions = listOf(
        QuizQuestion(1, "Кайсы сөз ат атооч?", listOf("мен", "китеп", "окуу", "жакшы"), 0),
        QuizQuestion(2, "Ат атооч эмнени алмаштырат?", listOf("санды", "белгини", "затты", "аракетти"), 2),
        QuizQuestion(3, "Кайсы сөз жекелик ат атооч?", listOf("биз", "алар", "мен", "силер"), 2),
        QuizQuestion(4, "«Ал келди» дегенде ат атооч кайсы?", listOf("келди", "ал", "ал келди", "жок"), 1),
        QuizQuestion(5, "Ат атооч кайсы суроого жооп берет?", listOf("ким? / эмне?", "кандай?", "канча?", "кайда?"), 0)
    )

    private val verbQuestions = listOf(
        QuizQuestion(1, "Кайсы сөз этиш?", listOf("окуйт", "окуу", "китеп", "жакшы"), 0),
        QuizQuestion(2, "Этиш эмнени билдирет?", listOf("белги", "предмет", "аракет", "сан"), 2),
        QuizQuestion(3, "Кайсы сөз өткөн чакта?", listOf("окуйт", "окуган", "окуй", "окуйт эле"), 1),
        QuizQuestion(4, "«Мен жаздым» — этиш кайсы?", listOf("мен", "жаздым", "мен жаздым", "жок"), 1),
        QuizQuestion(5, "Этиш кайсы суроого жооп берет?", listOf("кандай?", "ким?", "эмне кылды?", "канча?"), 2)
    )
}