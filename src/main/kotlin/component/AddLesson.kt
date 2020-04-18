package component

import kotlinx.html.InputType
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import react.*
import react.dom.*
import kotlinx.html.id
import kotlin.browser.document

interface AppLessonProps : RProps {
    var click: (String) -> Unit
}

val fAddJob =functionalComponent<AppLessonProps>() {
   props ->
        div {
            h3 {
                +"Введите название предмета"
            }
            input(InputType.text) {
                attrs {
                    id = "job"
                }
            }
            button { +"Добавить"
                attrs.onClickFunction= {
                    val namejob = document.getElementById("job") as HTMLInputElement
                props.click(namejob.value)
                }
            }
        }
    }

fun RBuilder.applesson(
    click: (String) -> Unit
) = this.child(fAddJob){
    attrs.click= click
}