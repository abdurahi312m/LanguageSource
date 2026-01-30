package kg.abu.feature_library

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ReaderViewModel : ViewModel() {

    private val _bookContent = MutableStateFlow(
        """
        Бул окуя илгери өткөн заманда болгон экен. Анда кыргыз эли Ала-Тоонун этегинде, шар аккан суунун боюнда жайланышып, бейпил турмуш кечирип жаткан убак. 
        
        Күндөрдүн биринде, айылдын четиндеги дөбөгө жаш баатыр чыгып, алыс жакты дүрбү менен карап турат. Анын оюнда элин коргоо, жерин сактоо максаты бар эле. 
        
        Тоолордун бийик чокуларына кар түшүп, аппак болуп кулпурат. Аба таза, жел салкын.
        """.trimIndent()
    )
    val bookContent = _bookContent.asStateFlow()

}