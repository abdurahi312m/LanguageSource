package kg.abu.core.model

import kotlinx.serialization.Serializable

sealed interface Screen {

    @Serializable
    data object Library : Screen

    @Serializable
    data class BookDetails(val id: Int) : Screen

    @Serializable
    data object Profile : Screen



}