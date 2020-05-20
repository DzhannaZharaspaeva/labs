package component

import kotlinx.html.InputType.text
import kotlinx.html.id
import react.*
import react.dom.input


val fjobredact =
    functionalComponent<RProps> { props ->
        input(text)  {
            attrs.placeholder = "Введите название предмета"
            attrs.id ="AddJob"
        }
    }

fun RBuilder.jobredact(
) = child(fjobredact) {
}