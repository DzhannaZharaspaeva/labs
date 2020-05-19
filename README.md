# Сдача 8 лабораторной работы
# Задание: доработайте приложение из видеоуроков. Разработайте компоненты, отвечающий за редактирование названия занятия и имени и фамилии студента. Разработайте компонент, отвечающий за редактирование списка элементов (с возможностью добавить или удалить элемент). В качестве аргументов этому компоненту передаются компоненты для отображения и для редактирования элемента списка. Добавьте в приложение страницы для редактирования списка студентов и списка занятий.
## Ход работы:
1. Сделала компонент для редактирования списка студента, здесь реализованы два инпута для ввода текста, для которых id обозначен как AddSurname и AddFirstname для имени и фамилии соответственно
```
interface studentredactProps : RProps {}
val fstudentredact =
    functionalComponent<studentredactProps> { props ->
        input(text) {
            attrs.placeholder = "Введите Имя студента"
            attrs.id ="AddFirstname"
        }
        input(text) {
            attrs.placeholder = "Введите Фамилию студента"
            attrs.id ="AddSurname"
        }
    }
```
2. Сделала компонент для редактирования списка занятий, здесь реализован инпут для ввода текста, для которого id обозначен как AddJob для названия занятия
```
interface jobredactProps : RProps {}
val fjobredact =
    functionalComponent<jobredactProps> { props ->
        input(text)  {
            attrs.placeholder = "Введите название предмета"
            attrs.id ="AddJob"
        }
    }
```
3. Доработала компонент для редактирования списка элементов, а именно были реализованы баттоны, с помощью которых можно либо удалять, либо добавлять информацию о новых занятиях и студентах
```
fun <O> fanyEdit(
    rComponenentname: RBuilder.(O) -> ReactElement,
    rComponenentEdit: RBuilder.() -> ReactElement,
    rComponent: RBuilder.(Array<O>, String, String) -> ReactElement
) =
    functionalComponent<AnyEditProps<O>> { props ->
        h3 { +"Redact" }
        ul {
            rComponenentEdit()
            button {
                +"Add"
                attrs.onClickFunction = props.Add
            }
            props.subObjs.mapIndexed { index, element ->
                li {
                    rComponenentname(element)
                    button {
                        +"Delete"
                        attrs.onClickFunction = props.Delete(index)
                    }
                }
            }
            rComponent(props.subObjs, props.name, props.path)
        }
    }
```
4. Далее был доработан app, в нем были реализованы отдельные функции для добавления и удаления как студентов, так и предметов, здесь также были разработаны функции добавления  на основе функции добавления предмета из 6 лабораторной работы. Ниже на скриншоте представлены функции добавления и удаления студентов: <br>
![тут долсм вжен быть код](https://sun4-12.userapi.com/_tMB_oXbOVGTcc6L0vFU0TmSWMCvJl74aKAARg/1U4_r2aS2ok.jpg)
5. Выполнение добавления и удаления студента видно на следующих скриншотах <br>
   1. Добавление студента <br>
![тут должен быть код](https://sun4-12.userapi.com/tUTClnG9mynbeaPTdrtv2qbTZ1eYaj_TQjLdvw/sSZBFBb9NyU.jpg)
   2. Удаление студента <br>
![тут должен быть](https://sun4-17.userapi.com/liTTC41oa3slaSeMQpjsKReh7VKEEP_MxhalWg/AOvg4BGOwbE.jpg) 
6. Выполнение добавления и удаления предмета <br>
   1. Добавление предмета <br>
![тут должен](https://sun4-15.userapi.com/SVKqN0mBZBxq-0XTze3SwS-of0gzyZdhbDBC4w/uplT42nw-c4.jpg) 
   2. Удаление предмета <br>
![код](https://sun4-10.userapi.com/EnVlyOm5NIr9HYVuLo6QtRqs7OmERPxJkLCZPw/PiEw-g-VGl0.jpg)

