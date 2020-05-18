package data

data class Job(
    val name: String
){
    override fun toString(): String = name
}

val jobList = arrayOf(
    Job("Lecture"),
    Job("Practice"),
    Job("Exam")
)