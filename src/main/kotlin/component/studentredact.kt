package component

import kotlinx.html.InputType.text
import kotlinx.html.id
import react.*
import react.dom.input

val fstudentredact =
    functionalComponent<RProps> { props ->
        input(text) {
            attrs.placeholder = "Введите Имя студента"
            attrs.id ="AddFirstname"
        }
        input(text) {
            attrs.placeholder = "Введите Фамилию студента"
            attrs.id ="AddSurname"
        }
    }

fun RBuilder.studentredact(
) = child(fstudentredact) {
}