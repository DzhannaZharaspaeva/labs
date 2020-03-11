package data

data class Student (
    val firstname: String,
    val surname: String,
    var state:Boolean
)

val studentList =
    arrayListOf(
        Student("Sheldon", "Cooper",true),
        Student("Leonard", "Hofstadter",true),
        Student("Howard", "Wolowitz",true),
        Student("Dzhanna","Zharaspaeva",true) ,
        Student("Martin","Garrix",true)
    )