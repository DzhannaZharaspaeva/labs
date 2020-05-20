package component

import data.Student
import react.*
import react.dom.li

interface studentnameProps : RProps {
    var student: Student }

val fstudentname =
    functionalComponent<studentnameProps> { props ->
        li{
            +"${props.student.firstname} ${props.student.surname}"
        }
    }

fun RBuilder.studentname(
    student: Student
) = child(fstudentname) {
    attrs.student = student
}