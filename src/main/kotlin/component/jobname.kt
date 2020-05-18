package component


import data.Job
import react.*
import react.dom.li

interface jobnameProps : RProps {
    var job: Job }

val fjobname =
    functionalComponent<jobnameProps>{ props ->
        li{
            +props.job.name
        }
    }

fun RBuilder.jobname(
    job: Job
) = child(fjobname) {
    attrs.job = job
}