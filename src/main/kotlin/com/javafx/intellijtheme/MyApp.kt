package com.javafx.intellijtheme

import com.javafx.intellijtheme.components.Person
import com.javafx.intellijtheme.components.people
import com.javafx.intellijtheme.intellij.*
import javafx.scene.layout.Priority
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
                item("JavaFx Components", keyCombination = "Alt+1") {
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
                for (i in 1..2) {
                    intellijDrawerItem(view, "Hospitals", expanded = true,icon = FontIcon().from(MaterialDesignH.HOSPITAL)) {

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
                scrollpane {
                    style {
                        borderWidth = multi(box(0.px))
                        padding = box(0.px)
                    }

                    isFitToWidth = true
                    isFitToHeight = true
                    vgrow = Priority.ALWAYS
                    addChildIfPossible(intellijTabPane.root.apply {
                        this.setMinSize(300.0, 300.0)
                    })

                }
            }
        }
    }

    init {
        reloadStylesheetsOnFocus()
        IntellijStyles.setLightMode()

    }


}