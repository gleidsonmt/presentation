package io.github.gleidsonmt.presentation;

import io.github.gleidsonmt.blockcode.BlockCode;
import io.github.gleidsonmt.blockcode.CodeType;
import io.github.gleidsonmt.blockcode.Theme;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Optional;

/**
 * Use scaffolding to create a structure of presentation using java based on the design system.
 *
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Created on  7 Jun 2026
 */
public class Presentation extends AbstractPresentation {
    //Top and down root
    private final VBox body = new VBox();
    // items for add in a tree
    protected ObservableList<Node> items;
    // base from nodes
    protected final StackPane root;

    private int idCount = 0;

    public Presentation() {
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

        items.addListener((ListChangeListener<Node>) c -> {
            if (c.next()) {
                if (c.wasAdded()) {
                    c.getAddedSubList().forEach(item -> VBox.setVgrow(item, Priority.ALWAYS));
                }
            }
        });
    }

    private String title = null;

    // h1 styles
    // -- Options --
    // Node graphic argument
    // String title argument
    // String parent * fixed
    // classes * fixed


    public Presentation h1(String _title) {
        return h1(null, _title);
    }

    public Presentation h1(Node graphic, String _title) {
        return createH(graphic, _title, "h1");
    }

    public Presentation h2(String _title) {
        return h2(null, _title);
    }

    public Presentation h2(Node graphic, String _title) {
        return createH(graphic, _title, "h2");
    }

    public Presentation h3(String _title) {
        return h3(null, _title);
    }

    public Presentation h3(Node graphic, String _title) {
        return createH(graphic, _title, "h3");
    }

    public Presentation h4(String _title) {
        return h4(null, _title);
    }

    public Presentation h4(Node graphic, String _title) {
        return createH(graphic, _title, "h4");
    }

    public Presentation h5(String _title) {
        return h5(null, _title);
    }

    public Presentation h5(Node graphic, String _title) {
        return createH(graphic, _title, "h5");
    }

    public Presentation h6(String _title) {
        return h6(null, _title);
    }

    public Presentation h6(Node graphic, String _title) {
        return createH(graphic, _title, "h6");
    }


    public Presentation text(String text) {
        items.add(createText(text));
        return this;
    }

    public Presentation text(String title, String... clazzes) {
        items.add(createText(title, clazzes));
        return this;
    }

    public Presentation image(Image image) {
        items.add(createImage(image));
        return this;
    }

    public Presentation separator() {
        items.add(createSeparator());
        return this;
    }

    /**
     * The typical text 12.
     *
     * @param legend The text for legend.
     * @return This presentation.
     */
    public Presentation legend(String legend) {
        items.add(createLabel(legend, "text-12", "text-bold"));
        return this;
    }

    /**
     * Creates a block of code with highlight.
     *
     * @param text The text of code.
     * @return This Presentation
     */
    public Presentation code(String text) {
        items.add(createBlockCode(CodeType.JAVA, text));
        return this;
    }

    public Presentation code(String text, String language) {
        Optional<CodeType> optional = Arrays.stream(CodeType.values()).filter(el -> el.toString().equalsIgnoreCase(language)).findAny();
        if (optional.isPresent()) {
            items.add(createBlockCode(optional.get(), text));
            return this;
        } else throw new RuntimeException("Language specified doesn't have a match.");
    }

    /**
     * Add a custom node to the presentation.
     *
     * @param node The node to add.
     * @return T this presentation.
     */
    public Presentation node(Node node) {
        items.add(node);
        return this;
    }

    public Presentation nodes(Node... nodes) {
        items.addAll(nodes);
        Arrays.stream(nodes).forEach(el -> VBox.setMargin(el, new Insets(10, 0, 10, 0)));
        return this;
    }

    /**
     * Create a table with two columns.
     *
     * @param presentations The object to get the properties
     * @return T this presentation.
     */
    @ApiStatus.Experimental
    public Presentation table(Row... presentations) {
        return table("Class", "Style", presentations);
    }

    /**
     * Create a table with two columns.
     *
     * @param columnOne     First column name.
     * @param columTwo      Second column name.
     * @param presentations The object to get the properties
     * @return T this presentation.
     */
    public Presentation table(String columnOne, String columTwo, Row... presentations) {
        TableView<Row> tableView = new TableView<>();
        tableView.getStyleClass().add("presentation-table");

        tableView.getItems().setAll(presentations);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        tableView.setMinHeight(300);
        TableColumn<Row, String> tableClass = new TableColumn<>(columnOne);
        TableColumn<Row, String> tableStyle = new TableColumn<>(columTwo);
        tableClass.setCellValueFactory(new PropertyValueFactory<>("property"));
        tableStyle.setCellValueFactory(new PropertyValueFactory<>("content"));

        tableView.getColumns().add(tableClass);
        tableView.getColumns().add(tableStyle);
        items.add(tableView);
        return this;
    }

    /**
     * Redirect to a browser with url.
     *
     * @param placeholder The text to show.
     * @param url         The website to go.
     * @return A hyperlink.
     */
    public Presentation link(String placeholder, String url) {
        items.add(createHyperlink(placeholder, url));
        return this;
    }

    @ApiStatus.Experimental
    public Presentation youTube(String url) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        // 1. Ative o JavaScript explicitamente
        webEngine.setJavaScriptEnabled(true);

        // 2. Altere o User-Agent para simular um navegador comum (Evita bloqueios)
        String htmlString = """
                <html>
                <head>
                    <meta name="referrer" content="strict-origin-when-cross-origin">
                </head>
                <body style="margin:0; padding:0; background-color:black;">
                
                    <iframe width="100%" height="100%"\s
                            src="https://www.youtube.com/embed/6bgkdQxetl0"\s
                            title="YouTube video player"\s
                            frameborder="0"\s
                            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"\s
                            referrerpolicy="strict-origin-when-cross-origin"\s
                            allowfullscreen>
                    </iframe>
                
                </body>
                </html>
                """;

        // 3. Força uma URL base segura (https) para que o iframe herde um contexto de segurança válido
//        webEngine.loadContent(htmlString, "text/html");
        webEngine.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");

        webEngine.loadContent(htmlString);
        webView.getEngine().setCreatePopupHandler(handler -> {
            return webView.getEngine(); // Força o iframe a renderizar na mesma tela
        });

        items.add(webView);
        return this;
    }

    // Bridge class must be public for JavaScript to reach it
    @ApiStatus.Experimental
    private class Bridge {
        public void log(String text) {
            System.out.println("JS Log: " + text);
        }

        public void error(String text) {
            System.err.println("JS Error: " + text);
        }
    }

    @ApiStatus.Internal
    protected BlockCode createBlockCode(CodeType codeType, String content) {
        var block = new BlockCode()
                .theme(Theme.GITHUB)
                .codeType(codeType)
                .content(content)
                .build();
        VBox.setMargin(block, new Insets(10));
        return block;
    }

    @ApiStatus.Internal
    private @NotNull Label createLabel(String text, String... styleClass) {
        javafx.scene.control.Label label = new Label(text);
        label.setWrapText(true);
        addClassesOrStyle(label, styleClass);
        VBox.setMargin(label, new Insets(0, 0, 20, 0));
        return label;
    }

    @ApiStatus.Internal
    private Hyperlink createHyperlink(String placeholder, String url) {
        Hyperlink hyperlink = new Hyperlink(placeholder);
        hyperlink.setOnAction(e -> {
            try {
                Desktop.getDesktop().browse(new URI(url.startsWith("https") ? url : "https://" + url));
            } catch (IOException | URISyntaxException ex) {
                throw new RuntimeException(ex);
            }
        });

        return hyperlink;
    }

    @ApiStatus.Internal
    private Presentation createH(Node graphic, String _title, String clazz) {
        return createHead(graphic, _title, clazz);
    }

    @ApiStatus.Internal
    private @NotNull TextFlow createText(String _text, String... options) {
        Text text = new Text(_text);
        text.getStyleClass().add("text-14");
        TextFlow flow = new TextFlow(text);

        addClassesOrStyle(flow, options);

        return flow;
    }

    @ApiStatus.Internal
    private Optional<TreeTitle> mineParent(TreeTitle actual, int id) {

        var list = items.stream().filter(node -> node instanceof TreeTitle).map(node -> (TreeTitle) node);
        var parent = list.filter(tree -> Integer.parseInt(tree.getId()) == id - 1).findAny();
        if (parent.isEmpty()) return Optional.empty();
        var searched = parent.get();

        var act = Integer.parseInt(mineHClass(actual).get().replaceAll("[^0-9]", ""));
        var val = Integer.parseInt(mineHClass(searched).get().replaceAll("[^0-9]", ""));

        
        if (act == val || act < val ) {
            return mineParent(actual, id - 1);
        } else {
            return parent;
        }
    }

    @ApiStatus.Internal
    private Presentation createHead(Node graphic, String _title, String cssClasses) {
        if (title == null) title = _title;
        items.add(createTreeTitle(graphic, _title, cssClasses));
        return this;
    }

    @ApiStatus.Internal
    private @NotNull TreeTitle createTreeTitle(String title, String... styles) {
        return createTreeTitle(null, title, styles);
    }

    private Optional<String> mineHClass(TreeTitle title) {
        return title.getStyleClass().stream().filter(clazz -> clazz.matches("h+[0-9]")).findAny();
    }

    @ApiStatus.Internal
    private @NotNull TreeTitle createTreeTitle(Node graphic, String title, String... styles) {
        var id = idCount++;
        TreeTitle label = new TreeTitle(title, String.valueOf(id));
        label.setIndex(String.valueOf(id));

        if (graphic != null) label.setGraphic(graphic);

        addClassesOrStyle(label, styles);

        if (label.getStyleClass().contains("h1")) {
            label.setRelated(null);

        } else {
            Optional<TreeTitle> optionalParent = mineParent(label, id);
            optionalParent.ifPresent(label::setRelated);
        }

        VBox.setMargin(label, new Insets(20, 0, 10, 0));
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
        Separator separator = new Separator(Orientation.HORIZONTAL);
        VBox.setMargin(separator, new Insets(10, 0, 10, 0));

        return separator;
    }

    public Presentation demo(Node node) {
        items.add(createDemo(node));
        return this;
    }

    public Presentation demo(Node[] nodes) {
        items.add(createDemo(nodes));
        return this;
    }

    public Presentation demo(Node[] nodes, Code... codes) {
        items.add(createTabs(nodes, codes));
        return this;
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
            Tab tab = new Tab(code.toString());
            tabPane.getTabs().add(tab);
            tab.setContent(createBlockCode(code.type(), code.content()));
        }

        box.getChildren().setAll(root, tabPane);

        return box;
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

    @Override
    public Presentation build() {
        body.getChildren().setAll(items);
        return this;
    }

    @Override
    public Node getRoot() {
        return root;
    }
}
