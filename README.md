# Сдача 8 лабораторной работы
# Задание: доработайте приложение из видеоуроков. Разработайте компоненты, отвечающий за редактирование названия занятия и имени и фамилии студента. Разработайте компонент, отвечающий за редактирование списка элементов (с возможностью добавить или удалить элемент). В качестве аргументов этому компоненту передаются компоненты для отображения и для редактирования элемента списка. Добавьте в приложение страницы для редактирования списка студентов и списка занятий.
## Ход работы:
1. Избавилась от компонентов с пустыми свойствами,вместо этого создала в app.kt функцию renderEditStudents (), в которой реализованы два инпута для ввода текста, для которых id обозначен как AddSurname и AddFirstname для имени и фамилии соответственно
```
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
```
2. Также создала в том же файле функцию renderEditJob() для редактирования списка занятий, здесь реализован инпут для ввода текста, для которого id обозначен как AddJob для названия занятия
```
 fun renderEditJob() : RBuilder.() -> ReactElement {
        return {
            input(InputType.text) {
                attrs.placeholder = "Введите название предмета"
                attrs.id = "AddJob"
            }
        }
    }
```
3. Создала общий компонент для редактирования списка элементов, а именно были реализованы кнопки, с помощью которых можно либо удалять, либо добавлять информацию о новых занятиях и студентах
```
fun <O> fanyEdit(
        rEdit : RBuilder.() -> ReactElement,
    rComponent: RBuilder.(Array<O>, String, String,(Int) -> Unit) -> ReactElement
) =
    functionalComponent<AnyEditProps<O>> { props ->
        h3 { +"Redact" }
        rEdit()
        ul {
            button {
                +"Add"
                attrs.onClickFunction = props.Add
            }
            rComponent(props.subObjs, props.name, props.path,props.del)
        }
    }

```
4. Далее был также доработан app, в нем были реализованы отдельные функции для добавления и удаления как студентов, так и предметов, здесь также были разработаны функции добавления  на основе функции добавления предмета из 6 лабораторной работы. Ниже на скриншоте представлены функции добавления и удаления студентов: <br>
![тут долсм вжен быть код](https://sun9-2.userapi.com/3ZD6Ub57pvlQmGA3GDKy0rz21j077GkpI1NucQ/s62t99--IVY.jpg)
5. Выполнение добавления и удаления студента видно на следующих скриншотах <br>
   1. Добавление студента <br>
![тут должен быть код](https://sun9-34.userapi.com/yiseAFEvwx-u2kN4_zjjKPTnEXSNyV4tQry3bA/pN_Px6LRRGY.jpg)
   2. Удаление студентов <br>
![тут должен быть](https://sun4-10.userapi.com/233u_t1dnO8yCLQeHgY6NIhdrX532P-rRYk5nA/Ja5pWfMPcMA.jpg) 
6. Выполнение добавления и удаления предмета <br>
   1. Добавление предмета <br>
![тут должен](https://sun4-17.userapi.com/dfNyTkIhzTuhhrBlWXfn0737MhuKlotliwfFrw/ps7iPzzJQ_k.jpg) 
   2. Удаление предметов <br>
![код](https://sun4-15.userapi.com/4-6fwyiW7gx0EgzGz-2uZaPcnd1TAUl34ayfZQ/pmTpQ0ui7hc.jpg)

