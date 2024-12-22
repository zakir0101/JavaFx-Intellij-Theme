package com.javafx.intellijtheme

import javafx.beans.property.SimpleObjectProperty
import javafx.collections.ObservableList
import tornadofx.Controller
import tornadofx.observableListOf
import java.nio.file.Path

class IntellijController : Controller() {
    val openedFiles: ObservableList<Path> = observableListOf()
    val selectedFile = SimpleObjectProperty<Path>()
    fun addPath(path: Path?) {
        if (path == null)
            return
        if (openedFiles.contains(path)) {
            if(selectedFile != path)
                selectedFile.set(path)
            else{
                selectedFile.set(null)
                selectedFile.set(path)
            }
            return
        }
        openedFiles.add(path)
    }

    fun removePath(path: Path?) {
        if (path == null)
            return

        if (selectedFile == path) {
            selectedFile.set(null)
            if (openedFiles.size > 1) {
                val fileIndex = openedFiles.indexOf(path)
                if (fileIndex > 0)
                    selectedFile.set(openedFiles[fileIndex - 1])
                else
                    selectedFile.set(openedFiles[1])
            } else {

            }

        }

        openedFiles.remove(path)

    }
}