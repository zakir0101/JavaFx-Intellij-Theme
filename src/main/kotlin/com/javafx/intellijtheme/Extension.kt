package com.javafx.intellijtheme

import javafx.event.EventTarget
import javafx.scene.Node
import javafx.scene.layout.Region
import javafx.scene.paint.Color
import javafx.stage.Screen
import javafx.stage.Stage
import org.kordamp.ikonli.Ikon

import org.kordamp.ikonli.javafx.FontIcon

import tornadofx.*


fun Region.getWidthPercentage(percentage: Int): Double {
    val parent: Region = this.parent as Region
    return percentage * parent.prefWidth / 100.0
}


fun Region.setWidthPercentage(percentage: Double) {
    val parent: Region = this.parent as Region
    println( "parent.width ${parent.width}")
    prefWidth = (percentage / 100.0) * parent.width
}

fun Region.getHeightPercentage(percentage: Int): Double {
    val parent: Region = this.parent as Region
    return percentage * parent.prefHeight / 100.0
}

fun Region.setHeightPercentage(percentage: Double) {
    val parent: Region = this.parent as Region
    println( "height.width ${parent.height}")
    prefHeight = (percentage / 100.0) * parent.height
}


fun Region.setSizePercentage(widthPercentage: Double, heightPercentage: Double) {
    val parent: Region = this.parent as Region
    if (widthPercentage != -1.0)
        prefWidth = (widthPercentage / 100.0) * parent.width
    if (heightPercentage != -1.0)
        prefHeight = (heightPercentage / 100.0) * parent.height
}

fun Region.setSizeAsStage(stage: Stage) {
    setPrefSize(stage.width, stage.height)
}
fun Region.setWidthExact(width: Double) {
    prefWidth = width
    minWidth = width
    maxWidth = width
}

fun Region.setHeightExact(height: Double) {
    prefHeight = height
    minHeight = height
    maxHeight = height
}



fun View.maximize(width: Double = 100.0, height: Double = 100.0) {
    this.primaryStage.width = Screen.getPrimary().visualBounds.width * (width / 100)
    this.primaryStage.height = Screen.getPrimary().visualBounds.height * (height / 100)
    this.primaryStage.isMaximized = true
}

fun View.percentage(width: Double, height: Double) {
    this.primaryStage.width = Screen.getPrimary().visualBounds.width * (width / 100.0)
    this.primaryStage.height = Screen.getPrimary().visualBounds.height * (height / 100.0)
    this.primaryStage.isMaximized = false

}
fun View.center() {
    this.primaryStage.x = Screen.getPrimary().visualBounds.width/2 - primaryStage.width/2
    this.primaryStage.y =  Screen.getPrimary().visualBounds.height/2 - primaryStage.height/2

}

fun FontIcon.from(icon: Ikon, size: Int? = null, color: Color? = null): FontIcon {
//     val colorName = javafx.scene.paint.Color::class.java.fields.find { color == it.get(null) }?.name
    if (size != null && color != null)
        return FontIcon("${icon.description}:$size:${color.toString()}").addClass(FontIconStyle.fontIcon)
    if (color == null && size != null )
        return FontIcon("${icon.description}:$size").addClass(FontIconStyle.fontIcon)
    return FontIcon("${icon.description}").addClass(FontIconStyle.fontIcon)
}

fun EventTarget.fontIcon(icon: Ikon, size: Int? = null, color: Color? = null, op: FontIcon.() -> Unit = {}):FontIcon {
    val fontIcon = FontIcon().from(icon,size, color)
    fontIcon.op()
    this.add(fontIcon)
    return fontIcon
}

fun Node.replaceClassIf(newClass :CssRule ,oldClass:CssRule , condition: Boolean){
    if (condition) {
        this.removeClass(oldClass)
        this.addClass(newClass)
    }else{
        this.removeClass(newClass)
        this.addClass(oldClass)
    }
}

class FontIconStyle : Stylesheet(){
    companion object{
        val fontIcon by cssclass()
    }
}


// fun  FontIcon.from(icon: Ikon, size : Int , color : LinearGradient): FontIcon {
//     return  FontIcon("${icon.description}:${size}:$colorName")
// }
