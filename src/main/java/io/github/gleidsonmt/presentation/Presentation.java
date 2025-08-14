/*
 *
 *    Copyright (C) Gleidson Neves da Silveira
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package io.github.gleidsonmt.presentation;

import io.github.gleidsonmt.blockcode.BlockCode;
import io.github.gleidsonmt.blockcode.CodeType;
import io.github.gleidsonmt.blockcode.Theme;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebView;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Base class to create imperative presentations.
 * Used to create view without fxml and to fast way to create presentation.
 * It's a top and down blocks. Which blocks represents one method inside this class.
 * Ex. The method title(String title). Create a label, if I put another method
 * in sequence two titles will be created in a vbox layout.
 * More: this.title("One").title("Two").build(); creates a vbos with two titles.
 * If I want a text between ones -
 * this.title("One").text("between").title("Two").build();
 * indented:
 * this.title("One)
 * .text("Between")
 * .title("Two")
 * .build().
 * Create a node as like a document with sections and blocks.
 *
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  22/01/2023
 */

@ApiStatus.AvailableSince("1.0")
@SuppressWarnings({"unchecked", "unused"})
public class Presentation<T extends PresentationCreator> implements PresentationCreator {

    //Top and down root
    private final VBox body = new VBox();
    // items for add in a tree
    protected ObservableList<Node> items;
    // base from nodes
    protected final StackPane root;

    public Presentation() {
//        this.context = _context;
        this.root = new StackPane();
        this.root.getStyleClass().add("presentation");
        this.body.getStyleClass().add("presentation-body");
        items = FXCollections.observableArrayList();

        ScrollPane scroll = new ScrollPane();
        this.root.getChildren().setAll(scroll);
        scroll.setContent(body);

        scroll.setFitToHeight(true);
        scroll.setFitToWidth(true);

        body.setPadding(new Insets(0, 30, 30, 30));
        body.setSpacing(10);
    }

    private String title = null;

    /**
     * Create a title in tree.
     *
     * @param _title the name of the title
     * @return The Presentation.
     */
    public T title(String _title) {
        if (title == null) title = _title;
        items.add(createTitle(_title, null, "title", "bold", "h1"));
        return (T) this;
    }

    public T h1(String _title) {
        if (title == null) title = _title;
        items.add(createTitle(_title, null, "h1"));
        return (T) this;
    }

    public T h1(String _title, String parent) {
        if (title == null) title = _title;
        items.add(createTitle(_title, parent, "h1"));
        return (T) this;
    }

    public T h2(String _title) {
        if (title == null) title = _title;
        items.add(createTitle(_title, null, "h2"));
        return (T) this;
    }

    public T h2(String _title, String parent) {
        if (title == null) title = _title;
        items.add(createTitle(_title, parent, "h2"));
        return (T) this;
    }

    public T h3(String _title) {
        if (title == null) title = _title;
        items.add(createTitle(_title, null, "h3"));
        return (T) this;
    }

    public T h3(String _title, String parent) {
        if (title == null) title = _title;
        items.add(createTitle(_title, parent, "h3"));
        return (T) this;
    }

    public T h4(String _title) {
        if (title == null) title = _title;
        items.add(createTitle(_title, null, "h4"));
        return (T) this;
    }

    public T h4(String _title, String parent) {
        if (title == null) title = _title;
        items.add(createTitle(_title, parent, "h4"));
        return (T) this;
    }

    public T h5(String _title) {
        if (title == null) title = _title;
        items.add(createTitle(_title, null, "h5"));
        return (T) this;
    }

    public T h5(String _title, String parent) {
        if (title == null) title = _title;
        items.add(createTitle(_title, parent, "h5"));
        return (T) this;
    }

    public T h6(String _title) {
        if (title == null) title = _title;
        items.add(createTitle(_title, null, "h6"));
        return (T) this;
    }

    public T h6(String _title, String parent) {
        if (title == null) title = _title;
        items.add(createTitle(_title, parent, "h6"));
        return (T) this;
    }

    /**
     * Create a title and set its a parent.
     *
     * @param _title the name of title
     * @param parent the first parent.
     * @return The Presentation
     */
    public T title(String _title, String parent) {
        if (title == null) title = _title;
        items.add(createTitle(_title, parent, ""));
        return (T) this;
    }

    public T title(String _title, String parent, String... styles) {
        if (title == null) title = _title;
        items.add(createTitle(_title, parent, styles));
        return (T) this;
    }


    @ApiStatus.Experimental
    @Deprecated(forRemoval = true)
    public T subtitle(String title) {
        items.add(createSubTitle(title));
        return (T) this;
    }

    public T text(String text, String... options) {
        items.add(createText(text, options));
        return (T) this;
    }

    public T text(String text) {
        items.add(createText(text));
        return (T) this;
    }

    public T separator() {
        items.add(createSeparator());
        return (T) this;
    }

    public T image(Image image) {
        items.add(createImage(image));
        return (T) this;
    }

    /**
     * Redirect to a browser with url.
     * @param placeholder The text to show.
     * @param url The website to go.
     * @return A hyperlink.
     */
    public T link(String placeholder, String url) {
        items.add(createHyperlink(placeholder, url));
        return (T) this;
    }

    /**
     * The typical text 12.
     *
     * @param legend The text for legend.
     * @return This presentation.
     */
    public T legend(String legend) {
        items.add(createLabel(legend, "text-12", "text-bold"));
        return (T) this;
    }

    /**
     * Creates a block of code with highlight.
     *
     * @param text The text of code.
     * @return This Presentation
     */
    public T code(String text) {
        items.add(createBlockCode(CodeType.JAVA, text));
        return (T) this;
    }


    public T code(String text, String language) {
        Optional<CodeType> optional = Arrays.stream(CodeType.values()).filter(el -> el.toString().equalsIgnoreCase(language)).findAny();
        if (optional.isPresent()) {
            items.add(createBlockCode(optional.get(), text));
            return (T) this;
        } else throw new RuntimeException("Language specified doesn't have a match.");
    }

    @ApiStatus.Experimental
    @Deprecated
    public T codes(String java) {
        items.add(createTabs(java, null, null));
        return (T) this;
    }

    @ApiStatus.Experimental
    @Deprecated
    public T codes(String java, String fxml) {
        items.add(createTabs(java, fxml, null));
        return (T) this;
    }

    @ApiStatus.Experimental
    @Deprecated
    public T codes(String java, String fxml, String css) {
        items.add(createTabs(java, fxml, css));
        return (T) this;
    }

    @ApiStatus.Experimental
    public T youTube(String url) {

        WebView webView = new WebView();

//        webView.getEngine().load(url);
        webView.setMinSize(400, 400);
        webView.setPrefSize(400, 400);

//        webView.getEngine().load("https://www.youtube.com/watch?v=maX5ymmQixM");

//
        webView.getEngine().setJavaScriptEnabled(true);
//
        double width = 400;
        double height = 400;

//        src="https://www.youtube.com/embed/maX5ymmQixM"
        webView.getEngine().loadContent(
                "<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<body>" +
                "<iframe style='width: 100%;' height=\"" + height + "\"" +
//                "src=https://www.youtube.com/embed/maX5ymmQixM" +
                "src='" + url + "'" +
                """
                            title="JavaFX UI: iOS Style Toggle Switch"
                            frameborder="0" allow="accelerometer;
                            autoplay; clipboard-write; encrypted-media;
                            gyroscope; picture-in-picture; web-share" allowfullscreen>
                        </iframe>
                        """ +
                "</body>" +
                "</html>"
        );

        items.add(webView);
        return (T) this;
    }

    private void addClassesOrStyle(Node node, String... options) {
        StringBuilder builder = new StringBuilder();
        if (options != null) {
            for (String c : options) {
                if (c.startsWith("-fx-")) {
                    builder.append(c);
                } else {
                    node.getStyleClass().add(c);
                }
            }
        }

        node.setStyle(builder.toString());
    }

    @ApiStatus.Internal
    private Hyperlink createHyperlink(String placeholder, String url) {
        Hyperlink hyperlink = new Hyperlink(placeholder);
        hyperlink.setOnAction(e -> {
            try {
                Desktop.getDesktop().browse(new URI(url.startsWith("https") ? url : "https://" + url ));
            } catch (IOException | URISyntaxException ex) {
                throw new RuntimeException(ex);
            }
        });

        return hyperlink;
    }

    @ApiStatus.Internal
    protected BlockCode createBlockCode(CodeType codeType, String content) {
        return new BlockCode()
                .theme(Theme.GITHUB)
                .codeType(codeType)
                .content(content)
                .build();
    }

    @ApiStatus.Internal
    @Deprecated
    private Node createDemos(List<Node> nodes, String... classes) {
        FlowPane root = new FlowPane();
        root.getStyleClass().addAll(classes);
        root.setPadding(new Insets(20));
        root.setVgap(10);
        root.setHgap(10);
        root.setAlignment(Pos.CENTER_LEFT);
        for (Node node : nodes) {
            root.getChildren().add(node);
        }
        return root;
    }

    @ApiStatus.Internal
    private Node createDemo(Node... node) {
        FlowPane root = new FlowPane();
        root.setPadding(new Insets(20));
        root.setVgap(10);
        root.setHgap(10);
        root.setAlignment(Pos.CENTER_LEFT);
        root.getChildren().addAll(node);
        return root;
    }

    @ApiStatus.Experimental
    @Deprecated(forRemoval = true)
    public T footer(Author... authors) {
        items.add(createFooter(authors));
        return (T) this;
    }

    @ApiStatus.Experimental
    @Deprecated(forRemoval = true)
    public T footer(@NotNull ObservableList<Author> authors) {
        for (Author author : authors) {
            items.add(createFooter(author));
        }
        return (T) this;
    }


//    @ApiStatus.OverrideOnly
//    public ObservableList<Author> createDefaultControl() {
//        return FXCollections.observableArrayList(
//            new Author("OpenJFX",
//                    "https://github.com/openjfx/openjfx.github.io",
//                    "https://openjfx.io/javadoc/17/javafx.controls/javafx/scene/control/"+title+".html")
//            );
//    }

//    @ApiStatus.OverrideOnly
//    public Author createDefaultAuthor(String control) {
//       return new Author("OpenJFX",
//                        "https://github.com/openjfx/openjfx.github.io",
//                        "https://openjfx.io/javadoc/17/javafx.controls/javafx/scene/control/"+control+".html");
//    }

    @ApiStatus.Experimental
    @Deprecated(forRemoval = true)
    public Author createUserDefault() {
        return new Author("Gleidson Neves",
                "https://github.com/gleidsonmt",
                "https://github.com/gleidsonmt/DashboardFx");
    }

    @ApiStatus.Internal
    @ApiStatus.Experimental
    @Deprecated(forRemoval = true)
    private Node createFooter(Author... authors) {
        VBox root = new VBox();

        Label title = new Label("Author" + (authors.length > 1 ? "s" : ""));
        Separator separator = new Separator();
        HBox.setHgrow(separator, Priority.ALWAYS);
        HBox.setMargin(separator, new Insets(5, 0, 0, 0));

        HBox header = new HBox();
        header.setAlignment(Pos.CENTER);
        header.getChildren().setAll(title, separator);

        HBox body = new HBox();
        for (Author author : authors) {
            Hyperlink hp = new Hyperlink();
//            hp.setGraphic(new IconContainer(Icons.GITHUB));
            hp.setText(author.getName());
            hp.setGraphicTextGap(10);
//            hp.setOnAction(event -> context.openLink(author.getGitUrl()));
            body.getChildren().add(hp);

            if (author.getDocumentation() != null) {
                Hyperlink h = new Hyperlink();
                h.setText(" / Documentation");
//                h.setOnAction(event -> context.openLink(author.getDocumentation()));
                HBox.setMargin(h, new Insets(5, 0, 0, 0));
                h.setGraphicTextGap(10);
                body.getChildren().add(h);
            }

            if (authors.length > 1) {
                Separator sep = new Separator();
                sep.setOrientation(Orientation.VERTICAL);
                HBox.setMargin(sep, new Insets(0, 10, 0, 10));
                body.getChildren().add(sep);
            }
        }
        root.getChildren().setAll(header, body);
        root.setPadding(new Insets(10));
        return root;
    }

    public T demo(Node node) {
        items.add(createDemo(node));
        return (T) this;
    }

    public T demo(Node[] nodes) {
        items.add(createDemo(nodes));
        return (T) this;
    }

    public T demo(Node[] nodes, Code... codes) {
        items.add(createTabs(nodes, codes));
        return (T) this;
    }


    @ApiStatus.Experimental
    public T demo(Node nodes, Code... codes) {
        items.add(createTabs(new Node[]{nodes}, codes));
        return (T) this;
    }

    @Deprecated
    public T demo(List<Node> nodes, String... classes) {
        items.add(createDemos( nodes, classes));
        return (T) this;
    }

    @Deprecated
    public T demo(Node node, String java, String fxml) {
        items.add(createTabs(List.of(node), java, fxml, null));
        return (T) this;
    }

    @Deprecated
    public T demo(Node node, String java, String fxml, String css) {
        items.add(createTabs(List.of(node), java, fxml, css));
        return (T) this;
    }

    @Deprecated
    public T demonstration(List<Node> nodes, String java) {
        demonstration(nodes, java, null);
        return (T) this;
    }

    @Deprecated
    public T demonstration(List<Node> nodes) {
        demonstration(nodes, null, null);
        return (T) this;
    }

    @Deprecated
    public T demonstration(List<Node> nodes, String java, String fxml) {
        items.add(createTabs(nodes, java, fxml, null));
        return (T) this;
    }

    @Deprecated(forRemoval = true)
    public T demonstration(List<Node> nodes, String java, String fxml, String css) {
        items.add(createTabs(nodes, java, fxml, css));
        return (T) this;
    }

    /**
     * Add a custom node to the presentation.
     *
     * @param node The node to add.
     * @return T this presentation.
     */
    public T node(Node node) {
        items.add(node);
        return (T) this;
    }

    public T nodes(Node... nodes) {
        items.addAll(nodes);
        Arrays.stream(nodes).forEach(el -> VBox.setMargin(el, new Insets(10,0,10,0)));
        return (T) this;
    }

    /**
     * Create a table with two columns.
     * @param presentations The object to get the properties
     * @return T this presentation.
     */
    public T table(Row... presentations) {
        return table("Class", "Style", presentations);
    }

    /**
     *  Create a table with two columns.
     * @param columnOne First column name.
     * @param columTwo Second column name.
     * @param presentations  The object to get the properties
     * @return T this presentation.
     */
    public T table(String columnOne, String columTwo, Row... presentations) {
        TableView<Row> tableView = new TableView<>();
        tableView.getStyleClass().add("presentation-table");

        tableView.getItems().setAll(presentations);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        tableView.setMinHeight(200);
        TableColumn<Row, String> tableClass = new TableColumn<>(columnOne);
        TableColumn<Row, String> tableStyle = new TableColumn<>(columTwo);
        tableClass.setCellValueFactory(new PropertyValueFactory<>("property"));
        tableStyle.setCellValueFactory(new PropertyValueFactory<>("content"));

        tableView.getColumns().add(tableClass);
        tableView.getColumns().add(tableStyle);
        items.add(tableView);
        return (T) this;
    }

    private final String java = "";
    private final String fxml = "";
    private final String css = "";

    @ApiStatus.Experimental
    public T demonstration(List<Node> nodes, InputStream inputStream) {

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
                aux = "";
            }
            if (isJava) {
                java.append(aux).append("\n");
            } else if (isCss) {
                css.append(aux).append("\n");
            } else if (isFXML) {
                fxml.append(aux).append("\n");
            }
        }
//        items.add(createTabs(nodes));
//        Yaml yaml = new Yaml();
//        Map<String, Object> objectMap = yaml.load(inputStream);
//        objectMap.forEach((el, object) -> {
//            switch (el) {
//                case "java" -> java = object.toString();
//                case "css" -> css = object.toString();
//                case "fxml" -> fxml = object.toString();
//            }
//        });
        items.add(createTabs(nodes, java.toString(), fxml.toString(), css.toString()));
        return (T) this;
    }

    private Node createTabs(String java, String fxml, String css) {
        TabPane tabPane = new TabPane();

        if (java != null && !java.isBlank()) {
            Tab javaTab = new Tab("Java");
            tabPane.getTabs().add(javaTab);
            javaTab.setContent(createBlockCode(CodeType.JAVA, java));
        }

        if (fxml != null && !fxml.isBlank()) {
            Tab tab = new Tab("FXML");
            tabPane.getTabs().add(tab);
            tab.setContent(createBlockCode(CodeType.XML, fxml));
        }

        if (css != null && !css.isBlank()) {
            Tab tab = new Tab("FXML");
            tabPane.getTabs().add(tab);
            tab.setContent(createBlockCode(CodeType.CSS, css));
        }

        return tabPane;
    }

    @ApiStatus.Internal
    private Node createTabs(Node[] nodes, Code... codes) {
        VBox box = new VBox();

        FlowPane root = new FlowPane();

        root.setPadding(new Insets(20));
        root.setVgap(10);
        root.setHgap(10);
        root.getChildren().setAll(nodes);
        root.setAlignment(Pos.CENTER);

        TabPane tabPane = new TabPane();

        for (Code code : codes) {
            VBox.setVgrow(tabPane, Priority.ALWAYS);
//        tabPane.setPrefHeight(100);
            tabPane.setMinHeight(150);

            tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
//
            Tab javaTab = new Tab(code.toString());
            tabPane.getTabs().add(javaTab);
//            javaTab.setContent(createBlockCode(CodeType.JAVA, code.getContent()));
            javaTab.setContent(createBlockCode(code.getType(), code.getContent()));
        }

        box.getChildren().setAll(root, tabPane);

        root.setStyle("-fx-background-color: -light-gray;");

        return box;
    }

    @ApiStatus.Internal
    private Node createTabs(List<Node> list, String java, String fxml, String css) {

        VBox box = new VBox();

        FlowPane root = new FlowPane();

        root.setPadding(new Insets(20));
        root.setVgap(10);
        root.setHgap(10);
        root.getChildren().setAll(list);
        root.setAlignment(Pos.CENTER);

        if (java != null && (fxml != null || css != null)) {
            TabPane tabPane = new TabPane();
            VBox.setVgrow(tabPane, Priority.ALWAYS);
//        tabPane.setPrefHeight(100);
            tabPane.setMinHeight(150);

            tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
//
            Tab javaTab = new Tab("Java");
            tabPane.getTabs().add(javaTab);
            javaTab.setContent(createBlockCode(CodeType.JAVA, java));

            if (fxml != null && !fxml.isEmpty()) {
                Tab fxmlTab = new Tab("FXML");
                fxmlTab.setContent(createBlockCode(CodeType.XML, fxml));
                tabPane.getTabs().add(fxmlTab);
            }

            if (css != null && !css.isEmpty()) {
                Tab cssTab = new Tab("CSS");
                cssTab.setContent(createBlockCode(CodeType.CSS, css));
                tabPane.getTabs().add(cssTab);
            }

            box.getChildren().setAll(root, tabPane);
//        root.getStyleClass().addAll("border-light-gray-2", "border-1", "depth-2");

        } else if (java != null) {
            box.setSpacing(10);
            box.getChildren().setAll(root, createBlockCode(CodeType.JAVA, java));
        } else {
            box.setSpacing(10);
            box.getChildren().setAll(root);
        }
        root.setStyle("-fx-background-color: -light-gray;");


        return box;
    }

    @Deprecated(forRemoval = true)
    private @NotNull Label createSubTitle(String title) {
        return createLabel(title, "subtitle", "h2");
    }

    @ApiStatus.Internal
    private @NotNull Label createTitle(String title, String related, @NotNull String... styles) {

        TreeTitle label = new TreeTitle(title);
//        label.getStyleClass().addAll("title");

        if (related != null) {
            label.setRelated(new TreeTitle(related));
        }

       addClassesOrStyle(label, styles);
//        if (styleClass != null) label.getStyleClass().addAll(styleClass);

        VBox.setMargin(label, new Insets(20, 0, 10, 0));

        return label;

    }

    @ApiStatus.Internal
    private @NotNull Label createLabel(String text, String... styleClass) {
        Label label = new Label(text);
        label.setWrapText(true);
        addClassesOrStyle(label, styleClass);
        VBox.setMargin(label, new Insets(0, 0, 20, 0));
        return label;
    }

    @ApiStatus.Internal
    private @NotNull Node createImage(Image image) {
        Region region = new Region();
//        region.setMinSize(image.getWidth(), image.getHeight());
        region.setMinHeight(image.getHeight());
        region.setBackground(
                new Background(
                        new BackgroundImage(
                                image,
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundPosition.DEFAULT,
                                new BackgroundSize(300, 300, true, true, true, false)
//                                new BackgroundSize(100, 100, true, true, true, true)
                        )
                )
        );
        return region;
    }

    @ApiStatus.Internal
    private @NotNull Separator createSeparator() {
        return new Separator(Orientation.HORIZONTAL);
    }

    @ApiStatus.Internal
    @Contract("_ -> new")
    private @NotNull TextFlow createText(String _text) {
        Text text = new Text(_text);
        text.getStyleClass().addAll("text-14");
        return new TextFlow(text);
    }

    private @NotNull TextFlow createText(String _text, String... options) {
        Text text = new Text(_text);
        text.getStyleClass().add("text-14");
        TextFlow flow = new TextFlow(text);

        addClassesOrStyle(flow, options);

        return flow;
    }

    @Override
    public T build() {
        if (items.stream().noneMatch(n -> n.getStyleClass().contains("title"))) {
            body.setPadding(new Insets(30, 30, 30, 30));
        }

        body.getChildren().setAll(items);
        return (T) this;
    }

    @Override
    public Node getRoot() {
        return root;
    }


}


