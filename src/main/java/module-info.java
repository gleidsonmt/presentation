module io.github.gleidsonmt.presentation {
    requires org.jetbrains.annotations;
    requires java.desktop;
    requires io.github.gleidsonmt.blockcode;
    requires jdk.compiler;

    opens io.github.gleidsonmt.presentation to javafx.fxml;
    exports io.github.gleidsonmt.presentation;
}