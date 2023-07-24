package com.javafx.intellijtheme.intellij

import com.javafx.intellijtheme.MyStyles
import com.javafx.intellijtheme.components.ListView
import com.javafx.intellijtheme.fontIcon
import com.javafx.intellijtheme.getListMenuData
import com.javafx.intellijtheme.replaceClassIf
import javafx.event.EventTarget
import javafx.geometry.Pos
import javafx.scene.layout.CornerRadii
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import org.kordamp.ikonli.materialdesign2.MaterialDesignA
import org.kordamp.ikonli.materialdesign2.MaterialDesignE
import org.kordamp.ikonli.materialdesign2.MaterialDesignG
import tornadofx.*
import java.beans.EventHandler
import kotlin.math.exp

class ExpandableListMenu : VBox() {

    private val listMenuContainer: VBox = VBox()
    var elistMenu: ListMenu = ListMenu()
        set(value) {
            field = value
            listMenuContainer.children.clear()
            listMenuContainer.addChildIfPossible(value)
            value.apply {
                useMaxWidth = true
                paddingRight = 3.0
            }

            IntellijStyles.darkMode.addListener { _, _, isDark ->
                value.replaceClassIf(IntellijStyles.listMenuClassDark, IntellijStyles.listMenuClassLight, isDark)
            }
            value.childrenUnmodifiable.forEach {
                it.addClass(IntellijStyles.listMenuItemClass)

            }

        }

    init {



        useMaxHeight = true

        val myBox = this
        IntellijStyles.darkMode.addListener { _, _, newValue ->
            this.replaceClassIf(IntellijStyles.expandableListMenuDark, IntellijStyles.expandableListMenuLight, newValue)
        }
//        this.addClass(MyStyles.collapse)
        this.focusedProperty().addListener { _, _, focused ->
            if (focused) addClass(Stylesheet.focused) else removeClass(Stylesheet.focused)
        }
        this.setOnMouseClicked { this.requestFocus() }




        this.addChildIfPossible(listMenuContainer)



        button {
//            graphic = fontIcon(MaterialDesignA.ARROW_EXPAND_RIGHT, 32,IntellijStyles.onPrimaryVariant.light)
            useMaxSize = true
            vgrow = Priority.ALWAYS
            setOnMouseClicked {
                if (myBox.isFocused)
                    this.requestFocus()
                else
                    myBox.requestFocus()
            }

        }


    }
}

fun EventTarget.expandableListMenu(op: ExpandableListMenu.() -> Unit): ExpandableListMenu {
    val expandableListMenu = ExpandableListMenu()
    this.addChildIfPossible(expandableListMenu)
    expandableListMenu.op()

    return expandableListMenu;
}