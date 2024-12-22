package com.javafx.intellijtheme.intellij

import com.javafx.intellijtheme.IntellijController
import com.javafx.intellijtheme.editors.AceEditor
import com.javafx.intellijtheme.from
import com.javafx.intellijtheme.replaceClassIf
import javafx.scene.control.TabPane
import javafx.scene.layout.Priority
import org.kordamp.ikonli.javafx.FontIcon
import org.kordamp.ikonli.materialdesign2.MaterialDesignF
import tornadofx.*
import kotlin.io.path.readText


enum class EditorType {
    MonacoFx, ECLIPSE, RichFX
}

class IntellijTabPane : View() {
    val controller: IntellijController by inject()
    lateinit var tabPane: TabPane
//    lateinit var activeEditor: MonacoFX

    override val root =
        tabpane {
            tabPane = this@tabpane
            removeClass(Stylesheet.tabPane)
            IntellijStyles.darkMode.addListener { _, _, newValue ->
                this.replaceClassIf(IntellijStyles.myTabPaneDark, IntellijStyles.myTabPaneLight, newValue)
            }
//            tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE
            hgrow = Priority.ALWAYS
            vgrow = Priority.ALWAYS
            useMaxSize = true

            controller.openedFiles.onChange {
                while (it.next()) {
                    if (it.wasAdded()) {
                        val file = it.addedSubList[0]
                        tab(file.fileName.toString()) {
                            graphic = FontIcon().from(MaterialDesignF.FILE_CODE)
                            useMaxSize = true

                            this.select()

                            controller.selectedFile.addListener { _, _, newFile ->
                                if (newFile == file && tabPane.selectionModel.selectedItem != this) {
                                    this.select()
                                    this.content.requestFocus()
                                }

                            }
                            this.setOnSelectionChanged {
                                if (tabPane.selectionModel.selectedItem == this) {
                                    controller.selectedFile.set(file)
                                    this.content.requestFocus()
                                }
                            }


                            this.setOnClosed { event ->
                                controller.removePath(file)
                            }



//                            val monacoFx = MonacoFX()
//                            this.addChildIfPossible(monacoFx)
//                            monacoFx.editor.document.text = file.readText()
//                            IntellijStyles.darkMode.addListener { _, _, isDark ->
//                                monacoFx.editor.currentTheme = if (isDark) "vs-dark" else "vs-light"
//                            }
//                            monacoFx.editor.currentTheme = if (IntellijStyles.darkMode.get()) "vs-dark" else "vs-light"
//
//                            val fileName = file.fileName.toString()
//                            monacoFx.editor.currentLanguage = when {
//                                fileName.endsWith(".py") -> "python"
//                                fileName.endsWith(".kt") -> "kotlin"
//                                fileName.endsWith(".java") -> "java"
//                                else -> "python"
//                            }

                            val aceEditor = AceEditor(file)
                            this.addChildIfPossible(aceEditor)


                            this@tabpane.requestFocus()

                        }
                    }
                }
            }


        }


}

