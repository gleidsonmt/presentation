package io.github.gleidsonmt.presentation;

//import io.github.gleidsonmt.core.Context;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  04/10/2024
 */
public class Test extends BorderPane {

    private StackPane fuck = new StackPane();

    public Test() {
        fuck.setPrefWidth(250);
        Button btn = new Button("Click on me");
        setRight(fuck);
        VBox center = new VBox();
        fuck.getChildren().add(new Label("Click on me"));
        center.getChildren().add(btn);

        center.setPrefWidth(500);
        center.setStyle("-fx-background-color: red;");
        fuck.setStyle("-fx-background-color: blue;");
        setCenter(center);
        btn.setOnMouseClicked(e -> {

        });
//        Context context = (Context) System.getProperties().get("context");
        widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if (newValue.doubleValue() < 735) {
//                    System.out.println("context.root().getChildren().contains(fuck) = " + context.root().getChildren().contains(fuck));

                    setRight(null);
                } else {
                        Platform.runLater(() -> {
                            setRight(fuck);

                        });
//                        context.root().removeChild(fuck);

                }
            }
        });

        btn.setOnMouseClicked(e -> {

//            context.flow().slideInFromRight(fuck);
        });

    }
}
