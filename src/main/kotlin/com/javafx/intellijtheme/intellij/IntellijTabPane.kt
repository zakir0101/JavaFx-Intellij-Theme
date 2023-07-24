package com.javafx.intellijtheme.intellij

import com.javafx.intellijtheme.getTabPaneData
import com.javafx.intellijtheme.replaceClassIf
import javafx.geometry.Pos
import javafx.scene.control.TabPane
import javafx.scene.layout.Priority
import tornadofx.*


class IntellijTabPane : View() {

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
//            getTabPaneData().forEachIndexed { index, uiCategory ->
//
//                tab(uiCategory.name) {
//                    graphic = uiCategory.icon
//                    useMaxSize = true
//                    vbox(alignment = Pos.CENTER) {
//                        useMaxSize
//                        this.add(uiCategory.node)
//                    }
//                }
//
//            }


        }


}

