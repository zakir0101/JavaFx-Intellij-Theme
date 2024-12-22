module com.javafx.intellijtheme {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.materialdesign2;
//    requires eu.mihosoft.monacofx;
    requires kotlin.stdlib;
    requires tornadofx;
    requires java.desktop;
    requires kotlin.stdlib.jdk7;
    requires jdk.jsobject;
    opens com.javafx.intellijtheme to javafx.fxml;
    opens com.javafx.intellijtheme.intellij to javafx.fxml ;
    opens com.javafx.intellijtheme.components to javafx.fxml;


    exports com.javafx.intellijtheme;
    exports com.javafx.intellijtheme.intellij;
    exports com.javafx.intellijtheme.components;


}