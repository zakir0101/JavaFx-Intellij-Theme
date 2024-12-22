package com.javafx.intellijtheme

import com.javafx.intellijtheme.components.Person
import com.javafx.intellijtheme.components.people
import com.javafx.intellijtheme.intellij.*
import javafx.event.Event
import javafx.scene.control.Tab
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.layout.Priority
import javafx.scene.web.WebView
import org.kordamp.ikonli.javafx.FontIcon
import org.kordamp.ikonli.materialdesign2.MaterialDesignC
import org.kordamp.ikonli.materialdesign2.MaterialDesignF
import org.kordamp.ikonli.materialdesign2.MaterialDesignH
import tornadofx.*


class MyApp : App(MainView::class, MyStyles::class) {

}

class MainView : View("Intellij UI Components") {
    val javaFxComponents: JavaFxComponents by inject()
    val intellijIDE: IntellijIDE by inject()
    val intellijTabPane: IntellijTabPane by inject()
    val intellijProjectExplorer: IntellijProjectExplorer by inject()

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
                text
                item("Light Mode", keyCombination = "Alt+l") {

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

            menu("Mode") {
                item("JavaFx Components", keyCombination = "Alt+3") {
                    action {
                        mainView = javaFxComponents.root
//                        IntellijStyles.updateTheme()
                    }
                }
                item("Intellij IDE", keyCombination = "Alt+2") {
                    action {
                        mainView = intellijIDE.root
//                        IntellijStyles.updateTheme()
                    }
                }
            }
            menu("Quit")


        }
        mainView = javaFxComponents.root
        mainView = intellijIDE.root
        intellijIDE.apply {
            val view = this
            leftDrawer.apply {
               val drawerItem = intellijDrawerItem(
                    view,
                    "Hospitals",
                    expanded = true,
                    icon = FontIcon().from(MaterialDesignH.HOSPITAL)
                ) {

                    addChildIfPossible(intellijProjectExplorer.root)
                }

                drawerItem.apply {
                    shortcut(combo = "Alt+4"){
                        val tree = intellijProjectExplorer.treeView
                        if(this@apply.expanded) {
                            this.expandedProperty.set(false)
                            intellijTabPane.tabPane.requestFocus()
                        }
                        else {
                            this.expandedProperty.set(true)
                            tree.requestFocus()
                            tree.selectionModel.select(tree.selectionModel.selectedItem)
                        }
                    }
                }
            }
            rightDrawer.apply {
                for (i in 1..1) {
                    intellijDrawerItem(view, "Contacts", icon = FontIcon().from(MaterialDesignC.CONTACTS)) {
                        tableview(people) {
                            vgrow = Priority.ALWAYS
                            IntellijStyles.darkMode.addListener { _, _, isDark ->
                                this.replaceClassIf(
                                    IntellijStyles.intellijTableDark,
                                    IntellijStyles.intellijTableLight,
                                    isDark
                                )
                            }
                            columnResizePolicy = SmartResize.POLICY
                            readonlyColumn("Name", Person::name).remainingWidth()
                            readonlyColumn("Nick", Person::nick).remainingWidth()
                        }
                    }
                }

            }
            bottomDrawer.apply {
                for (i in 1..4) {
                    intellijDrawerItem(
                        view,
                        "People",
                        icon = FontIcon().from(MaterialDesignF.FACE_SHIMMER)
                    ) {
                        vbox {
                            vgrow = Priority.ALWAYS
                            IntellijStyles.darkMode.addListener { _, _, isDark ->
                                this.replaceClassIf(
                                    IntellijStyles.bgGradientDark,
                                    IntellijStyles.bgGradientLight,
                                    isDark
                                )
                            }
                        }
                    }
                }
            }
            mainWindow.apply {

                IntellijStyles.darkMode.addListener { _, _, isDark ->
                    this.replaceClassIf(IntellijStyles.basicControlDark, IntellijStyles.basicControlLight, isDark)
                }
                intellijTabPane.root.apply {
                    vgrow = Priority.ALWAYS
                    this.setMinSize(300.0, 300.0)

                }
                addChildIfPossible(intellijTabPane.root)

            }
        }
    }

    init {
        reloadStylesheetsOnFocus()
        IntellijStyles.setDarkMode()

    }


}