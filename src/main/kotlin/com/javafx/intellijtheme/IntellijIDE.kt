package com.javafx.intellijtheme

import com.javafx.intellijtheme.intellij.*
import javafx.geometry.Pos
import javafx.geometry.Side
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import tornadofx.*

class IntellijIDE : View() {
    val view = this
    lateinit var leftDrawer: Drawer
    lateinit var rightDrawer: Drawer
    lateinit var bottomDrawer: Drawer
    private   var lastBottomHeight = 300.0

    val mainWindow: VBox = VBox()


    override val root = stackpane {
        IntellijStyles.darkMode.addListener { _, _, isDark ->
            this.replaceClassIf(IntellijStyles.intellijDark, IntellijStyles.intellijLight, isDark)
        }
        rightDrawer = drawer(side = Side.RIGHT) {
            vgrow = Priority.NEVER
            stackpaneConstraints {
                this.alignment = Pos.TOP_RIGHT
            }
            setWidthExact(300.0)
            useMaxHeight = true

            this.widthProperty().addListener { _, oldWidth, newWidth ->
                adjustTabpaneWidth()
            }

            this.contentArea.children.onChange {
                adjustTabpaneWidth()
            }


        }




        leftDrawer = drawer(side = Side.LEFT) {
            stackpaneConstraints {
                this.alignment = Pos.TOP_LEFT
            }
            useMaxHeight = true
            setWidthExact(300.0)


            this.widthProperty().addListener { _, oldWidth, newWidth ->
                adjustTabpaneWidth()
            }

            this.contentArea.children.onChange {
                adjustTabpaneWidth()
            }


        }

        bottomDrawer = drawer(side = Side.BOTTOM) {
            id = "bottom-drawer"
            stackpaneConstraints {
                this.alignment = Pos.BOTTOM_CENTER
            }
            this.heightProperty().addListener { _, _, newValue ->
                adjustTabpaneHeight()
            }
            this.contentArea.children.onChange {
                adjustTabpaneHeight()
                 if (bottomDrawer.contentArea.children.isEmpty()) {
                     lastBottomHeight = bottomDrawer.prefHeight
                     bottomDrawer.setHeightExact(25.0)
                 }
                 else {
                     bottomDrawer.setHeightExact(lastBottomHeight)
                 }
            }
            setHeightExact(lastBottomHeight)
            useMaxWidth = true


        }

        this.addChildIfPossible(mainWindow)



        bottomDrawer.removeClass(DrawerStyles.drawer)
        leftDrawer.removeClass(DrawerStyles.drawer)
        rightDrawer.removeClass(DrawerStyles.drawer)



        IntellijStyles.darkMode.addListener { _, _, isDark ->
            bottomDrawer.replaceClassIf(IntellijStyles.drawerDark, IntellijStyles.drawerLight, isDark)
            rightDrawer.replaceClassIf(IntellijStyles.drawerDark, IntellijStyles.drawerLight, isDark)
            leftDrawer.replaceClassIf(IntellijStyles.drawerDark, IntellijStyles.drawerLight, isDark)

        }


        mainWindow.apply {
            setWidthExact(primaryStage.width - 25.0 * 2 - 3 * 2)
            setHeightExact(primaryStage.height - 25.0 - 25.0 - 3.0)
            this.translateX = 25.0
            stackpaneConstraints {
                alignment = Pos.TOP_LEFT
            }
        }


        primaryStage.heightProperty().addListener { _, old, new ->
            var newHeight = (bottomDrawer.prefHeight / old.toDouble()) * new.toDouble()
            val remainingHeight = primaryStage.height - newHeight
            if (remainingHeight < 151.0)
                newHeight = primaryStage.height - 151.0
            if (newHeight <= 55.0)
                newHeight = 56.0

            if (bottomDrawer.contentArea.children.isEmpty()) {
                lastBottomHeight = newHeight
            }
            else {
                bottomDrawer.setHeightExact(newHeight)
            }
            adjustTabpaneHeight()
        }


        primaryStage.widthProperty().addListener { _, oldWidth, newWidth ->
            var newWidthLeft = (leftDrawer.prefWidth / oldWidth.toDouble()) * newWidth.toDouble()
            var newWidthRight = (rightDrawer.prefWidth / oldWidth.toDouble()) * newWidth.toDouble()

            val leftWidth = if (leftDrawer.contentArea.children.isNotEmpty()) leftDrawer.width else (25.0)
            val rightWidth = if (rightDrawer.contentArea.children.isNotEmpty()) rightDrawer.width else (25.0)

            val remainingWidthForLeft = newWidth.toDouble() - newWidthLeft
            val remainingWidthForRight = newWidth.toDouble() - newWidthRight
            if (remainingWidthForLeft < 151.0)
                newWidthLeft = newWidth.toDouble() - 151.0
            if (remainingWidthForRight < 151.0)
                newWidthRight = newWidth.toDouble() - 151.0
            if (newWidthRight <= 55.0)
                newWidthRight = 56.0
            if (newWidthLeft <= 55.0)
                newWidthLeft = 56.0
            val remainingBetween = newWidth.toDouble() - newWidthRight - newWidthLeft
            if (remainingBetween <= 55.0) {
                if (newWidthLeft < newWidthRight)
                    newWidthRight -= 55.0
                else
                    newWidthLeft -= 55
            }
            leftDrawer.setWidthExact(newWidthLeft)
            rightDrawer.setWidthExact(newWidthRight)
            adjustTabpaneWidth()
        }

    }

    private fun adjustTabpaneHeight() {
        val newHeight = if (bottomDrawer.contentArea.children.isNotEmpty()) bottomDrawer.height else 25.0
        mainWindow.setHeightExact(primaryStage.height - 28.0 - 3.0 - newHeight)
        leftDrawer.contentArea.paddingBottom =(  newHeight )
    }

    private fun adjustTabpaneWidth() {
        val leftWidth = if (leftDrawer.contentArea.children.isNotEmpty()) leftDrawer.width else (25.0)
        val rightWidth = if (rightDrawer.contentArea.children.isNotEmpty()) rightDrawer.width else (25.0)

        mainWindow.setWidthExact(primaryStage.width - leftWidth - 3.0 * 2 - rightWidth)
        mainWindow.translateX = leftWidth - 1.0

    }
}

