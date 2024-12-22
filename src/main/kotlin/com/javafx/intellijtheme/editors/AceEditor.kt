package com.javafx.intellijtheme.editors

import com.javafx.intellijtheme.intellij.IntellijStyles
import javafx.beans.property.SimpleStringProperty
import javafx.concurrent.Worker
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import javafx.scene.web.WebView
import tornadofx.addChildIfPossible
import tornadofx.vgrow
import netscape.javascript.JSObject
import java.nio.file.Path
import kotlin.io.path.readText

class AceEditor(file : Path) : VBox() {
    val webView = WebView()
    val engine = webView.engine
    var javascriptConnector: JSObject? = null
    var javaConnnector: JavaConnector = JavaConnector()

    init {
        engine.load(javaClass.getResource("/ace/editor.html")?.toExternalForm() ?: "")
        webView.apply {
            vgrow = Priority.ALWAYS
        }
        this.addChildIfPossible(webView)
        engine.loadWorker.stateProperty().addListener { observable, oldValue, newValue ->
            if (Worker.State.SUCCEEDED == newValue) {
                val window = engine.executeScript("window") as JSObject
                window.setMember("javaConnector", javaConnnector)
                javascriptConnector = engine.executeScript("getJsConnector()") as JSObject

                this.sendTextToJs(file.readText())
                IntellijStyles.darkMode.addListener { _, _, isDark ->
                    if (isDark) this.setDarkMode() else this.setLightMode()
                }
                if (IntellijStyles.darkMode.get()) this.setDarkMode() else this.setLightMode()
                val fileName = file.fileName.toString()
                when {
                    fileName.endsWith(".py") -> setLangPython()
                    fileName.endsWith(".kt") -> setLangKotlin()
                    fileName.endsWith(".java") ->setLangJava()
                }




            }
        }
    }

    fun sendTextToJs(text: String) {
        if (javascriptConnector != null)
            javascriptConnector!!.call("sendTextToJs", text)
    }

    fun setLightMode() {
        engine.executeScript("setLightMode()")
    }

    fun setDarkMode() {
        engine.executeScript("setDarkMode()")
    }

    fun setLangJava() {
        engine.executeScript("setLangJava()")
    }

    fun setLangKotlin() {
        engine.executeScript("setLangKotlin()")
    }

    fun setLangPython() {
        engine.executeScript("setLangPython()")
    }

    fun setLangJavascript() {
        engine.executeScript("setLangJavascript()")
    }

    fun setLangHtml() {
        engine.executeScript("setLangHtml()")
    }

    fun setLangCss() {
        engine.executeScript("setLangCss()")
    }
}


class JavaConnector {
    val code = SimpleStringProperty("")
    fun sendTextToJava(text: String?) {
        if (text != null) {
            code.set(text)
        }
    }
}