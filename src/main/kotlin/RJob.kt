import data.Student
import data.studentList
import org.w3c.dom.events.Event
import react.*
import react.dom.h1
import react.dom.ol

interface RJobProps : RProps {
    var job: String
    var studs: Array<Student>
}
interface RStudentListState : RState {
    var present: Array<Boolean>
}
class RJobList : RComponent<RJobProps, RStudentListState>() {
    override fun componentWillMount() {
        state.apply {
            present = Array(props.studs.size){false}
        }
    }
    override fun RBuilder.render() {
               h1 {
                   +props.job
                }
                ol {
                    rList(props.studs, state.present, byIndex())
                }
            }

    fun byIndex(): (Int) -> (Event) -> Unit = this::onClick
    fun onClick(index: Int): (Event) -> Unit = {
        setState {
            present[index] = !present[index]
        }
    }
}
fun RBuilder.Job() =
    child(RJobList::class) {
        attrs.job = "Chemistry"
        attrs.studs = studentList.toTypedArray()
    }
