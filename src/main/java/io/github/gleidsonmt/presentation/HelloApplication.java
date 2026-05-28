package io.github.gleidsonmt.presentation;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
//import org.scenicview.ScenicView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Base class to create imperative presentations.
 * Used to create view without fxml and to fast way to create presentation.
 * It's a top and down blocks. Which blocks represents one method inside
 * this class.
 * Ex. the method title(String title). creates a label, if I put another method
 * in sequence two titles will be created in a vbox layout.
 * More: this.title("One").title("Two").build(); creates a vbos with two titles.
 * if I want a text between ones -
 * this.title("One").text("between").title("Two").build();
 * indented:
 * this.title("One)
 * .text("Between")
 * .title("Two")
 * .build().
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

//        Presentation presentation = new Presentation();
       SimplePresentation presentation = new SimplePresentation();

        VBox node = new VBox(new Button("X"));
        node.setMinHeight(200);

        presentation
//                .title("Titulo 1")
//                .separator()
//                .subtitle("Subtitle 1")
//                .h1("H1", "Titulo 1")
//                .h2("H2", "")
//                .h3("H3")
//                .h4("H4")
//                .h5("H5")
//                .h6("H6")
//                .text("Lorem ipsum dolor color")
//                .code("<button />", "html")
//                .demo(new Button("Welcome"))
//                .demonstration(new Button("Welcome"))
//                .demonstration(List.of(new Button("Button")), "Button btn = new Button(x);")
//                .demonstration(List.of(new Button("Button")), "Button btn = new Button(x);", "<button />")
//                .demonstration(List.of(new Button("Button")), getClass().getResourceAsStream("texts/buttons.txt"))
//                .image(new Image(Objects.requireNonNull(getClass().getResource("img/avatar.jpg")).toExternalForm()))

                /*     .legend("My legend")
                     .date(LocalDate.now())
                     .table(
                             new Row("h1", "-fx-font-size: 12"),
                             new Row("h2", "-fx-font-size: 12"),
                             new Row("h3", "-fx-font-size: 12"),
                             new Row("h4", "-fx-font-size: 12"),
                             new Row("h5", "-fx-font-size: 12"),
                             new Row("h6", "-fx-font-size: 12")
                     )
                     .demo(createDemo())
                     .codes("fxml", "", "")
                     .demonstration(List.of(new Button("Wow")), "java")
                     .demo(List.of(new Label("Lable"), new Label("Lable")))
                     .h1("Number one", null)
                     .h2("Number two", "Number one")
                     .link("Google", "google.com")

                     .youTube("https://www.youtube.com/embed/h1JTqEtnKgw?si=8MX3rs1cM9nxocdx")*/
//                .youTube("https://www.youtube.com/embed/maX5ymmQixM")
//                .h3("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum quis velit ut dolor vestibulum aliquet in non sem. Praesent vitae purus lobortis, commodo quam vehicula, sagittis enim. Quisque sed eros at augue luctus pharetra. Nam non dolor laoreet magna egestas rhoncus sed vitae arcu. Morbi interdum vestibulum nulla nec varius. Nullam sed risus nec justo accumsan dignissim non eget ante. Fusce dapibus nibh ante, sed placerat ante aliquet in. Nullam blandit tortor at tellus molestie feugiat. Proin blandit tortor id fermentum maximus. Morbi accumsan porttitor velit, a facilisis metus finibus ac. Cras a scelerisque magna, vitae facilisis est. Phasellus maximus tortor et lacus maximus rhoncus. In purus metus, hendrerit rhoncus lacinia eu, pharetra a felis. Sed ac neque vel dui dignissim elementum vitae ut ante.")
//                .youTube("https://www.youtube.com/embed/maX5ymmQixM")\
//                .demo(new Button(), "Button btn = new Button();", "<Button />", ".button {}")
//                .demo(new Button(), "Button btn = new Button();", "<Button />")
                // .demo(new Button(), new Code("code", .java)

                .demo(new Button("Button"))
                .demo(
                        new Button("Button"),
                        new Code("var x = new Button();")
                )
                .demo(new Button[]{

                }, new Code[]{

                })
                .demo(
                        new Button("Button"),
                        new Code("<button>")
                )
//                .load(yaml)
                .build();

//        PresentationBase<PresentationBase> pres = new PresentationBase<>();
//        pres
//                .title("Teste")
//                .demo("")
//                .build();

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
                aux = aux.substring(aux.indexOf("[") + 1, aux.length() - 1);
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


    }

    public static void main(String[] args) {
        launch();
    }
}