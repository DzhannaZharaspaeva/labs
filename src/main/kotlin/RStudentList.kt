import data.Student
import org.w3c.dom.events.Event
import react.*
import react.dom.li

interface RStudentListProps : RProps {
    var students: Array<Student>
}
fun RBuilder.rList(students: Array<Student>, state:  Array<Boolean>, onClick: (Int) -> (Event) -> Unit) =
    child(functionalComponent<RStudentListProps> { props ->
        props.students.forEachIndexed { index, student ->
            li {
                rstudent(student, state[index], onClick(index))
            }
        }
    }){
        attrs.students = students
    }

