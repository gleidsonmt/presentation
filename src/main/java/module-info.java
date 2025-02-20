module io.github.gleidsonmt.presentation {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;
//    requires scenicView;

//    requires io.github.gleidsonmt.core;
    requires io.github.gleidsonmt.blockcode;
    requires org.yaml.snakeyaml;


    opens io.github.gleidsonmt.presentation to javafx.fxml;
    exports io.github.gleidsonmt.presentation;
}