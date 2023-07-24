package com.javafx.intellijtheme.components

import javafx.beans.property.SimpleListProperty
import javafx.geometry.Side
import javafx.scene.layout.Priority
import tornadofx.*




class Link(val name: String, val uri: String)
class Person(val name: String, val nick: String)


val TornadoFXScreencastsURI = "https://www.google.com/"
val _links = observableListOf(
    Link("Google.com", "http://google.com/"),
    Link("JavaFX Docs", "https://openjfx.io//"),
    Link("TornadoFx Docs", "https://docs.tornadofx.io//"),
    Link("TornadoFx Screencast", TornadoFXScreencastsURI),
    Link("Facebook", "https://de-de.facebook.com//"),
)

var people = observableListOf<Person>(
    Person("Mohamed", "Ali"),
    Person("John", "Doa"),
    Person("James", "Bond"),
    Person("Mike", "Zuckerberg"),


    )
val userAgent =
    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36"
val links = SimpleListProperty<Link>(_links)

class DataControls : View("TornadoFX Info Browser") {
    override val root = drawer(side = Side.BOTTOM) {
        vgrow = Priority.NEVER
        item("Screencasts", expanded = true, showHeader = true) {
            webview {
                engine.javaScriptEnabledProperty().set(true)
                prefWidth = 470.0
                engine.userAgent = userAgent
                engine.load(TornadoFXScreencastsURI)
            }
        }
        item("Links", showHeader = true) {
            listview(links) {
                cellFormat { link ->
                    graphic = hyperlink(link.name) {
                        setOnAction {
                            hostServices.showDocument(link.uri)
                        }
                    }
                }
            }
        }
        item("People", showHeader = true) {
            tableview(people) {
                readonlyColumn("Name", Person::name)
                readonlyColumn("Nick", Person::nick)
            }
        }

    }


    // Sample data variables left out (iPhoneUserAgent, TornadoFXScreencastsURI, people and links)
}
//
//class DataControls : View() {
//
//    override val root = vbox {  }
//}