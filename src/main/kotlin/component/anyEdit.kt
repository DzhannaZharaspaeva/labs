package component

import hoc.withDisplayName
import kotlinx.html.InputType.submit
import kotlinx.html.id
import kotlinx.html.js.onClickFunction
import org.w3c.dom.events.Event
import react.*
import react.dom.*

interface AnyEditProps<O> : RProps {
    var subObjs: Array<O>
    var Add: (Event) -> Unit
    var Delete: (Int) -> (Event) -> Unit
    var name: String
    var path: String
}

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

fun <O> RBuilder.anyEdit(
    rComponenentname: RBuilder.(O) -> ReactElement,
    rComponenentEdit: RBuilder.() -> ReactElement,
    rComponent: RBuilder.(Array<O>, String, String) -> ReactElement,
    subObjs: Array<O>,
    Add: (Event) -> Unit,
    name: String,
    path: String,
    Delete: (Int) -> (Event) -> Unit
) = child(
    withDisplayName("EditAny", fanyEdit<O>(rComponenentname, rComponenentEdit, rComponent))
) {
    attrs.subObjs = subObjs
    attrs.Add = Add
    attrs.Delete = Delete
    attrs.name = name
    attrs.path = path
}
