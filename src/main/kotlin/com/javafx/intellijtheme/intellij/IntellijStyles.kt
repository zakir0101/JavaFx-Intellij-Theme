package com.javafx.intellijtheme.intellij

import com.javafx.intellijtheme.FontIconStyle
import com.javafx.intellijtheme.MyStyles
import javafx.beans.property.SimpleBooleanProperty
import javafx.scene.Cursor
import javafx.scene.paint.Color
import tornadofx.*

class StyleColor(var light: Color, val dark: Color) {
    fun get(i: Int): Color {
        return if (i == 0) light else dark
    }
}


open class IntellijStyles : Stylesheet() {


    companion object {

        val fontIcon = FontIconStyle.fontIcon

        private val Light = 0
        private val Dark = 1

        val basicControlLight by cssclass()
        val basicControlDark by cssclass()

        val appbarDark by cssclass()
        val appbarLight by cssclass()

        val stageLight by cssclass()
        val stageDark by cssclass()

        val closeButton by cssid()
        val appbarTitle by cssid()
        val stageBorderRight by cssid()
        val stageBorderLeft by cssid()
        val stageBorderBottom by cssid()

        val listMenuClassLight by cssclass()
        val listMenuClassDark by cssclass()
        val listMenuItemClass by cssclass()

        val bgPrimaryLight by cssclass()
        val bgPrimaryDark by cssclass()

        val textOnPrimaryLight by cssclass()
        val textOnPrimaryDark by cssclass()

        val bgSecondaryLight by cssclass()
        val bgSecondaryDark by cssclass()

        val myTabPaneLight by cssclass()
        val myTabPaneDark by cssclass()

        val primary = StyleColor(Color.web("#F2F2F2"), Color.web("#3C3F41"))
        val primarySelected = StyleColor(Color.web("#D4D4D4"), Color.web("#0D293E"))
        val onPrimarySelected = StyleColor(Color.BLACK, Color.WHITE)
        val onPrimary = StyleColor(Color.web("#000000"), Color.web("#BBBBBB"))
        val onPrimaryVariant = StyleColor(Color.web("#999999"), Color.web("#69726A"))
        val secondary = StyleColor(Color.web("#FFFFFF"), Color.web("#2B2B2B"))
        val onSecondary = StyleColor(Color.web("#260707"), Color.web("#2B2B2B"))
        val tertiary1 = StyleColor(Color.web("#B15B2E"), Color.web("#B15B2E"))
        val tertiary2 = StyleColor(Color.web("#3E86A0"), Color.web("#3E86A0"))
        val tertiary3 = StyleColor(Color.web("#697D45"), Color.web("#697D45"))

        var modeIndex: Int = 0
        val darkMode: SimpleBooleanProperty = SimpleBooleanProperty(false)

        fun setDarkMode(boolean: Boolean) {
            modeIndex = if (boolean) 1 else 0
            darkMode.set(boolean)
        }

        fun StyleColor.getColor(): Color {
            return this.get(modeIndex)
        }

    }

    init {


        listOf(bgPrimaryLight, bgPrimaryDark).forEachIndexed { index, cssRule ->
            cssRule {
                backgroundColor += primary.get(index)
            }
        }


        listOf(textOnPrimaryLight, textOnPrimaryDark).forEachIndexed { index, cssRule ->
            cssRule {
                fill = onPrimary.get(index)
                textFill = onPrimary.get(index)
            }
        }




        listOf(bgSecondaryLight, bgSecondaryDark).forEachIndexed { index, cssRule ->
            cssRule {
                backgroundColor += secondary.get(index)
            }
        }


        // *******************************************************************
        // *********************    Basic Control  **********************************
        // *******************************************************************


        listOf(basicControlLight, basicControlDark).forEachIndexed { index, cssRule ->
            cssRule {

                // ***************    Button  *******************

                button {
                    fill = onPrimary.get(index)
                    textFill = onPrimary.get(index)

                    borderColor = multi(box(onPrimaryVariant.get(index)))
                    borderRadius += box(4.px)
                    backgroundRadius = multi(box(4.px), box(4.px))
                    backgroundColor = multi(primary.get(index), primary.get(index))
                    backgroundInsets = multi(box(0.px), box(1.px))
                    and(pressed) {
                        borderColor = multi(box(tertiary2.get(index)))
                        backgroundColor = multi(tertiary2.get(index), primary.get(index))

                    }
                }

                // ***************    Basic Control  *******************

                Stylesheet.textField {
                    borderInsets = multi(box(0.px), box(3.px))
                    borderWidth = multi(box(0.px), box(1.px))
                    borderColor = multi(box(Color.TRANSPARENT), box(onPrimaryVariant.get(index)))
                    borderRadius += box(3.px)
                    backgroundRadius = multi(box(3.px), box(3.px))
                    backgroundColor = multi(Color.TRANSPARENT, primary.get(index))
                    backgroundInsets = multi(box(0.px), box(3.px))
                    textFill = onPrimary.get(index)

                    and(focused) {
                        borderColor = multi(box(Color.TRANSPARENT), box(tertiary2.get(index)))
                        backgroundColor = multi(tertiary2.get(index), primary.get(index))

                    }
                }
                // ***************    Combobox  *******************


                comboBox {
                    borderInsets = multi(box(0.px), box(3.px))
                    borderWidth = multi(box(0.px), box(1.px))
                    borderColor = multi(box(Color.TRANSPARENT), box(onPrimaryVariant.get(index)))
                    borderRadius += box(3.px)
                    backgroundRadius = multi(box(3.px), box(3.px))
                    backgroundColor = multi(Color.TRANSPARENT, primary.get(index))
                    backgroundInsets = multi(box(0.px), box(3.px))
                    text {
                        fill = onPrimary.get(index)
                        textFill = onPrimary.get(index)
                    }

                    and(focused) {
                        borderColor = multi(box(Color.TRANSPARENT), box(tertiary2.get(index)))
                        backgroundColor = multi(tertiary2.get(index), primary.get(index))

                    }
                    comboBoxPopup {

                        listView {
                            backgroundColor = multi((primary.get(index)))
                            listCell {
                                fill = onPrimary.get(index)
                                textFill = onPrimary.get(index)
                                backgroundColor = multi((primary.get(index)))
                                and(hover) {
                                    fill = onPrimarySelected.get(index)
                                    textFill = onPrimarySelected.get(index)
                                    backgroundColor = multi((primarySelected.get(index)))
                                }
                            }
                        }

                    }


                }

                // ***************    Toggel Button  *******************
                toggleButton {
                    borderColor = multi(box(onPrimaryVariant.get(index)))
                    borderRadius += box(4.px)
                    backgroundRadius = multi(box(4.px), box(4.px))
                    backgroundInsets = multi(box(0.px), box(1.px))
                    if (index == 0) {
                        backgroundColor = multi(Color.TRANSPARENT, primary.get(index).darker())
                        text {
                            fill = onPrimary.get(index).brighter().brighter()
                        }
                    } else {
                        backgroundColor = multi(Color.TRANSPARENT, primary.get(index))
                        text { fill = onPrimary.get(index) }
                    }
                    and(pressed) {
                        borderColor = multi(box(tertiary2.get(index)))
                        backgroundColor = multi(tertiary2.get(index), primary.get(index))
                    }
                    and(selected) {
                        if (index == 0) {
                            backgroundColor = multi(Color.TRANSPARENT, primary.get(index))
                            text { fill = onPrimary.get(index) }
                        } else {
                            backgroundColor = multi(Color.TRANSPARENT, primary.get(index).darker())
                            text { fill = onPrimary.get(index).brighter().brighter() }
                            borderColor = multi(box(tertiary2.get(index)))
                        }
                    }


                }

                // ***************    Radio Button  *******************

                text {

                    fill = onPrimary.get(index)
                    textFill = onPrimary.get(index)

                }
                // ***************    Radio Button  *******************

                radioButton {
                    text {
                        fill = onPrimary.get(index)
                        textFill = onPrimary.get(index)

                    }
                    radio {
                        borderColor = multi(box(onPrimaryVariant.get(index)))
                        borderRadius += box(15.px)
                        backgroundColor = multi(Color.TRANSPARENT, primary.get(index))
                        backgroundInsets = multi(box(0.px), box(2.px))
                        listOf(pressed, focused).forEach {
                            and(it) {
                                borderColor = multi(box(tertiary2.get(index)))
                                backgroundColor = multi(tertiary2.get(index), primary.get(index))
                            }
                        }
                    }
                    and(selected) {
                        radio {
                            dot {
                                backgroundColor = multi(primary.get(index), onPrimary.get(index))
                                backgroundInsets = multi(box(0.px, 0.px, (-1).px, 0.px), box(0.px))
                            }
                        }
                    }
                    and(focused) {
                        radio {
                            borderColor = multi(box(tertiary2.get(index)))
                            backgroundColor = multi(tertiary2.get(index), primary.get(index))

                        }
                    }
                }

                // ***************    Check Box  *******************
                checkBox {
                    text {
                        fill = onPrimary.get(index)
                        textFill = onPrimary.get(index)
                    }

                    box {
                        borderColor = multi(box(Color.TRANSPARENT), box(onPrimaryVariant.get(index)))
                        borderInsets = multi(box(0.px), box(2.px))
                        borderWidth = multi(box(0.px), box(1.px))
                        backgroundColor = multi(Color.TRANSPARENT, primary.get(index))
                        backgroundInsets = multi(box(0.px), box(2.px))
                        fill = onPrimary.get(index)

                        listOf(pressed, focused).forEach { item ->
                            and(item) {
                                borderColor = multi(box(Color.TRANSPARENT), box(tertiary2.get(index)))
                                backgroundColor = multi(tertiary2.get(index), primary.get(index))
                            }
                        }
                    }
                    and(focused) {
                        box {
                            borderColor = multi(box(Color.TRANSPARENT), box(tertiary2.get(index)))
                            backgroundColor = multi(tertiary2.get(index), primary.get(index))
                        }
                    }


                }

                // ***************    Tooltip  *******************
                tooltip {
                    backgroundColor = multi(primary.get(index))
                }


                // ***************    progressbar  *******************
                Stylesheet.progressBar {
                    track {
                        backgroundColor = multi(primary.get(index))
                        borderColor = multi(box(onPrimaryVariant.get(index)))
                        borderRadius = multi(box(2.px))
                    }
                }


                // ***************    progressbar  *******************
                Stylesheet.progressIndicator {
                    minWidth = 50.px
                    minHeight = 50.px
                    progressColor = (tertiary2.get(index))
                    tick{
                        backgroundColor += primary.get(index)
                    }
                    text {
                        fill = onPrimary.get(index)
                        textFill = onPrimary.get(index)
                    }
                    indicator {
                        backgroundColor = multi(primary.get(index))

                    }
                }
                // ***************    textarea  *******************
                textArea {

                    borderInsets = multi(box(0.px), box(3.px))
                    borderWidth = multi(box(0.px), box(1.px))
                    borderColor = multi(box(Color.TRANSPARENT), box(onPrimaryVariant.get(index)))
                    borderRadius += box(3.px)
                    backgroundRadius = multi(box(3.px), box(3.px))
                    backgroundColor = multi(Color.TRANSPARENT, primary.get(index))
                    backgroundInsets = multi(box(0.px), box(3.px))
                    textFill = onPrimary.get(index)
                    scrollPane {
                        backgroundColor += Color.TRANSPARENT
                        viewport {
                            backgroundColor += Color.TRANSPARENT
                        }
                        content {
                            backgroundColor += Color.TRANSPARENT
                            text {
                                fill = onPrimary.get(index)
                            }
                        }
                    }
                    and(focused) {
                        borderColor = multi(box(Color.TRANSPARENT), box(tertiary2.get(index)))
                        backgroundColor = multi(tertiary2.get(index), primary.get(index))

                    }

                }

                // ***************    Scroll Pane  *******************


                scrollPane {

                    backgroundColor = multi(Color.TRANSPARENT)
                    backgroundInsets = multi(box(0.px))
                    borderWidth = multi(box(0.px))
                    padding = box(0.px)
                    viewport {
                        backgroundColor += Color.TRANSPARENT
                    }
                    content {
                        backgroundColor += Color.TRANSPARENT

                    }



                    corner {
                        backgroundInsets = multi(box(0.px))
                        backgroundColor = multi(primary.get(index))
                        padding = box(0.px)

                    }


                    scrollBar {
                        fontSize = 8.px
                        backgroundInsets = multi(box(0.px))
                        backgroundColor = multi(primary.get(index))
                        listOf(vertical, horizontal).forEach { orent ->
                            and(orent) {
                                if ("vertical" == orent.name) {
                                    borderWidth = multi(box(0.px))
                                }
                                if (orent.name == "horizontal")
                                    borderWidth = multi(box(0.px))
                                borderRadius = multi(box(0.px))
                                borderColor = multi(box(primary.get(index)))




                                track {
                                    backgroundColor += Color.TRANSPARENT
                                }
                                Stylesheet.thumb {

                                    padding = box(1.px)
                                    backgroundColor += if (index == Dark) tertiary2.get(index).darker()
                                        .grayscale() else primarySelected.get(index)
                                }


                                listOf(incrementButton, decrementButton, incrementArrow, decrementArrow).forEach {
                                    it {
                                        backgroundColor = multi(Color.TRANSPARENT)
                                        borderColor = multi(box(Color.TRANSPARENT))
                                    }
                                }


                            }

                        }
                    }


                }


                //************************* Date picker ***************************


                datePicker {

                    backgroundColor += primary.get(index)

                    textField {
                        borderColor = multi(box(Color.TRANSPARENT), box(Color.TRANSPARENT))
                    }

                    arrowButton {
                        backgroundInsets = multi(box(0.px))
                        backgroundColor = multi(
                            if (index == Dark) tertiary2.get(index).darker().grayscale() else primarySelected.get(index)
                        )
                        backgroundRadius = multi(box(0.px))
                        and(hover) {
                            backgroundColor = multi(tertiary2.get(index))
                        }
                        arrow {
                            backgroundColor = multi(onPrimary.get(index))
                        }
                    }

                    cell {
                        backgroundColor = multi(primary.get(index))
                        textFill = onPrimary.get(index)
                        borderColor += box(onPrimaryVariant.get(index))
                        listOf(hover, focused).forEach {
                            and(it) {
                                backgroundColor = multi(primarySelected.get(index))
                                textFill = onPrimarySelected.get(index)
                            }
                        }
                    }



                    datePickerPopup {
                        monthYearPane {
                            backgroundColor =
                                multi(if (index == Light) tertiary2.get(index) else tertiary2.get(index).darker())
                            text {
                                fill = primary.get(index)
                                textFill = primary.get(index)

                            }

                        }

                        weekNumberCell {

                        }





                        spinner {
                            backgroundColor = multi(Color.TRANSPARENT)
                            button {
                                backgroundColor = multi(Color.TRANSPARENT)
                                borderColor = multi(box(Color.TRANSPARENT))
                                listOf(leftArrow, rightArrow).forEach {
                                    it {
                                        fill = Color.WHITE
                                        backgroundColor += Color.WHITE

                                    }
                                }
                            }
                        }


                    }


                }


            }


        }


        // *******************************************************************
        // *********************    Border  **********************************
        // *******************************************************************

        listOf(stageLight, stageDark).forEachIndexed { index, stage ->
            stage {
                stageBorderRight {
                    cursor = Cursor.H_RESIZE
                    backgroundColor += primary.get(index)
                }

                stageBorderLeft {
                    backgroundColor += primary.get(index)
                    cursor = Cursor.H_RESIZE
                }

                stageBorderBottom {
                    backgroundColor += primary.get(index)
                    cursor = Cursor.V_RESIZE
                }

            }
        }


        // *******************************************************************
        // ********************* Appbar    ***********************************
        // *******************************************************************

        listOf(appbarLight, appbarDark).forEachIndexed { index, appbar ->
            appbar {
                backgroundColor += primary.get(index)

                appbarTitle {
                    textFill = onPrimaryVariant.get(index)
                    fill = onPrimaryVariant.get(index)
                    fontSize = 14.px
                }

                Stylesheet.menuBar {
                    backgroundColor += primary.get(index)
                    contextMenu {
                        backgroundColor += primary.get(index)
                    }


                    listOf(menu, Stylesheet.menuItem).forEach {
                        it {
                            label {
                                textFill = onPrimary.get(index)
                                fill = onPrimary.get(index)
                            }
                            listOf(hover, focused, selected, showing).forEach {
                                and(it) {
                                    label {
                                        textFill = onPrimarySelected.get(index)
                                        fill = onPrimarySelected.get(index)
                                    }
                                    backgroundColor += primarySelected.get(index).brighter().brighter()
                                }
                            }
                        }

                    }
                }

                button {
                    backgroundColor += Color.TRANSPARENT
                    focusTraversable = true
                    minWidth = 47.5.px
                    MyStyles.fontIcon {
                        fontSize = 18.px
                        backgroundColor += Color.DARKGRAY
                        fill = Color.DARKGRAY
                    }

                    and(hover) {
                        backgroundColor += Color.LIGHTGRAY
                        and(closeButton) {
                            backgroundColor += Color.RED
                            MyStyles.fontIcon {
                                fill = Color.WHITE
                            }
                        }
                        MyStyles.fontIcon {
                            fill = Color.DARKGRAY.darker()
                        }
                    }
                }

            }

        }


        // *******************************************************************
        // ********************* List Menu ***********************************
        // *******************************************************************


        listOf(listMenuClassLight, listMenuClassDark).forEachIndexed { index, lstMnu ->
            lstMnu {
                and(focused) {

                }
                backgroundColor += primary.get(index)

                listMenuItemClass {
                    listOf(hover, focused).forEach {
                        and(it) {
                            backgroundColor += primarySelected.get(index).brighter()
                        }
                    }
                    and(selected) {
                        backgroundColor += primarySelected.get(index)

                    }
                    backgroundColor += primary.get(index)
                    label {
                        textFill = onPrimary.get(index)
                        fill = onPrimary.get(index)
                    }
                    text {
                        fill = onPrimary.get(index)
                    }

                }
            }

        }


        // *******************************************************************
        // ********************* Tab pane   ***********************************
        // *******************************************************************


        listOf(myTabPaneLight, myTabPaneDark).forEachIndexed { index, cssRule ->

            cssRule {
                backgroundColor += secondary.get(index)
                padding = box(0.px)
                borderInsets = multi(box(1.px, 0.px, 1.px, 0.px))
                borderColor =
                    multi(box(onPrimaryVariant.get(index), Color.TRANSPARENT, onPrimaryVariant.get(index), Color.TRANSPARENT))

                tabHeaderArea {
                    padding = box(0.px, 0.px, 0.px, 0.px)
                    tabHeaderBackground {
                        backgroundColor += primary.get(index)
                        padding = box(0.px)
                        borderInsets += (box(0.px))
                        borderWidth += box(0.px)
                        borderColor += box(Color.TRANSPARENT)
                    }
                }


                tab {
                    padding = box(4.px, 8.px, 4.px, 8.px)
                    backgroundInsets = multi(box(0.px))
                    backgroundColor = multi(primary.get(index))
                    borderInsets = multi(box(0.px))
                    borderWidth = multi(box(0.px, 0.px, 4.px, 0.px))
                    borderColor = multi(box(Color.TRANSPARENT))
                    Stylesheet.tabLabel {
                        textFill = onPrimary.get(index)
                        fill = onPrimary.get(index)
                    }
                    fontIcon {
                        fill = onPrimary.get(index)
                        fontSize = 16.px
                    }
                    and(selected) {
                        backgroundColor += primary.get(index).brighter()
                        borderColor = multi(
                            box(
                                Color.TRANSPARENT,
                                Color.TRANSPARENT,
                                tertiary2.get(index),
                                Color.TRANSPARENT,
                            )
                        )
                        fontIcon {
                            fill = tertiary2.get(index)
                        }

                    }
                    listOf(hover, focused).forEach {
                        and(it) {
                            backgroundInsets = multi(box(0.px))
                            backgroundColor = multi(secondary.get(index))
                            borderInsets = multi(box(0.px))
                            borderWidth = multi(box(0.px, 0.px, 4.px, 0.px))
                            borderColor = multi(box(Color.TRANSPARENT))
                        }
                        and(selected) {
                            backgroundColor += primary.get(index).brighter()
                            borderColor = multi(
                                box(
                                    Color.TRANSPARENT,
                                    Color.TRANSPARENT,
                                    primarySelected.get(index).brighter().brighter(),
                                    Color.TRANSPARENT,
                                )
                            )

                        }
                    }
                }
            }


        }


        // *******************************************************************
        // ********************* Tab pane   ***********************************
        // *******************************************************************

        text

    }
}



