import kotlin.browser.document
import data.Student
import data.studentList
import kotlinx.html.*
import kotlinx.html.InputType.radio
import kotlinx.html.InputType.valueOf
import kotlinx.html.dom.append
import kotlinx.html.js.*
import kotlinx.html.js.input
import kotlinx.html.js.li
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import kotlin.browser.document
import kotlin.dom.clear

var ascending = true

fun main() {
    document.getElementById("root")!!
        .append {
            h1 {
                +"Students"
                onClickFunction = onCLickFunction()
            }
            ol {
                attributes += "id" to "listStudents"
                studentList.map {
                    li { //для каждого элемента списка
                        +"${it.firstname} ${it.surname}" //имяфамилия
                        attributes += "id" to it.firstname
                        onClickFunction = toclick(it)
                    }
                }
            }
            p{
                radioButtons()
            }
        }
}

private fun TagConsumer<HTMLElement>.radioButtons() {
    val colors = arrayOf("Green", "DeepPink", "Aqua")
    colors.forEach {
        input(radio, name = "radioButtons") {
            attributes += "value" to it
            onClickFunction = {
                val col=  document.getElementById("root")!!
                col.setAttribute("style", "color: ${value}")
            }
        }
        br()
    }
}

private fun LI.toclick(student: Student): (Event) -> Unit {
    return {
        val exisstudent = document.getElementById(student.firstname)!! //наличие студентов
        if (student.state) {
            exisstudent.setAttribute("style", "color:lavender")
            student.state = false }
        else {
            exisstudent.setAttribute("style", "color:coral")
            student.state = true

        }
    }
}


private fun H1.onCLickFunction(): (Event) -> Unit {
    return {
        val listStudents = document.getElementById("listStudents")!!
        listStudents.clear()
        listStudents.append {
            if (ascending)
                studentList.sortBy { it.firstname }
            else
                studentList.sortByDescending { it.firstname }
            ascending = !ascending
            studentList.map {
                li {
                    +"${it.firstname} ${it.surname}"
                    attributes += "style" to "color:coral"
                }
            }
        }
    }
}