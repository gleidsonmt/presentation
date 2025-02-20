package io.github.gleidsonmt.presentation;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Base class to create imperative presentations.
 * Used to create view without fxml and to fast way to create presentation.
 * It's a top and down blocks. Which blocks represents one method inside
 * this class.
 *      Ex. the method title(String title). creates a label, if I put another method
 * in sequence two titles will be created in a vbox layout.
 *      More: this.title("One").title("Two").build(); creates a vbos with two titles.
 * if I want a text between ones -
 *      this.title("One").text("between").title("Two").build();
 * indented:
 *      this.title("One)
 *          .text("Between")
 *          .title("Two")
 *          .build().
 * Create a node as like a document with sections and blocks.
 *
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  22/01/2024
 */
public class HelloApplication
//{
        extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Presentation presentation = new Presentation();
        VBox node = new VBox(new Button("X"));
        node.setMinHeight(200);
        presentation
                .title("Titulo 1")
                .subtitle("Subtitle 1")
                .h1("H1", "Titulo 1")
                .h2("H2", "")
                .h3("H3")
                .h4("H4")
                .h5("H5")
                .h6("H6")
                .text("Lorem ipsum dolor color")
                .code("<button />", "html")
                .demo(new Button("Welcome"))
                .demonstration(new Button("Welcome"))
                .demonstration(List.of(new Button("Button")), "Button btn = new Button(x);")
                .demonstration(List.of(new Button("Button")), "Button btn = new Button(x);", "<button />")
                .demonstration(List.of(new Button("Button")), getClass().getResourceAsStream("texts/buttons.txt"))
                .node(new Button("Custom Node"))
                .image(new Image(Objects.requireNonNull(getClass().getResource("img/avatar.jpg")).toExternalForm()))
//                .youTube("", "")
                .build();


        Scene scene = new Scene((Parent) presentation.getRoot(), 800, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

//        read();
//        ScenicView.show(scene);
    }

    void read() {
            InputStream inputStream = getClass().getResourceAsStream("texts/buttons.txt");
            Scanner scanner = new Scanner(inputStream);
            StringBuilder java = new StringBuilder();
            StringBuilder css = new StringBuilder();
            StringBuilder fxml = new StringBuilder();
            boolean isJava = false;
            boolean isCss = false;
            boolean isFXML = false;
            while (scanner.hasNext()) {
                String aux = scanner.nextLine();
                if (aux.trim().startsWith("[")) {
                    System.out.println("aux = " + aux);
                    aux = aux.substring(aux.indexOf("[")+1, aux.length() -1);
                    if (aux.equalsIgnoreCase("java")) {
                        isJava = true;
                        isCss = false;
                        isFXML = false;
                    } else if (aux.equalsIgnoreCase("css")) {
                        isJava = false;
                        isCss = true;
                        isFXML = false;
                    } else {
                        isJava = false;
                        isCss = false;
                        isFXML = true;
                    }
                }
                    if (isJava) {
                        java.append(aux).append("\n");
                    } else if (isCss) {
                        css.append(aux).append("\n");
                    } else if (isFXML) {
                        fxml.append(aux).append("\n");
                }
            }
        System.out.println("java = " + java);
        System.out.println("css = " + css);
        System.out.println("fxl = " + fxml);

    }

    public static void main(String[] args) {
        launch();
    }
}