package com.javafx.intellijtheme

import com.javafx.intellijtheme.intellij.IntellijStyles
import javafx.geometry.Pos
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import tornadofx.*


class ExpandableListMenu : View() {

    override val root = hbox {
        val container = VBox().apply {
            useMaxHeight = true
            alignment = Pos.CENTER
            hgrow = Priority.ALWAYS
            IntellijStyles.darkMode.addListener { _, _, isDark ->
                this.replaceClassIf(IntellijStyles.bgSecondaryDark, IntellijStyles.bgSecondaryLight, isDark)
            }
        }

        useMaxSize = true
        hgrow = Priority.ALWAYS
        vgrow = Priority.ALWAYS

        vbox {
            useMaxHeight = true
            val myBox = this
            IntellijStyles.darkMode.addListener { _, _, newValue ->
                this.replaceClassIf(IntellijStyles.bgPrimaryDark, IntellijStyles.bgPrimaryLight, newValue)
            }
            this.addClass(MyStyles.collapse)
            this.focusedProperty().addListener { _, _, focused ->
                if (focused) addClass(Stylesheet.focused) else removeClass(Stylesheet.focused)
            }
            this.setOnMouseClicked { this.requestFocus() }

            listmenu(theme = "blue") {
                IntellijStyles.darkMode.addListener { _, _, isDark ->
                    this.replaceClassIf(IntellijStyles.listMenuClassDark, IntellijStyles.listMenuClassLight, isDark)
                }
                this.setOnMouseClicked { myBox.requestFocus() }
                useMaxWidth = true
                paddingRight = 3.0
                getListMenuData().forEachIndexed { index, uiCategory ->
                    item(
                        text = uiCategory.name,
                        graphic = uiCategory.icon
                    ) {
                        addClass(IntellijStyles.listMenuItemClass)
                        if (index == 0) {
                            activeItem = this
                            addClass(Stylesheet.selected)
                            if (uiCategory.name == "Data Controls")
                                container.alignment = Pos.BOTTOM_LEFT
                            container.addChildIfPossible(uiCategory.node)
                        }
                        whenSelected {
                            (activeItem as ListMenuItem).removeClass(Stylesheet.selected)
                            this.addClass(Stylesheet.selected)
                            container.children.clear()
                            container.alignment =
                                if (uiCategory.name == "Data Controls") Pos.BOTTOM_LEFT else Pos.CENTER
                            container.addChildIfPossible(uiCategory.node)
                        }
                    }
                }


            }


        }

        this.add(container)

    }
}


