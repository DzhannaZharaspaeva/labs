package component

import data.*
import hoc.withDisplayName
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import react.*
import react.dom.*
import react.router.dom.*
import kotlin.browser.document

interface AppProps : RProps {}

interface AppState : RState {
    var jobs: Array<Job>
    var students: Array<Student>
    var presents: Array<Array<Boolean>>
}

interface RouteNumberResult : RProps {
    var number: String
}

class App : RComponent<AppProps, AppState>() {
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
                    anyList(state.jobs, "Jobs", "/jobs")
                }
            )
            route("/students",
                exact = true,
                render = {
                    anyList(state.students, "Students", "/students")
                }
            )
            route("/studentredact",
                exact = true,
                render = {
                    anyEdit(
                        RBuilder::studentname,
                        RBuilder::studentredact,
                        RBuilder::anyList,
                        state.students,
                        addStudent(),
                        "Students",
                        "/students",
                        { deleteStudent(it) }
                    )
                })
            route("/jobredact",
                exact = true,
                render = {
                    anyEdit(
                        RBuilder::jobname,
                        RBuilder::jobredact,
                        RBuilder::anyList,
                        state.jobs,
                        addJob(),
                        "Jobs",
                        "/jobs",
                        { deleteJob(it) }
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

    fun addJob(): (Event) -> Unit {
        return { _: Event ->
            val addJ = document.getElementById("AddJob") as HTMLInputElement
            setState {
                jobs += Job(addJ.value)
                presents += arrayOf(
                    Array(state.students.size) { false })
            }
        }
    }

    fun addStudent(): (Event) -> Unit {
        return { _: Event ->
            val addSF = document.getElementById("AddFirstname") as HTMLInputElement
            val addSS = document.getElementById("AddSurname") as HTMLInputElement
            setState {
                students += Student(addSF.value, addSS.value)
                presents.mapIndexed { index, _ ->
                    presents[index] += arrayOf(false)
                }
            }
        }
    }

    fun deleteStudent(index: Int): (Event) -> Unit {
        return { _: Event ->
            var deleteStud = state.students.toMutableList().apply { removeAt(index) }.toTypedArray()
            var newpresents = state.presents.mapIndexed { id, _ ->
                state.presents[id].toMutableList().apply {
                    removeAt(index)
                }.toTypedArray()
            }
            setState {
                students = deleteStud
                presents = newpresents.toTypedArray()
            }
        }
    }

    fun deleteJob(index: Int): (Event) -> Unit {
        return { _: Event ->
            var deleteJ = state.jobs.toMutableList().apply { removeAt(index) }.toTypedArray()
            var newpresents = state.presents.toMutableList().apply { removeAt(index) }.toTypedArray()
            setState {
                jobs = deleteJ
                presents = newpresents
            }
        }
    }

}

fun RBuilder.app(
) =
    child(withDisplayName("AppHoc", App::class)) {}





