package com.javafx.intellijtheme.components

import com.javafx.intellijtheme.MyStyles
import com.javafx.intellijtheme.intellij.IntellijStyles
import com.javafx.intellijtheme.replaceClassIf
import com.javafx.intellijtheme.setHeightExact
import com.javafx.intellijtheme.setWidthExact
import javafx.application.Platform
import javafx.collections.FXCollections
import javafx.geometry.Pos
import javafx.scene.control.ToggleGroup
import javafx.scene.layout.Priority
import javafx.scene.paint.Color
import javafx.scene.text.Font
import tornadofx.View
import tornadofx.*
import tornadofx.row
import java.time.LocalDate
import kotlin.concurrent.thread


class BasicControls : View() {

    val myWidth = 150.0

    override val root = gridpane {
        vgrow = Priority.ALWAYS
        hgrow = Priority.ALWAYS
        useMaxSize = true
        IntellijStyles.darkMode.addListener { _, _, isDark ->
            replaceClassIf(MyStyles.myGridPaneDark, MyStyles.myGridPaneLight, isDark )
            replaceClassIf(IntellijStyles.basicControlDark, IntellijStyles.basicControlLight,isDark)
        }
        row {

            stackpane {
                label("Button") {
                    stackpaneConstraints {
                        alignment = Pos.TOP_LEFT
                    }

                }
                button("Click Me") {}
            }
            stackpane() {
                label("Label")
                label("Read Me") {
                    IntellijStyles.darkMode.addListener { _, _, isDark ->
                        replaceClassIf(IntellijStyles.textOnPrimaryDark, IntellijStyles.textOnPrimaryLight, isDark)
                    }
                }
            }
            stackpane {
                label("Text Field")
                group {
                    textfield("Input something") {

                    }
                }
            }
            stackpane {
                label("Password Field")
                group {
                    passwordfield("password123") {
                        requestFocus()
                    }
                }
            }

            stackpane {
                label("Hyperlink")
                group {
                    hyperlink("Open File").action { println("Opening file...") }

                }
            }

            stackpane {
                label("Text")
                group {
                    text("Veni\nVidi\nVici") {
                        fill = Color.PURPLE
                        font = Font(20.0)
                    }

                }
            }


        }


        row {

            stackpane {
                label("Check Box")
                checkbox("check me") {
                    action { println(isSelected) }
                }
            }
            stackpane {
                label("Combo Box")
                group {
                    val texasCities = FXCollections.observableArrayList(
                        "Austin",
                        "Dallas", "Midland", "San Antonio", "Fort Worth"
                    )

                    combobox<String> {
                        items = texasCities
                    }
                }
            }
            stackpane {
                label("Toggel Button")
                group {
                    togglebutton("OFF") {
                        isSelected = false
                        action {
                            text = if (isSelected) "ON" else "OFF"
                        }
                    }
                }
            }
            stackpane {
                label("Radio Button")
                group {
                    val toggleGroup = ToggleGroup()

                     vbox(spacing = 5.0) {
                        padding = insets(0.0,10.0)
                        radiobutton("Employee", toggleGroup)
                        radiobutton("Contractor", toggleGroup)
                        radiobutton("Intern", toggleGroup)
                    }
                }
            }

            stackpane {
                label("Text Flow")
                group {
                    textflow {
                        text("Tornado") {
                            fill = Color.PURPLE
                            font = Font(20.0)
                        }
                        text("FX") {
                            fill = Color.ORANGE
                            font = Font(28.0)
                        }
                    }
                }
            }
            stackpane {
                label("Tooltip")
                group {
                    button("hover show") {
                        tooltip("Writes input to the database")
                    }

                }
            }


        }


        row {

            stackpane {
                label("Date Picker")
                group {
                    datepicker {
                        value = LocalDate.now()
                    }
                }
            }
            stackpane {
                label("Textarea")
                group {
//                    padding = insets(8.0)
                    textarea("Type memo here") {
                        setWidthExact(myWidth)
                        setHeightExact(myWidth)
                     }
                }
            }
            stackpane {
                label("Progressbar")
                group {
                    progressbar(0.5)
                }
            }
            stackpane {
                label("Progress indicator")
                group {
                    progressindicator {
                        thread {
                            for (i in 1..100) {
                                Platform.runLater { progress = i.toDouble() / 100.0 }
                                Thread.sleep(100)
                            }
                        }
                    }
                }
            }
            stackpane {
                label("Image View")

                vbox(alignment = Pos.CENTER) {
                    useMaxSize = true
                    setWidthExact(myWidth)
                    setHeightExact(myWidth)


                }

            }
            stackpane {
                label("Password Field")
                scrollpane {
                    setWidthExact(myWidth)
                    setHeightExact(myWidth)

                    imageview("java-logo.jpg") {
                        scaleX = 0.4
                        scaleY = 0.4
                    }
                }
            }

        }




        this.children.forEach {
            it.addClass(MyStyles.myGridPaneItem).gridpaneConstraints { this.vhGrow = Priority.ALWAYS }
            it.getChildList()!!.first().addClass(MyStyles.myGridPaneLable).stackpaneConstraints {
                alignment = Pos.TOP_LEFT;
                marginTop = -11.0
                marginLeft = 10.0
            }

        }
    }
}