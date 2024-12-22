package com.javafx.intellijtheme.intellij

import com.javafx.intellijtheme.FontIconStyle
import com.javafx.intellijtheme.MyStyles
import com.javafx.intellijtheme.from
import javafx.beans.property.SimpleBooleanProperty
import javafx.scene.Cursor
import javafx.scene.paint.Color
import javafx.scene.paint.CycleMethod
import javafx.scene.paint.LinearGradient
import javafx.scene.paint.Stop
import org.kordamp.ikonli.javafx.FontIcon
import org.kordamp.ikonli.materialdesign2.MaterialDesignB
import org.kordamp.ikonli.materialdesign2.MaterialDesignW
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
        val stageCornerLeft by cssid()
        val stageCornerRight by cssid()

        val listMenuClassLight by cssclass()
        val listMenuClassDark by cssclass()
        val listMenuItemClass by cssclass()

        val bgPrimaryLight by cssclass()
        val bgPrimaryDark by cssclass()

        val bgGradientLight by cssclass()
        val bgGradientDark by cssclass()

        val expandableListMenuLight by cssclass()
        val expandableListMenuDark by cssclass()

        val textOnPrimaryLight by cssclass()
        val textOnPrimaryDark by cssclass()

        val bgSecondaryLight by cssclass()
        val bgSecondaryDark by cssclass()

        val myTabPaneLight by cssclass()
        val myTabPaneDark by cssclass()

        val drawerLight by cssclass()
        val drawerDark by cssclass()
        val drawerItemResizer by cssclass()

        val intellijLight by cssclass()
        val intellijDark by cssclass()
        val bottomDrawer by cssid()

        val intellijTableLight by cssclass()
        val intellijTableDark by cssclass()

        val intellijFileExplorerLight by cssclass()
        val intellijFileExplorerDark by cssclass()
        val defaultFileIcon by cssclass()

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

        fun setDarkMode() {
            setDarkMode(false)
            setDarkMode(true)
        }

        fun setLightMode() {
            setDarkMode(true)
            setDarkMode(false)
        }

        fun updateTheme() {
            if (darkMode.get() == true)
                setDarkMode(true)
            else
                setDarkMode(false)
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


        listOf(bgGradientLight, bgGradientDark).forEachIndexed { index, cssRule ->
            val stops = listOf(Stop(0.0, primary.get(index)), Stop(1.0, IntellijStyles.primarySelected.get(index)));
            val lg1 = LinearGradient(0.0, 0.0, 0.0, 1.0, true, CycleMethod.NO_CYCLE, stops)

            cssRule {
                backgroundColor += lg1
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
                    tick {
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

                backgroundColor += Color.TRANSPARENT

                stageBorderRight {
                    cursor = Cursor.E_RESIZE
                    backgroundColor += primary.get(index)
                }

                stageBorderLeft {
                    backgroundColor += primary.get(index)
                    cursor = Cursor.W_RESIZE
                }

                stageBorderBottom {
                    backgroundColor += primary.get(index)
                    cursor = Cursor.S_RESIZE
                }

                stageCornerLeft {
                    cursor = Cursor.SW_RESIZE
                    backgroundColor += primary.get(index)

                }
                child(stageCornerRight) {
                    cursor = Cursor.SE_RESIZE
                    backgroundColor += primary.get(index)
                }

            }
        }


        // *******************************************************************
        // ********************* Appbar    ***********************************
        // *******************************************************************

        listOf(appbarLight, appbarDark).forEachIndexed { index, appbar ->
            appbar {
                backgroundInsets = multi(box(0.px))
                backgroundColor = multi(primary.get(index))
                borderWidth = multi(box(0.px))
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
                borderWidth = multi(box(1.px, 0.px, 0.px, 0.px))
                borderColor =
                    multi(
                        box(
                            onPrimaryVariant.get(index),
                            onPrimaryVariant.get(index),
                            onPrimaryVariant.get(index),
                            onPrimaryVariant.get(index)
                        )
                    )

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


                    tabCloseButton {
                        shape =
                            "M19,6.41L17.59,5 12,10.59 6.41,5 5,6.41 10.59,12 5,17.59 6.41,19 12,13.41 17.59,19 19,17.59 13.41,12z"
                        fill = onPrimary.get(index)
                        backgroundColor += onPrimary.get(index)
                        scaleX = 0.5
                        scaleY = 0.5
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
        // ********************* expandable List Menu   **********************
        // *******************************************************************

        listOf(expandableListMenuLight, expandableListMenuDark).forEachIndexed { index, cssRule ->
            cssRule {
                backgroundColor += primary.get(index)
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
                button {

                    visibility = FXVisibility.VISIBLE
                    borderInsets = multi(box(0.px))
                    borderColor = multi(box(Color.TRANSPARENT))
                    borderWidth = multi(box(0.px))
                    backgroundInsets = multi(box(0.px))
                    backgroundColor = multi(primary.get(index))

                    and(hover) {
                        backgroundColor = multi(primary.get(index).derive(0.5))

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
                    button {
                        visibility = FXVisibility.COLLAPSE

                    }
                }

            }

        }

        // *******************************************************************
        // ********************* Drawer    ***********************************
        // *******************************************************************

        listOf(drawerLight, drawerDark).forEachIndexed { index, cssRule ->
            cssRule {
                DrawerStyles.drawerItem {
                    backgroundColor += Color.TRANSPARENT
                    drawerItemResizer {
                        backgroundColor += onPrimaryVariant.get(index)
                    }
                }

                child(DrawerStyles.contentArea) {
                    backgroundColor = multi(Color.TRANSPARENT)
                    borderColor =
                        multi(box(Color.TRANSPARENT, primary.get(index), primary.get(index), primary.get(index)))
                    borderWidth = multi(box(0.px))
                }

                child(DrawerStyles.buttonArea) {
                    spacing = 0.px
                    padding = box(0.px)

                    backgroundColor = multi(primary.get(index))
                    borderWidth = multi(box(0.5.px))
                    borderColor =
                        multi(box(if (index == Dark) secondary.get(index) else onPrimaryVariant.get(index)))


                    toggleButton {
                        padding = box(4.px, 8.px, 4.px, 8.px)
                        backgroundInsets = multi(box(0.px))
                        backgroundColor = multi(primary.get(index))
                        borderWidth = multi(box(0.px))
                        borderColor = multi(box(Color.TRANSPARENT))
                        backgroundInsets += box(0.px)
                        backgroundRadius += box(0.px)

                        text {
                            textFill = onPrimary.get(index)
                            fill = onPrimary.get(index)
                        }
                        fontIcon {
                            fill = onPrimary.get(index)
                            fontSize = 14.px
                        }
                        and(selected) {
                            backgroundColor += primary.get(index).brighter()
                            borderColor = multi(
                                box(Color.TRANSPARENT)
                            )
                        }
                        listOf(hover, focused).forEach {
                            and(it) {
                                backgroundColor = multi(secondary.get(index))
                            }
                            and(selected) {
                                backgroundColor += primary.get(index).brighter()

                            }

                        }


                    }
//                DrawerStyles.drawerItem child titledPane {
//                    title {
//                        backgroundRadius += box(0.px)
//                        padding = box(2.px, 5.px)
//                    }
//                    content {
//                        borderColor += box(Color.TRANSPARENT)
//                    }
//                }


                }

            }
        }

        // *******************************************************************
        // ********************* Intellij    ***********************************
        // *******************************************************************
        listOf(intellijLight, intellijDark).forEachIndexed { index, cssRule ->
            cssRule {
                backgroundColor += secondary.get(index)
                padding = box(0.px)
                child(bottomDrawer) {
                    child(DrawerStyles.buttonArea) {
                        padding = box(0.px, 24.px, 0.px, 24.px)
                    }
                    child(DrawerStyles.contentArea) {
                        padding = box(0.px, 25.px, 0.px, 25.px)

                    }
                }
            }

        }


        // *******************************************************************
        // ********************* Intellij Table    ***********************************
        // *******************************************************************

        listOf(intellijTableLight, intellijTableDark).forEachIndexed { index, cssRule ->
            cssRule {

                val stops = listOf(Stop(0.0, primary.get(index)), Stop(1.0, primarySelected.get(index)));
                val lg1 = LinearGradient(0.0, 0.0, 0.0, 1.0, true, CycleMethod.NO_CYCLE, stops)

                backgroundColor += Color.TRANSPARENT
                and(focused) {
                    backgroundColor += Color.TRANSPARENT
                }
                columnHeaderBackground {
                    backgroundColor += lg1
                    label {
                        fill = onPrimary.get(index)
                        textFill = onPrimary.get(index)
                    }

                    and(empty) {
                        backgroundColor += Color.RED
                    }
                }

                columnHeader {
                    backgroundColor = multi(onPrimaryVariant.get(index), lg1)
                    backgroundInsets = multi(box(0.px), box(0.px, 0.5.px, 0.5.px, 0.px))
                    and(empty) {
                        backgroundColor += Color.RED
                    }
                }
                tableCell {
                    borderColor += box(onPrimaryVariant.get(index))
                    padding = box(2.px, 0.px, 2.px, 10.px)
                    borderWidth = multi(box(0.5.px))

                }
                tableRowCell {
                    backgroundColor = multi(onPrimary.get(index), primary.get(index))
                    backgroundInsets = multi(box(0.px), box(0.px, 0.px, 0.5.px, 0.px))
                    padding = box(0.px)
                    text {
                        fill = onPrimary.get(index)
                    }
                    and(odd) {
                        backgroundColor = multi(onPrimary.get(index), secondary.get(index))
                    }
                    and(selected) {
                        backgroundColor = multi(onPrimary.get(index), primarySelected.get(index))
                        text {
                            fill = onPrimarySelected.get(index)
                        }
                    }
                }
                tableColumn {


                }

            }
        }

        // *******************************************************************
        // ********************* Intellij File Explorer    ***********************************
        // *******************************************************************

        listOf(intellijFileExplorerLight, intellijFileExplorerDark).forEachIndexed { index, cssRule ->

            val stops = listOf(Stop(0.0, primary.get(index)), Stop(1.0, secondary.get(index)));
            val lg1 = LinearGradient(0.0, 0.0, 0.0, 1.0, true, CycleMethod.NO_CYCLE, stops)

            cssRule {
                titledPane {
                    borderWidth = multi(box(0.5.px, 0.px, 0.5.px, 0.px))
                    borderColor = multi(box(onPrimaryVariant.get(index)))
                    borderInsets = multi(box(0.px))
                    borderRadius = multi(box(0.px))
                    backgroundInsets = multi(box(0.px))
                    backgroundColor = multi(lg1)
//
//                      text{
//                        fill = onPrimary.get(index)
//                        textFill = onPrimary.get(index)
//                    }
//                    arrow{
//                        backgroundColor += onPrimary.get(index)
//                    }
//                    and(focused){
//
//                    }
                    title {
                        borderColor = multi(box(Color.TRANSPARENT))
                        borderInsets = multi(box(0.px))
                        backgroundColor = multi(lg1)
                        text {
                            fill = onPrimary.get(index)
                            textFill = onPrimary.get(index)
                        }
                        arrowButton {
                            arrow {
                                backgroundColor += onPrimary.get(index)
                            }
                        }

                    }
                    content {
                        backgroundColor = multi(if (index == Light) secondary.get(index) else primary.get(index))
                        backgroundColor += Color.RED
                        padding = box(0.px)
                        borderWidth = multi(box(0.px))
                    }

                }


                treeView {
                    backgroundInsets = multi(box(0.px))
                    backgroundColor = multi(if (index == Light) secondary.get(index) else primary.get(index))
                    treeCell {
                        backgroundColor += Color.TRANSPARENT
                        text {
                            fill = onPrimary.get(index)
                            textFill = onPrimary.get(index)
                        }
                        arrow {
                            backgroundColor += onPrimary.get(index)
                        }
                        defaultFileIcon {
                            fill = onPrimaryVariant.get(index)
                            backgroundColor += onPrimaryVariant.get(index)
                        }
                        and(selected) {
                            backgroundColor += primarySelected.get(index)
                            text {
                                fill = onPrimarySelected.get(index)
                                textFill = onPrimarySelected.get(index)
                            }

                            arrow {
                                backgroundColor += onPrimarySelected.get(index)
                            }
                        }

                    }

                    scrollBar {
                        fontSize = 8.px
                        backgroundInsets = multi(box(0.px))
                        backgroundColor = multi(primary.get(index))
                        borderColor = multi(box(onPrimaryVariant.get(index)))
                        borderWidth = multi(box(0.5.px))
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


                    corner {
                        backgroundInsets = multi(box(0.px))
                        backgroundColor = multi(primary.get(index))
                        padding = box(0.px)

                    }








                }
            }
        }

    }
}




