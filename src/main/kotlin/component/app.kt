package component


import data.*
import org.w3c.dom.events.Event
import react.*
import react.dom.h1

interface AppProps : RProps {
    var students: Array<Student>

}

interface AppState : RState {
    var job: Array<Job>
    var presents: Array<Array<Boolean>>
    var newjob: String
}

class App : RComponent<AppProps, AppState>() {
    override fun componentWillMount() {
        state.job = jobList
        state.presents = Array(state.job.size) {
            Array(props.students.size) { false }
        }

    }

    fun addJob():(String) -> Unit = { newjob ->
            setState {
                job += Job(newjob)
                presents += arrayOf(Array(props.students.size){false})
            }
        }


    override fun RBuilder.render() {
        h1 { +"App" }
        applesson(addJob())

        lessonListFull(
            state.job,
            props.students,
            state.presents,
            onClickLessonFull
        )
        studentListFull(
            state.job,
            props.students,
            transform(state.presents),
            onClickStudentFull
        )
    }

    fun transform(source: Array<Array<Boolean>>): Array<Array<Boolean>> {
        return Array(source[0].size){ row->
            Array(source.size){col ->
                source[col][row]
            }
        }
    }

    fun onClick(indexLesson: Int, indexStudent: Int): (Event) -> Unit {
        return { _: Event ->
            setState {
                presents[indexLesson][indexStudent] =
                        !presents[indexLesson][indexStudent]
            }
        }
    }

    val onClickLessonFull =
            { indexLesson: Int ->
                { indexStudent: Int ->
                    onClick(indexLesson, indexStudent)
                }
            }

    val onClickStudentFull =
            { indexStudent: Int ->
                { indexLesson: Int ->
                    onClick(indexLesson, indexStudent)
                }
            }

}



fun RBuilder.app(
    students: Array<Student>
) = child(App::class) {

    attrs.students = students
}