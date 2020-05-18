# Сдача 8 лабораторной работы
1. Сделала компонент для редактирования списка студентов
```
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
2. Сделала компонент для редактирования списка предметов
```
val fjobredact =
    functionalComponent<jobredactProps> { props ->
        input(text)  {
            attrs.placeholder = "Введите название предмета"
            attrs.id ="AddJob"
        }
    }
```
3. Доработала компонент для редактирования списка элементов
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
4. Выполнение добавления и удаления студента видно на следующих скриншотах <br>
   1. Добавление студента <br>
![тут должен быть код](https://sun4-12.userapi.com/tUTClnG9mynbeaPTdrtv2qbTZ1eYaj_TQjLdvw/sSZBFBb9NyU.jpg)
   2. Удаление студента <br>
![тут должен быть](https://sun4-17.userapi.com/liTTC41oa3slaSeMQpjsKReh7VKEEP_MxhalWg/AOvg4BGOwbE.jpg) 
5. Выполнение добавления и удаления предмета <br>
   1. Добавление предмета <br>
![тут должен](https://sun4-15.userapi.com/SVKqN0mBZBxq-0XTze3SwS-of0gzyZdhbDBC4w/uplT42nw-c4.jpg) 
   2. Удаление предмета <br>
![код](https://sun4-10.userapi.com/EnVlyOm5NIr9HYVuLo6QtRqs7OmERPxJkLCZPw/PiEw-g-VGl0.jpg)
Также в данной лаюораторной работе была использована функция добавления предмета/студента, которая была разработана в 6 лабораторной работе, в app.
