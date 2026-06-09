module io.github.gleidsonmt.presentation {
    requires org.jetbrains.annotations;
    requires java.desktop;

    requires javafx.web;
    requires javafx.controls;
    requires javafx.media;
    requires javafx.fxml;

    requires transitive io.github.gleidsonmt.blockcode;

    exports io.github.gleidsonmt.presentation;
}