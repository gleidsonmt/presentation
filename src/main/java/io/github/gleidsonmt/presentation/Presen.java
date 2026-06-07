package io.github.gleidsonmt.presentation;

import io.github.gleidsonmt.blockcode.BlockCode;
import io.github.gleidsonmt.blockcode.CodeType;
import io.github.gleidsonmt.blockcode.Theme;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Description:
 *
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on 07/06/2026
 */
public class Presen extends AbstractPresentation {

    //Top and down root
    private final VBox body = new VBox();
    // items for add in a tree
    protected ObservableList<Node> items;
    // base from nodes
    protected final StackPane root;

    private int idCount = 0;

    public Presen() {
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

    public Presen h1(String _title) {
        return h1(null, _title);
    }

    public Presen h1(Node graphic, String _title) {
        return createH(graphic, _title, "h1");
    }

    public Presen h2(String _title) {
        return h2(null, _title);
    }

    public Presen h2(Node graphic, String _title) {
        return createH(graphic, _title, "h2");
    }

    public Presen h3(String _title) {
        return h3(null, _title);
    }

    public Presen h3(Node graphic, String _title) {
        return createH(graphic, _title, "h3");
    }

    public Presen h4(String _title) {
        return h4(null, _title);
    }

    public Presen h4(Node graphic, String _title) {
        return createH(graphic, _title, "h4");
    }

    public Presen h5(String _title) {
        return h5(null, _title);
    }

    public Presen h5(Node graphic, String _title) {
        return createH(graphic, _title, "h5");
    }

    public Presen h6(String _title) {
        return h6(null, _title);
    }

    public Presen h6(Node graphic, String _title) {
        return createH(graphic, _title, "h6");
    }


    public Presen text(String text) {
        items.add(createText(text));
        return this;
    }

    public Presen image(Image image) {
        items.add(createImage(image));
        return this;
    }

    public Presen separator() {
        items.add(createSeparator());
        return this;
    }

    /**
     * The typical text 12.
     *
     * @param legend The text for legend.
     * @return This presentation.
     */
    public Presen legend(String legend) {
        items.add(createLabel(legend, "text-12", "text-bold"));
        return this;
    }

    /**
     * Creates a block of code with highlight.
     *
     * @param text The text of code.
     * @return This Presentation
     */
    public Presen code(String text) {
        items.add(createBlockCode(CodeType.JAVA, text));
        return this;
    }

    public Presen code(String text, String language) {
        Optional<CodeType> optional = Arrays.stream(CodeType.values()).filter(el -> el.toString().equalsIgnoreCase(language)).findAny();
        if (optional.isPresent()) {
            items.add(createBlockCode(optional.get(), text));
            return this;
        } else throw new RuntimeException("Language specified doesn't have a match.");
    }

    /**
     * Redirect to a browser with url.
     *
     * @param placeholder The text to show.
     * @param url         The website to go.
     * @return A hyperlink.
     */
    public Presen link(String placeholder, String url) {
        items.add(createHyperlink(placeholder, url));
        return this;
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
    private Presen createH(Node graphic, String _title, String clazz) {
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
        var parent = list.filter(tree -> tree.getIndex() == id - 1).findAny();
        if (parent.isEmpty()) return Optional.empty();
        var searched = parent.get();

        if (mineHClass(actual).equals(mineHClass(searched))) {
            return mineParent(actual, id - 1);
        } else {
            return parent;
        }
    }

    @ApiStatus.Internal
    private Presen createHead(Node graphic, String _title, String cssClasses) {
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
        TreeTitle label = new TreeTitle(title, "title-" + (id));
        label.setIndex(id);

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

    public Presen demo(Node node) {
        items.add(createDemo(node));
        return this;
    }

    public Presen demo(Node[] nodes) {
        items.add(createDemo(nodes));
        return  this;
    }

//    public Presen demo(Node[] nodes, Code... codes) {
//        items.add(createTabs(nodes, codes));
//        return this;
//    }

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
    public Presen build() {
        body.getChildren().setAll(items);
        return this;
    }

    @Override
    public Node getRoot() {
        return root;
    }
}
