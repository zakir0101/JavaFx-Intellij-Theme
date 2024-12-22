package com.javafx.intellijtheme

import com.javafx.intellijtheme.intellij.IntellijStyles
import javafx.geometry.Side
import javafx.scene.paint.Color
import tornadofx.*


class MyStyles : IntellijStyles() {

    companion object {

        val fontIcon = FontIconStyle.fontIcon
        val collapse by cssclass()
        val myGridPaneLight by cssclass()
        val myGridPaneDark by cssclass()
        val myGridPaneItem by cssclass()
        val myGridPaneLable by cssclass()
        val bottomDrawer by cssid()


    }

    init {
        collapse {

            focusTraversable = true
            prefWidth = 45.px
            maxWidth = 45.px
            minWidth = 45.px
            listMenuItemClass {
                prefWidth = 45.px
                maxWidth = 45.px
                minWidth = 45.px
                Stylesheet.text {
                    visibility = FXVisibility.COLLAPSE
                }
            }
            and(focused) {
                prefWidth = 300.px
                maxWidth = 300.px
                minWidth = 300.px
                listMenuItemClass {
                    Stylesheet.text {
                        visibility = FXVisibility.VISIBLE
                    }
                }

            }
        }

//        scrollPane {
//            backgroundColor = multi(Color.TRANSPARENT)
//            backgroundInsets = multi(box(0.px))
//            borderWidth = multi(box(0.px))
//
//        }


        listOf(myGridPaneLight, myGridPaneDark).forEachIndexed { index, cssRule ->
            cssRule {

                backgroundColor += secondary.get(index)
                myGridPaneItem {
                    borderWidth = multi(box(1.px))
                    borderColor = multi(box(onPrimaryVariant.get(index)))
                    borderInsets = multi(box(80.px, 40.px))

                }

                myGridPaneLable {
                    text {
                        fill = onPrimary.get(index)
                    }
                    backgroundColor += secondary.get(index)
                    padding = box(0.px, 5.px)
                }

            }

        }
        webView{
            padding = box(20.px , 0.px,0.px,0.px)
        }

//        DrawerStyles.drawer {
//            DrawerStyles.contentArea {
//                prefWidth = 300.px
//            }
//        }
//
//        bottomDrawer {
//            prefWidth = -1.px
//            DrawerStyles.contentArea {
//                backgroundColor += Color.TRANSPARENT
//                padding = box(0.px, 25.px, 0.px, 25.px)
//                prefWidth = -1.px
//                borderColor = multi(box(Color.TRANSPARENT, primary.light, primary.light, primary.light))
//            }
//        }
//
    }


}