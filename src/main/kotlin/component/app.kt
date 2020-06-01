package component

import data.*
import hoc.withDisplayName
import kotlinx.html.InputType
import kotlinx.html.id
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import react.*
import react.dom.*
import react.router.dom.*
import kotlin.browser.document

interface AppState : RState {
    var jobs: Array<Job>
    var students: Array<Student>
    var presents: Array<Array<Boolean>>
}

interface RouteNumberResult : RProps {
    var number: String
}

class App : RComponent<RProps, AppState>() {
    override fun componentWillMount() {
        state.jobs = jobList
        state.students = studentList
        state.presents = Array(state.jobs.size) {
            Array(state.students.size) { false }
        }

    }

    override fun RBuilder.render() {
        header {
            h1 { +"App" }
            nav {
                ul {
                    li { navLink("/jobs") { +"Jobs" } }
                    li { navLink("/students") { +"Students" } }
                    li { navLink("/studentredact") { +"Redact Student" } }
                    li { navLink("/jobredact") { +"Redact Job" } }
                }
            }
        }

        switch {
            route("/jobs",
                exact = true,
                render = {
                    anyList(state.jobs, "Jobs", "/jobs", {} )
                }
            )
            route("/students",
                exact = true,
                render = {
                    anyList(state.students, "Students", "/students", {} )
                }
            )
            route("/studentredact",
                exact = true,
                render = {
                    anyEdit(
                            renderEditStudents(),
                        RBuilder::anyList,
                        state.students,
                        addStudent(),
                        "Students",
                        "/students",
                        deleteStudent()
                    )
                })
            route("/jobredact",
                exact = true,
                render = {
                    anyEdit(
                            renderEditJob(),
                        RBuilder::anyList,
                        state.jobs,
                        addJob(),
                        "Jobs",
                        "/jobs",
                        deleteJob()
                    )
                })
            route("/jobs/:number",
                render = { route_props: RouteResultProps<RouteNumberResult> ->
                    val num = route_props.match.params.number.toIntOrNull() ?: -1
                    val lesson = state.jobs.getOrNull(num)
                    if (lesson != null)
                        anyFull(
                            RBuilder::student,
                            lesson,
                            state.students,
                            state.presents[num]
                        ) { onClick(num, it) }
                    else
                        p { +"No such lesson" }
                }
            )
            route("/students/:number",
                render = { route_props: RouteResultProps<RouteNumberResult> ->
                    val num = route_props.match.params.number.toIntOrNull() ?: -1
                    val student = state.students.getOrNull(num)
                    if (student != null)
                        anyFull(
                            RBuilder::lesson,
                            student,
                            state.jobs,
                            state.presents.map {
                                it[num]
                            }.toTypedArray()
                        ) { onClick(it, num) }
                    else
                        p { +"No such student" }
                }
            )
        }
    }

    fun onClick(indexJob: Int, indexStudent: Int): (Event) -> Unit {
        return { _: Event ->
            setState {
                presents[indexJob][indexStudent] =
                    !presents[indexJob][indexStudent]
            }
        }
    }

    fun addJob(): (Event) -> Unit =  {
            val addJ = document.getElementById("AddJob") as HTMLInputElement
            val addJob = state.presents.mapIndexed { index, _ ->
                state.presents[index].plus(arrayOf(false))
            }.toTypedArray()
            setState {
                jobs += Job(addJ.value)
                presents = addJob
            }
    }

    fun addStudent(): (Event) -> Unit = {
            val addSF = document.getElementById("AddFirstname") as HTMLInputElement
            val addSS = document.getElementById("AddSurname") as HTMLInputElement
            setState {
                students += Student(addSF.value, addSS.value)
                presents += arrayOf(Array(state.students.size){false})
            }
    }
    fun deleteStudent(): (Int) -> Unit =  {
        val redactStud = state.students.toMutableList().apply {
            removeAt(it) }
            .toTypedArray()
        val editedPresents = state.presents.toMutableList().apply {
            removeAt(it)}
            .toTypedArray()
        setState{
            students = redactStud
            presents= editedPresents
        }
    }

    fun deleteJob(): (Int)  -> Unit = {
            val deleteJ = state.jobs.toMutableList().apply { removeAt(it) }.toTypedArray()
            val newpresents =state.presents.mapIndexed { index, _ ->
                state.presents[index].toMutableList().apply {
                    removeAt(index)
                }.toTypedArray()
            }.toTypedArray()
            setState {
                jobs = deleteJ
                presents = newpresents
            }
        }

    fun renderEditStudents () : RBuilder.() -> ReactElement {
        return {
            input(InputType.text) {
                attrs.placeholder = "Введите Имя студента"
                attrs.id = "AddFirstname"
            }
            input(InputType.text) {
                attrs.placeholder = "Введите Фамилию студента"
                attrs.id = "AddSurname"
            }
        }
    }

    fun renderEditJob() : RBuilder.() -> ReactElement {
        return {
            input(InputType.text) {
                attrs.placeholder = "Введите название предмета"
                attrs.id = "AddJob"
            }
        }
    }

    }

fun RBuilder.app(
) =
    child(withDisplayName("AppHoc", App::class)) {}





