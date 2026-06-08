module io.github.gleidsonmt.presentation {
    requires org.jetbrains.annotations;
    requires java.desktop;
    requires jdk.compiler;
    requires jdk.jsobject;

    requires javafx.web;
    requires javafx.controls;
    requires javafx.media;
    requires javafx.fxml;

    requires io.github.gleidsonmt.blockcode;

    exports io.github.gleidsonmt.presentation;
}