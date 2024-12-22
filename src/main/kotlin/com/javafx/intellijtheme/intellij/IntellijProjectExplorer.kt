package com.javafx.intellijtheme.intellij

import com.javafx.intellijtheme.IntellijController
import com.javafx.intellijtheme.from
import com.javafx.intellijtheme.replaceClassIf
import javafx.scene.control.TreeItem
import javafx.scene.control.TreeView
import javafx.scene.input.KeyCode
import javafx.scene.layout.Priority
import org.kordamp.ikonli.javafx.FontIcon
import org.kordamp.ikonli.materialdesign2.MaterialDesignF
import org.kordamp.ikonli.materialdesign2.MaterialDesignL
import tornadofx.*
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.absolute
import kotlin.io.path.isDirectory
import kotlin.io.path.listDirectoryEntries

class IntellijProjectExplorer : View() {

    val controller: IntellijController by inject()
    lateinit var  treeView : TreeView<Path>
    override val root = vbox {

        IntellijStyles.darkMode.addListener { _, _, newValue ->
            this.replaceClassIf(IntellijStyles.intellijFileExplorerDark, IntellijStyles.intellijFileExplorerLight, newValue)
            this.replaceClassIf(IntellijStyles.basicControlDark, IntellijStyles.basicControlLight, newValue)
        }
//        paddingBottom = 20.0
        vgrow = Priority.ALWAYS
        titledpane("Projects") {

        }
        treeView = treeview<Path> {
            // Create root item
            vgrow = Priority.ALWAYS
            root = TreeItem(Paths.get("E:\\zakir\\IdeaProjects\\JavaFxIntellijTheme"))
            val tree = this
//            shortcut(combo = "Alt+4"){
//                this.requestFocus()
//
//            }
            // Make sure the text in each TreeItem is the name of the Person
            cellFormat {
                val fileName = it.fileName.toString()
                id = it.toString()
                text = fileName

//                this.treeItem.expandTo(1)

                this.graphic = when{
                    it.isDirectory() -> FontIcon().from(MaterialDesignF.FOLDER, 22, IntellijStyles.tertiary2.light)
                    fileName.endsWith(".kt") -> FontIcon().from(MaterialDesignL.LANGUAGE_KOTLIN, 22, IntellijStyles.tertiary1.light)
                    fileName.endsWith(".java") -> FontIcon().from(MaterialDesignL.LANGUAGE_JAVA, 22, IntellijStyles.tertiary2.light)
                    fileName.endsWith(".py") -> FontIcon().from(MaterialDesignL.LANGUAGE_PYTHON, 22, IntellijStyles.tertiary3.light)
                    else ->{
//                      this.addClass(IntellijStyles.defaultFileIcon)
                        FontIcon().from(MaterialDesignF.FILE, 22).addClass(IntellijStyles.defaultFileIcon)
                    }
                }
                this.onDoubleClick {
                    if (it.isDirectory())
                        this.treeItem.expandTo(1)
                    else
                        controller.addPath(it)
                }

                tree.setOnKeyPressed { key ->
                    val selected = tree.selectionModel.selectedItem
                    if (selected != null && key.code == KeyCode.ENTER && selected.value == it) {
                        if (it.isDirectory())
                            selected.expandTo(1)
                        else
                            controller.addPath(it)
                    }

                }

            }
            // Generate items. Children of the root item will contain departments
            populate { parent ->
                if (parent.value.isDirectory()) parent.value.listDirectoryEntries()
                    .filter { it.fileName.toString() !in listOf(".git", ".gitignore", ".gradle", ".idea") }
                else
                    null
            }
        }
    }
}