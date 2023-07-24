package com.javafx.intellijtheme.intellij

import com.javafx.intellijtheme.*
import javafx.event.EventTarget
import javafx.geometry.Pos
import javafx.scene.Cursor
import javafx.scene.Node
import javafx.scene.control.MenuBar
import javafx.scene.layout.BorderPane
import javafx.scene.layout.Priority
import javafx.scene.layout.Region
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.stage.Screen
import javafx.stage.Stage
import javafx.stage.StageStyle
import org.kordamp.ikonli.materialdesign2.MaterialDesignW
import tornadofx.*


class CustomStage(
    val primaryStage: Stage,
//    darkMode: Boolean = false,
    menu: MenuBar? = null,
    mainView: Node? = null,
    minWidth: Int = 720,
    minHeight: Int = 420,
    borderWidths: Double = 3.0
) : StackPane() {
    val borderWidth = 4.0
    val minWindowWidth = minWidth
    val minWindowHeight = minHeight
     val  myBorderPane = BorderPane()
    var mainView = mainView ?: Region()
        set(main) {
            field = main
            myBorderPane.center = main
        }

    var menubar: MenuBar = menu ?: MenuBar()
        set(menu) {
            field = menu
            menubarContainer.children.removeAll()
            menubarContainer.addChildIfPossible(menu)
        }
    val menubarContainer = group()

    init {

        this.maximize()
        this.primaryStage.initStyle(StageStyle.UNDECORATED)

        IntellijStyles.darkMode.addListener { _, _, new ->
            this.replaceClassIf(IntellijStyles.stageDark, IntellijStyles.stageLight, new)
        }

        primaryStage.isResizable = true

        this.addChildIfPossible(myBorderPane)
        myBorderPane .apply {
            useMaxSize = true
            style{
                backgroundColor += Color.TRANSPARENT
            }
            //*******************************************************
            //***************** Center
            //*******************************************************

            center = mainView

            //*******************************************************
            //***************** TOP
            //*******************************************************
            setupAppbar()

            //*******************************************************
            //***************** BOTTOM
            //*******************************************************

            bottom = hbox {
                useMaxWidth = true
                setHeightExact(borderWidth)
                id = "stage-border-bottom"

                var mouseY = 0
                this.setOnMouseReleased {
                    mouseY = 0
                }
                this.setOnMouseDragged {
                    if (primaryStage.isMaximized)
                        return@setOnMouseDragged
                    val height = it.screenY.toInt() - mouseY
                    if (mouseY != 0 && (primaryStage.height + height) > minWindowHeight) {
                        primaryStage.height += height
                    }
                    mouseY = it.screenY.toInt()
                }


            }


            //*******************************************************
            //***************** Left
            //*******************************************************

            left = vbox() {
                useMaxHeight = true
                setWidthExact(borderWidth)
                id = "stage-border-left"
                var mouseX = 0.0
                this.setOnMouseReleased {
                    mouseX = 0.0
                }
                this.setOnMouseDragged {

                    if (primaryStage.isMaximized)
                        return@setOnMouseDragged
                    val deltaWidth = it.screenX - mouseX
                    if (mouseX != 0.0 && (primaryStage.width - deltaWidth) > minWindowWidth   ) {
                        primaryStage.width -= deltaWidth
                        primaryStage.x += deltaWidth
                    }
                    mouseX = it.screenX
                }
            }

            //*******************************************************
            //***************** RIGHT
            //*******************************************************


            right = vbox {
                useMaxHeight = true
                setWidthExact(borderWidth)
                id = "stage-border-right"

                var mouseX = 0
                this.setOnMouseReleased {
                    mouseX = 0
                }
                this.setOnMouseDragged {
                    if (primaryStage.isMaximized)
                        return@setOnMouseDragged
                    val width = it.screenX.toInt() - mouseX
                    if (mouseX != 0 && (primaryStage.width + width) > minWindowWidth) {
                        primaryStage.width += width

                    }
                    mouseX = it.screenX.toInt()
                }
            }
            //*******************************************************
            //***************** END von Borderpane
            //*******************************************************
        }

        //*******************************************************
        //***************** right Corner
        //*******************************************************

        region {
            id = "stage-corner-right"
            this.stackpaneConstraints {
                alignment = Pos.BOTTOM_RIGHT
            }
            setWidthExact(borderWidth*2)
            setHeightExact(borderWidth*2)
            var mouseX = 0
            var mouseY = 0
            this.setOnMouseReleased {
                mouseX = 0
                mouseY = 0
            }
            this.setOnMouseDragged {
                if (primaryStage.isMaximized)
                    return@setOnMouseDragged
                val width = it.screenX.toInt() - mouseX
                val height = it.screenY.toInt() - mouseY
                if (mouseX != 0 && (primaryStage.width + width) > minWindowWidth) {
                    primaryStage.width += width
                }
                if (mouseY != 0 && (primaryStage.height + height) > minWindowHeight) {
                    primaryStage.height += height

                }
                mouseX = it.screenX.toInt()
                mouseY = it.screenY.toInt()
            }
        }


        region {
            id = "stage-corner-left"

            this.stackpaneConstraints {
                alignment = Pos.BOTTOM_LEFT
            }
            setWidthExact(borderWidth*2)
            setHeightExact(borderWidth*2)
            var mouseX = 0
            var mouseY = 0
            this.setOnMouseReleased {
                mouseX = 0
                mouseY = 0
            }

            this.setOnMouseDragged {
                if (primaryStage.isMaximized)
                    return@setOnMouseDragged
                val width = it.screenX.toInt() - mouseX
                val height = it.screenY.toInt() - mouseY
                if (mouseX != 0 && (primaryStage.width - width) > minWindowWidth) {
                    primaryStage.x += width
                    primaryStage.width -= width
                }
                if (mouseY != 0 && (primaryStage.height + height) > minWindowHeight) {
                    primaryStage.height += height

                }
                mouseX = it.screenX.toInt()
                mouseY = it.screenY.toInt()
            }

        }
    }

    private fun setupAppbar() {
        myBorderPane.top = hbox(alignment = Pos.CENTER_LEFT) {
            var mouseX = 0
            var mouseY = 0
            this.setOnMouseReleased {
                mouseX = 0
                mouseY = 0
            }

            IntellijStyles.darkMode.addListener { _, _, new ->
                this.replaceClassIf(IntellijStyles.appbarDark, IntellijStyles.appbarLight, new)
            }
            this.setOnMouseDragged {
                if (primaryStage.isMaximized)
                    return@setOnMouseDragged
                if (mouseX != 0 && mouseY != 0) {
                    primaryStage.x += it.screenX.toInt() - mouseX
                    primaryStage.y += it.screenY.toInt() - mouseY
                }
                mouseX = it.screenX.toInt()
                mouseY = it.screenY.toInt()
            }
            menubarContainer.addChildIfPossible(menubar)
            this.addChildIfPossible(menubarContainer)

            hbox(alignment = Pos.CENTER) {
                hgrow = Priority.ALWAYS
                this.label("Tornadofx Components 2023") {
                    id = "appbar-title"

                }
            }


            button {
                graphic = fontIcon(MaterialDesignW.WINDOW_MINIMIZE)
                action {
                    primaryStage.isIconified = true;
                }
            }
            button {
                action {
                    if (primaryStage.isMaximized) {
                        percentage(60.0, 80.0)
                        primaryStage.centerOnScreen()
                        center()
                    } else
                        maximize()

                }
                graphic = fontIcon(MaterialDesignW.WINDOW_MAXIMIZE)

            }
            button {
                id = "close-button"
                graphic = fontIcon(MaterialDesignW.WINDOW_CLOSE, 18)
                action {
                    primaryStage.close()
                }

            }

        }


    }


    fun percentage(width: Double, height: Double) {
        this.primaryStage.width = Screen.getPrimary().visualBounds.width * (width / 100.0)
        this.primaryStage.height = Screen.getPrimary().visualBounds.height * (height / 100.0)
        this.primaryStage.isMaximized = false

    }

    fun maximize(width: Double = 100.0, height: Double = 100.0) {
        this.primaryStage.width = Screen.getPrimary().visualBounds.width * (width / 100)
        this.primaryStage.height = Screen.getPrimary().visualBounds.height * (height / 100)
        this.primaryStage.isMaximized = true
        setSizeAsStage(primaryStage)

    }

    fun center() {
        this.primaryStage.x = Screen.getPrimary().visualBounds.width / 2 - primaryStage.width / 2
        this.primaryStage.y = Screen.getPrimary().visualBounds.height / 2 - primaryStage.height / 2

    }

}


fun EventTarget.intellijStage(
    primaryStage: Stage,
//    darkMode: Boolean = false,
    menu: MenuBar? = null,
    mainView: Node? = null,
    minWidth: Int = 720,
    minHeight: Int = 420,
    borderWidths: Double = 3.0,
    op: CustomStage.() -> Unit = {}
): CustomStage {
    val stage = CustomStage(primaryStage, menu, mainView, minWidth, minHeight, borderWidths)
    stage.op()

    this.addChildIfPossible(stage)
    return stage
}
