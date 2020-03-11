# Сдача 3 лабораторной работы
1. Сделала отдельную функцию для создания радиокнопок без повторения кода

```
private fun TagConsumer<HTMLElement>.radioButtons() {
    val colors = arrayOf("Green", "DeepPink", "Aqua")
    colors.forEach {
        input(radio, name = "radioButtons") {
            attributes += "value" to it
            onClickFunction = {
              val col=  document.getElementById("root")!!
                col.setAttribute("style", "color: ${value}")
            }
        }
        br()
    }
}
```
2. В main передаем эту функцию <br>
![тут должен быть код](https://sun4-16.userapi.com/49ph67w1RQ6Mfm5tK_-vDprjTb2UutDDI94A0A/lCfTUlgR9Qw.jpg)
3. Работа программы представлена на следующем изображении <br>
![код](https://sun4-10.userapi.com/634hd7Q6WmzXqGlZpKjDp8YcMK-E4jHe263dew/iit9wrpeNsY.jpg)
