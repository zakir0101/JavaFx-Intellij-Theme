package com.javafx.intellijtheme.intellij

import com.javafx.intellijtheme.IntellijIDE
import com.javafx.intellijtheme.setHeightExact
import com.javafx.intellijtheme.setWidthExact
import javafx.geometry.Pos
import javafx.geometry.Side
import javafx.scene.Cursor
import javafx.scene.Node
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import tornadofx.*



fun Drawer.intellijDrawerItem(
    view: IntellijIDE,
    title: String? = null,
    icon: Node? = null,
    expanded: Boolean = false,
    showHeader: Boolean = multiselect,
    op: VBox.() -> Unit
):DrawerItem {
    val borderWidth = 3.0
    val drawer = this
    val item = this.item(title, icon, expanded, false, {})

    this.contentArea.style {
        backgroundColor += Color.TRANSPARENT
    }
    if (drawer.dockingSide == Side.BOTTOM) {

        item.alignment = Pos.TOP_CENTER
        item.region {
            addClass(IntellijStyles.drawerItemResizer)

            setHeightExact(borderWidth)
            useMaxWidth = true
            style (append = true) {
                cursor = Cursor.N_RESIZE
            }

            var mouseY = 0
            this.setOnMouseReleased {
                mouseY = 0
            }
            this.setOnMouseDragged {
                val deltaHeight = it.screenY.toInt() - mouseY
                val minH = 55.0
                val maxH = view.primaryStage.height - 150.0
                if (mouseY != 0 && (drawer.height - deltaHeight) < maxH && (drawer.height - deltaHeight) > minH) {
                    drawer.setHeightExact(drawer.prefHeight - deltaHeight)
                }
                mouseY = it.screenY.toInt()
            }

        }


        op(item)

    } else if (drawer.dockingSide == Side.RIGHT) {
        item.hbox {

            vgrow = Priority.ALWAYS
            this.region {
                setWidthExact(borderWidth)
                useMaxHeight = true
                addClass(IntellijStyles.drawerItemResizer)
                style (append = true) {
                    cursor = Cursor.E_RESIZE
                }
                var mouseX = 0
                this.setOnMouseReleased {
                    mouseX = 0
                }
                this.setOnMouseDragged {
                    val deltaWidth = it.screenX.toInt() - mouseX
                    val minW = 55.0
                    var maxW = view.primaryStage.width - 55.0
                    val remainingWidth = view.primaryStage.width - drawer.prefWidth + deltaWidth - 55.0
                    if (view.leftDrawer.contentArea.children.isNotEmpty())
                        maxW -= view.leftDrawer.prefWidth
                    else if (view.leftDrawer.contentArea.children.isEmpty() && view.leftDrawer.prefWidth > remainingWidth) {
                        if ((remainingWidth) > minW + 1)
                            view.leftDrawer.setWidthExact(remainingWidth)
                        else
                            view.leftDrawer.setWidthExact(minW + 1)
                    }
                    if (mouseX != 0 && (drawer.width - deltaWidth) < (view.primaryStage.width - 151.0) && (drawer.width - deltaWidth) < maxW && (drawer.width - deltaWidth) > minW) {
                        drawer.setWidthExact(drawer.prefWidth - deltaWidth)
                    }
                    mouseX = it.screenX.toInt()
                }

            }


            this.vbox {


                hgrow = Priority.ALWAYS
                this.op()
            }
        }
    } else if (drawer.dockingSide == Side.LEFT) {
//        item.alignment = Pos.TOP_CENTER
        item.hbox {
            vgrow = Priority.ALWAYS

            this.vbox {
                hgrow = Priority.ALWAYS
                this.op()
                this.children.onChange { change ->
                    while (change.next()) {
                        if (change.wasAdded()) {
                            change.addedSubList.asSequence()
                                .filter { VBox.getVgrow(it) == null }
                                .forEach { VBox.setVgrow(it, Priority.ALWAYS) }
                        }
                    }
                }
            }

            this.region {
                setWidthExact(borderWidth)
                useMaxHeight = true
                addClass(IntellijStyles.drawerItemResizer)
                style (append = true) {
                    cursor = Cursor.E_RESIZE
                }
                var mouseX = 0
                this.setOnMouseReleased {
                    mouseX = 0
                }
                this.setOnMouseDragged {
                    val deltaWidth = it.screenX.toInt() - mouseX
                    val minW = 55.0
                    var maxW = view.primaryStage.width - 55.0
                    val remainingWidth = view.primaryStage.width - drawer.prefWidth - deltaWidth - 55.0
                    if (view.rightDrawer.contentArea.children.isNotEmpty())
                        maxW -= view.rightDrawer.prefWidth
                    else if (view.rightDrawer.contentArea.children.isEmpty() && view.rightDrawer.prefWidth > remainingWidth) {
                        if ((remainingWidth) > minW + 1)
                            view.rightDrawer.setWidthExact(remainingWidth)
                        else
                            view.rightDrawer.setWidthExact(minW + 1)
                    }
                    if (mouseX != 0 && (drawer.width + deltaWidth) < (view.primaryStage.width - 151.0) && (drawer.width + deltaWidth) < maxW && (drawer.width + deltaWidth) > minW) {
                        drawer.setWidthExact(drawer.prefWidth + deltaWidth)

                    }
                    mouseX = it.screenX.toInt()
                }

            }


        }
    }
return item
}