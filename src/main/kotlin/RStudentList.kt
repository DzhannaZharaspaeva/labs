import data.Student
import org.w3c.dom.events.Event
import react.*
import react.dom.li

interface RStudentListProps : RProps {
    var students: Array<Student>
}
fun RBuilder.RList(students: Array<Student>, state:  Array<Boolean>, onClick: (Int) -> (Event) -> Unit) =
    child(functionalComponent<RStudentListProps> { props ->
        props.students.forEachIndexed { index, student ->
            li {
                RStudent(student, state[index], onClick(index))
            }
        }
    }){
        attrs.students = students
    }

