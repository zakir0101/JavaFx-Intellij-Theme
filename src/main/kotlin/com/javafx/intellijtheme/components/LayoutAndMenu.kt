package com.javafx.intellijtheme.components


import com.javafx.intellijtheme.getTabPaneData
import com.javafx.intellijtheme.intellij.IntellijStyles
import com.javafx.intellijtheme.replaceClassIf
import javafx.event.EventTarget
import javafx.geometry.Pos
import javafx.scene.control.TabPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.paint.Color
import org.kordamp.ikonli.javafx.FontIcon
import org.kordamp.ikonli.materialdesign2.*
import tornadofx.View
import tornadofx.fold
import tornadofx.*



class LayoutAndMenu : View() {

    override val root =
        tabpane {
            removeClass(Stylesheet.tabPane)
            IntellijStyles.darkMode.addListener { _, _, newValue ->
                this.replaceClassIf(IntellijStyles.myTabPaneDark, IntellijStyles.myTabPaneLight,newValue)
            }
            tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE
            hgrow = Priority.ALWAYS
            vgrow = Priority.ALWAYS
            useMaxSize = true
            getTabPaneData().forEachIndexed { index, uiCategory ->

                tab(uiCategory.name) {
                    graphic = uiCategory.icon
                    useMaxSize = true
                    vbox(alignment = Pos.CENTER) {
                        useMaxSize
                        this.add(uiCategory.node)
                    }
                }

            }


        }


}



class Accordion : View() {
    override val root =
        squeezebox {
            fold("Customer Editor", expanded = true) {
                form {
                    fieldset("Customer Details") {
                        field("Name") { textfield() }
                        field("Password") { textfield() }
                    }
                }
            }
            fold("Some other editor", expanded = true) {
                stackpane {
                    label("Nothing here")
                }
            }
        }
}


class Form : View() {


    override val root = form {
        useMaxSize = false
        vbox(20) {
            fieldset("Left FieldSet") {
                hbox(20) {
                    vbox {
                        field("Field l1a") { textfield() }
                        field("Field l2a") { textfield() }
                    }
                    vbox {
                        field("Field l1b") { textfield() }
                        field("Field l2b") { textfield() }
                    }
                }
            }
            fieldset("Right FieldSet") {
                hbox(20) {
                    vbox {
                        field("Field r1a") { textfield() }
                        field("Field r2a") { textfield() }
                    }
                    vbox {
                        field("Field r1b") { textfield() }
                        field("Field r2b") { textfield() }
                    }
                }
            }
        }
    }
}


class ListView : View() {

    override val root =
        listmenu(theme = "blue") {
            item(text = "Contacts", graphic = FontIcon.of(MaterialDesignG.GOOGLE, 22, Color.YELLOW)) {
                activeItem = this
                whenSelected { }
            }

            item(text = "Projects", graphic = FontIcon.of(MaterialDesignT.TWITTER, 22, Color.YELLOW))
            item(text = "Settings", graphic = FontIcon.of(MaterialDesignF.FACEBOOK, 22, Color.YELLOW))
        }
}

class GridPaneView : View() {

    override val root = gridpane {
        row {
            button("North") {
                useMaxWidth = true
                gridpaneConstraints {
                    marginBottom = 10.0
                    columnSpan = 2
                }
            }
        }
        row {
            button("West")
            button("East")
        }
        row {
            button("South") {
                useMaxWidth = true
                gridpaneConstraints {
                    marginTop = 10.0
                    columnSpan = 2
                }
            }
        }
    }
}


class VBoxHBox : HBox() {

    init {
        println("create was called")
        style {
            backgroundColor += Color.WHEAT
        }
        vbox(alignment = Pos.CENTER) {
            style {
                backgroundColor += Color.BLACK
            }
            useMaxHeight = true
            prefWidth = 20.0
        }
        vbox {
            useMaxHeight = true
            hgrow = Priority.ALWAYS
            pane {
                useMaxWidth = true
                vgrow = Priority.ALWAYS
//                setWidthPercentage(100.0)
//                setHeightPercentage(90.0)
            }
            hbox {
                useMaxWidth = true
                prefHeight = 20.0
//                setWidthPercentage(100.0)
//                setHeightPercentage(10.0)
                pane {
//                    setHeightPercentage(100.0)
//                    setWidthPercentage(50.0)
                    useMaxHeight = true
                    hgrow = Priority.ALWAYS
                    style {
                        backgroundColor += Color.WHEAT.darker()
                    }
                }
                pane {
                    useMaxHeight = true
                    hgrow = Priority.ALWAYS
//                    setHeightPercentage(100.0)
//                    setWidthPercentage(50.0)
                    style {
                        backgroundColor += Color.WHEAT.desaturate()
                    }
                }

            }

        }

        println("create was finished")

    }
}

fun EventTarget.vbox_hbox(ob: VBoxHBox.() -> Unit): VBoxHBox {
    val b = VBoxHBox()
    this.add(b)
    b.ob()
    return b;
}