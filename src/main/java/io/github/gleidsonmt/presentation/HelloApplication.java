package io.github.gleidsonmt.presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.scenicview.ScenicView;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        PresentationCreator presentation = new PresentationCreator();
        presentation
                .title("WOW")
                .build();


        Scene scene = new Scene((Parent) presentation.getRoot(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        ScenicView.show(scene);
    }

    public static void main(String[] args) {
        launch();
    }
}