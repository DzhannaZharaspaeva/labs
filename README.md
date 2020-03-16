# Сдача 4 лабораторной работы
1. В данной лабораторной работе реализовала функциональный компонент «Список студентов»

```
fun RBuilder.rList(students: Array<Student>) =
    child(functionalComponent<RStudentListProps> { props ->
        props.students.forEach {student ->
            li {
                rstudent(student)
            }
        }
    }){
        attrs.students = students
    }
```
2. В main передаем с помощью функции render наш функциональный компонент
![тут должен быть код](https://sun4-10.userapi.com/Nzt_uuxXsC7FmE4GmxvFodpo50wBcMhsQcrZag/70LvWmqgOho.jpg)
3. Работа программы представлена на следующем изображении <br>
![код](https://sun4-10.userapi.com/HvK0QHD4YPrObKDb4OdbVIWGuK8paqsTEDe8ng/pIoWbF_uWBA.jpg)
