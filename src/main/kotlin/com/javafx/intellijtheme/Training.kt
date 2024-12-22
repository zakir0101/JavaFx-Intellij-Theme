package com.javafx.intellijtheme

import java.nio.file.Path

enum class FileType{
    FILE,FOLDER
}
data class Training(val name : String , val type:FileType , val parentPath:Path)
fun main() {
    println("hello World4")
}