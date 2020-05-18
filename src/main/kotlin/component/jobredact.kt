package component

import kotlinx.html.InputType.text
import kotlinx.html.id
import react.*
import react.dom.input

interface jobredactProps : RProps {}

val fjobredact =
    functionalComponent<jobredactProps> { props ->
        input(text)  {
            attrs.placeholder = "Введите название предмета"
            attrs.id ="AddJob"
        }
    }

fun RBuilder.jobredact(
) = child(fjobredact) {
}