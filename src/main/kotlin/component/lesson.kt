package component

import react.*
import react.dom.*
import kotlinx.html.js.onClickFunction
import org.w3c.dom.events.Event
import data.*

interface JobProps : RProps {
    var job: Job
    var present: Boolean
    var onClick: (Event)->Unit
}

val fLesson =
    functionalComponent<JobProps> {
        span (
            if(it.present) "present" else "absent"
        ){
            +it.job.name
            attrs.onClickFunction = it.onClick
        }
    }

fun RBuilder.lesson(
    job: Job,
    present: Boolean,
    onClick: (Event)->Unit
) = child(fLesson) {
        attrs.job = job
        attrs.present = present
        attrs.onClick = onClick
    }