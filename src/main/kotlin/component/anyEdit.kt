package component

import hoc.withDisplayName
import kotlinx.html.js.onClickFunction
import org.w3c.dom.events.Event
import react.*
import react.dom.*

interface AnyEditProps<O> : RProps {
    var subObjs: Array<O>
    var Add: (Event) -> Unit
    var name: String
    var path: String
    var del : (Int) -> Unit
}
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

fun <O> RBuilder.anyEdit(
        rEdit: RBuilder.() -> ReactElement,
    rComponent: RBuilder.(Array<O>, String, String, (Int)-> Unit) -> ReactElement,
    subObjs: Array<O>,
    Add: (Event) -> Unit,
    name: String,
    path: String,
    del : (Int) -> Unit
) = child(
    withDisplayName("EditAny", fanyEdit (rEdit,rComponent))
) {
    attrs.subObjs = subObjs
    attrs.Add = Add
    attrs.name = name
    attrs.path = path
    attrs.del = del
}

