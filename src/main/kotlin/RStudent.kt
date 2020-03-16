import data.Student
import react.*
import react.dom.li

interface RStudentProps : RProps {
    var student: Student
}
interface RStudentListProps : RProps {
    var students: Array<Student>
}
fun RBuilder.rstudent(student: Student) =
    child(
        functionalComponent<RStudentProps> {
            +"${it.student.firstname} ${it.student.surname}"
        }
    ){
        attrs.student = student
    }

fun RBuilder.rList(students: Array<Student>) =
    child(functionalComponent<RStudentListProps> { props ->
        props.students.forEach {student ->
            li {
                rstudent(student)
            }
        }
    }){
        attrs.students = students
    }