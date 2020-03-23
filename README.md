# Сдача 5 лабораторной работы
1. В данной лабораторной работе необходимо было RStudentList преобразовать в функциональный компонент.

```
interface RStudentListProps : RProps {
    var students: Array<Student>
}
fun RBuilder.RList(students: Array<Student>, state:  Array<Boolean>, onClick: (Int) -> (Event) -> Unit) =
    child(functionalComponent<RStudentListProps> { props ->
        props.students.forEachIndexed { index, student ->
            li {
                RStudent(student, state[index], onClick(index))
            }
        }
    }){
        attrs.students = students
    }
    }
```
2.Далее необходимо было переделать приложение, реализовав компонент «Занятие». Этот компонент отображает название занятия и список студентов, которые должны на нем присутствовать.<br>
![тут должен быть код](https://sun4-17.userapi.com/FsG3i8BkS-hBq0at2k-v9Y9lMCg_YbYfuJ9lGw/DbkrXEdUhz0.jpg) <br>
3.Также необходимо было  поднять состояние компонента RStudentList в созданный компонент 
![код](https://sun4-10.userapi.com/u9B-PMwOog1LibT8z7MBbO-_9uPpoPaWIZAXSQ/Vck71hfTToo.jpg) <br>
4. Результат работы программы продемонстрирован на следующих изображениях: 
![коод](https://sun4-16.userapi.com/qAYQyKd7GjD50l9fZf6FHZNmOsTtx-l1E7jmpA/fVb8HX9i7U8.jpg)
![кд](https://sun4-16.userapi.com/aENpRpAf-4Ni6Af5DEjO7ojWaz_pg58RB20X6A/aAEEmcC36ec.jpg)

