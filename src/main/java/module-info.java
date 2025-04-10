module io.github.gleidsonmt.presentation {
    requires org.jetbrains.annotations;

    requires io.github.gleidsonmt.blockcode;
    requires org.scenicview.scenicview;

    opens io.github.gleidsonmt.presentation to javafx.fxml;
    exports io.github.gleidsonmt.presentation;
    exports io.github.gleidsonmt.presentation.internal;
    opens io.github.gleidsonmt.presentation.internal to javafx.fxml;
}