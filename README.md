# Сдача 8 лабораторной работы
# Задание: доработайте приложение из видеоуроков. Разработайте компоненты, отвечающий за редактирование названия занятия и имени и фамилии студента. Разработайте компонент, отвечающий за редактирование списка элементов (с возможностью добавить или удалить элемент). В качестве аргументов этому компоненту передаются компоненты для отображения и для редактирования элемента списка. Добавьте в приложение страницы для редактирования списка студентов и списка занятий.
## Ход работы:
1. Заново загрузила исходный код к 8 лабораторной работе, добавила компонент AddStudent, в котором реализованы кнопка добавления и два инпута для ввода текста, для которых id обозначен как surname и name для имени и фамилии соответственно
```
interface AddStudentProps : RProps {
    var onClick: (Event) -> Unit
}
val fAddStudent =
        functionalComponent<AddStudentProps> {
            h3 { +"Введите имя и фамилию студента" }
            div {
                li {
                    input(type = text) {
                        attrs {
                            id = "name"
                            placeholder = "Имя" } }
                    input(type = text) {
                        attrs {
                            id = "surname"
                            placeholder = "Фамилия" } } }
                button {
                    +"Добавить"
                    attrs.onClickFunction = it.onClick } } }
```
2. Также создала компонент AddLesson для редактирования списка занятий, здесь реализованы кнопка добавления и инпут для ввода текста, для которого id обозначен как lesson для названия занятия
```
interface AddLessonProps : RProps {
    var onClick: (Event) -> Unit
}
val fAddLesson =
        functionalComponent<AddLessonProps> {
            h3 { +"Введите название урока урока"}
            div {
                li {
                    input(type = text) {
                        attrs {
                            id = "lesson"
                            placeholder = "Название" } } }
                button {
                    +"Добавить"
                    attrs.onClickFunction = it.onClick } } }
```
3. Создала общий компонент для редактирования списка элементов AnyEdit, в котором реализована кнопка удаления
```
interface AnyEditProps<S> : RProps {
    var subobjs: Array<S>
    var name: String
    var path: String
    var addFunc: (Event) -> Unit
    var delFunc: (Int) -> (Event) -> Unit
}
fun <S> fAnyEdit(
        rEdit: RBuilder.((Event) -> Unit) -> ReactElement,
        rComponent: RBuilder.(Array<S>, String, String) -> ReactElement
) =
        functionalComponent<AnyEditProps<S>> {
            div {
                h2 { +"Редактирование" }
                rEdit(it.addFunc)
                it.subobjs.mapIndexed { index, s ->
                    li { +"$s"
                        button {
                            +"Удалить"
                            attrs.onClickFunction = it.delFunc(index) } } }
                rComponent(it.subobjs, it.name, it.path) } }

```
4. Далее был также доработан app, в нем были реализованы отдельные функции для добавления и удаления как студентов, так и предметов, здесь также были разработаны функции добавления  на основе функции добавления предмета из 6 лабораторной работы. Ниже на скриншоте представлены функции добавления и удаления студентов: <br>
![тут долсм вжен быть код](https://sun9-69.userapi.com/wWNZ0eB096Pf13If9VAteITuwxL21WLyBLWJfg/uYKZobixGXw.jpg)
5. Выполнение добавления и удаления студента видно на следующих скриншотах <br>
   1. Добавление студента <br>
![тут должен быть код](https://sun9-67.userapi.com/vu-d_E62G7iaQp9L8vhTOoBpURSuaQX9Ek9FzQ/aKckm2kInxA.jpg)
   2. Удаление студентов <br>
![тут должен быть](https://sun4-10.userapi.com/dInDf7g9kKuHIqQy3f2ILfJAnD6Oxgzmr1IooQ/Fd8QX_NYPdA.jpg) 
6. Выполнение добавления и удаления предмета <br>
   1. Добавление предмета <br>
![тут должен](https://sun9-12.userapi.com/ozCTP4Os6qfay0eevy98u2An08G4aA_4pA4M2w/TE2EUF6E4CM.jpg) 
   2. Удаление предметов <br>
![код](https://sun4-10.userapi.com/gVigAsYOL09mff831y9cVaTQkasT90tLKnlyIg/7HuDr_y1JCs.jpg)

