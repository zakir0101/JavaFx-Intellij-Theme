package com.javafx.intellijtheme

import com.javafx.intellijtheme.components.*
import com.javafx.intellijtheme.components.Form
import com.javafx.intellijtheme.intellij.IntellijStyles.Companion.getColor
import com.javafx.intellijtheme.intellij.IntellijTabPane
import com.javafx.intellijtheme.intellij.IntellijDrawer
import com.javafx.intellijtheme.intellij.IntellijStyles
import javafx.scene.Group
import javafx.scene.Node
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import org.kordamp.ikonli.javafx.FontIcon
import org.kordamp.ikonli.materialdesign2.*
import tornadofx.*


//*********************************************************************
//*************************** DATA CLASSED ****************************
//*********************************************************************

data class UIIItem(val name: String, val icon: FontIcon, val node: Node)



//*********************************************************************
//*************************** DATA CLASSED ****************************
//*********************************************************************


fun getListMenuData() = listOf(


    UIIItem(
        "Basic Controls",
        FontIcon().from(MaterialDesignF.FACEBOOK, 22, IntellijStyles.tertiary3.getColor()),
        AnchorPane().apply {
            useMaxSize = true
            vgrow = Priority.ALWAYS
            IntellijStyles.darkMode.addListener { _, _, isDark ->
                replaceClassIf(
                    IntellijStyles.basicControlDark,
                    IntellijStyles.basicControlLight,
                    isDark
                )
            }

            scrollpane {

                this.isFitToHeight = true
                this.isFitToWidth = true
                style {
                    anchorpaneConstraints {
                        topAnchor = 0.0; bottomAnchor = 0.0
                        leftAnchor = 0.0; rightAnchor = 0.0
                    }
                }
                addChildIfPossible(find(BasicControls::class).root)
            }
        }

    ),
    UIIItem(
        "Data Controls",
        FontIcon().from(MaterialDesignN.NAVIGATION, 22, IntellijStyles.tertiary2.getColor()),
        VBox(find(IntellijDrawer::class).root.apply {
            useMaxHeight = false
//            maxHeight = 300.0
//            minHeight = 300.0
            vgrow = Priority.ALWAYS
        }
        ).apply { }
    ),
    UIIItem(
        "Layout and Menus",
        FontIcon().from(MaterialDesignG.GOOGLE, 22, IntellijStyles.tertiary1.getColor()),
        find(IntellijTabPane::class).root
    )
)






//*********************************************************************
//*************************** DATA CLASSED ****************************
//*********************************************************************

fun getTabPaneData() = listOf(
    UIIItem(
        "Accordion",
        FontIcon().from(MaterialDesignH.HOME),
        Group(find(Accordion::class).root)
    ),
    UIIItem(
        "ListView",
        FontIcon().from(MaterialDesignE.EMAIL_EDIT),
        Group(find(ListView::class).root)
    ),
    UIIItem(
        "Form",
        FontIcon().from(MaterialDesignA.ACCOUNT_PLUS),
        Group(find(Form::class).root)
    ),
    UIIItem(
        "GridPane",
        FontIcon().from(MaterialDesignG.GRID),
        Group(find(GridPaneView::class).root)
    ),
    UIIItem(
        "VBoxHBox",
        FontIcon().from(MaterialDesignA.ACCOUNT_BOX),
        VBoxHBox().apply { useMaxSize = true; vgrow = Priority.ALWAYS }
    )
)
