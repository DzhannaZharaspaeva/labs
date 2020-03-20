# Сдача 5 лабораторной работы
1. В данной лабораторной работе необходимо было RStudentList преобразовать в функциональный компонент.

```
fun RBuilder.rList(students: Array<Student>, state:  Array<Boolean>, onClick: (Int) -> (Event) -> Unit) =
    child(functionalComponent<RStudentListProps> { props ->
        props.students.forEachIndexed { index, student ->
            li {
                rstudent(student, state[index], onClick(index))
            }
        }
    }){
        attrs.students = students
    }
```
2.Далее необходимо было переделать приложение, реализовав компонент «Занятие». Этот компонент отображает название занятия и список студентов, которые должны на нем присутствовать.<br>
![тут должен быть код](https://sun4-12.userapi.com/AmG60rUXVYG-Hc-qI8hRZeizrFv-u6nwtgHBFw/9Ol5-eZ8iu0.jpg)
3.Также необходимо было  поднять состояние компонента RStudentList в созданный компонент <br>
![код](https://sun4-16.userapi.com/o4yWEltlhM3tcP5sD7_nyYH7INENmRi3_y_KGQ/JXKNfky7ouQ.jpg)
4. Результат работы программы продемонстрирован на следующих изображениях: <br>
![коод](https://sun4-16.userapi.com/tR7wrCGEVtejesm2eaXDv_U3ObdI4x-_ArckKg/M0NmdhdcviE.jpg)
![кд](https://sun4-15.userapi.com/bb2_-1N5AFF-AI3tsjwNGF9DDN7gi8-M_JqceQ/1u9mMwzeNes.jpg)

