module io.github.gleidsonmt.presentation {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;
    requires scenicView;

    opens io.github.gleidsonmt.presentation to javafx.fxml;
    exports io.github.gleidsonmt.presentation;
}