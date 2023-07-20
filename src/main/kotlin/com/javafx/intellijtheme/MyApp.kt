package com.javafx.intellijtheme

import com.javafx.intellijtheme.intellij.IntellijStyles
import com.javafx.intellijtheme.intellij.intellijStage
import tornadofx.*

class MyApp : App(MainView::class, MyStyles::class) {

}

class MainView : View("Intellij UI Components") {
    val tornadoViews: ExpandableListMenu by inject()

    override val root = intellijStage(primaryStage) {
        menubar = menubar {
            menu("File") {
                item("Save")

            }
            this.menu("Edit") {
                item("Copy") {
                }
                item("Paste")
            }
            menu("View") {
                item("Light Mode" , keyCombination = "Alt+l") {

                    action {
                        IntellijStyles.setDarkMode(false)
                        reloadStylesheetsOnFocus()
                    }
                }
                item("Dark Mode", keyCombination = "Alt+d") {
                    action {
                        IntellijStyles.setDarkMode(true)
                        reloadStylesheetsOnFocus()
                    }
                }

            }

            menu("Save")
            menu("Quit")


        }
        mainView = tornadoViews.root

    }

    init {
        reloadStylesheetsOnFocus()
        IntellijStyles.setDarkMode(true)
        IntellijStyles.setDarkMode(false)

    }


}