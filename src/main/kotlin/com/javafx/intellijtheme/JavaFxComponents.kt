package com.javafx.intellijtheme

import com.javafx.intellijtheme.intellij.IntellijStyles
import com.javafx.intellijtheme.intellij.expandableListMenu
import javafx.geometry.Pos
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import tornadofx.*


class JavaFxComponents : View() {

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

        expandableListMenu {
            this.elistMenu = listmenu(theme = "blue") {
                getListMenuData().forEachIndexed { index, uiCategory ->
                    item(
                        text = uiCategory.name,
                        graphic = uiCategory.icon
                    ) {
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


