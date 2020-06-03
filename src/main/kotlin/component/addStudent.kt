package component

import hoc.withDisplayName
import kotlinx.html.InputType.text
import kotlinx.html.id
import kotlinx.html.js.onClickFunction
import org.w3c.dom.events.Event
import react.RBuilder
import react.RProps
import react.child
import react.dom.*
import react.functionalComponent

interface AddStudentProps : RProps {
    var onClick: (Event) -> Unit
}
val fAddStudent =
        functionalComponent<AddStudentProps> {
            h3 { +"Введите имя и фамилию студента" }
            div {
                li {
                    input(type = text) {
                        attrs {
                            id = "name"
                            placeholder = "Имя" } }
                    input(type = text) {
                        attrs {
                            id = "surname"
                            placeholder = "Фамилия" } } }
                button {
                    +"Добавить"
                    attrs.onClickFunction = it.onClick } } }

fun RBuilder.addStudent(
        onClick: (Event) -> Unit
) = child(
        withDisplayName("studentsAdd", fAddStudent)
) {
    attrs.onClick = onClick
}