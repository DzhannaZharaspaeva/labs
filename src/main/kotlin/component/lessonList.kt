package component

import data.Job
import react.*
import react.dom.*
import react.router.dom.*

interface LessonListProps : RProps{
    var lessons: Array<Job>
}

val fLessonList =
    functionalComponent<LessonListProps> {
        h2 { +"Lessons" }
        ul {
            it.lessons.mapIndexed { index, lesson ->
                li {
                    navLink("/lessons/$index") { +lesson.name }
                }
            }
        }
    }

fun RBuilder.lessonList(lessons: Array<Job>) =
    child(fLessonList) { attrs.lessons = lessons }