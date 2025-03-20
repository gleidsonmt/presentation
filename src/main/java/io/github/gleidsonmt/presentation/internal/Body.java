package io.github.gleidsonmt.presentation.internal;

import io.github.gleidsonmt.presentation.TreeTitle;
import javafx.scene.layout.VBox;

import java.util.Optional;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  18/03/2025
 */
public class Body extends VBox {

    public Body() {

    }

    public Optional<TreeTitle> find(String name) {
        Optional<TreeTitle> optional = getChildren().stream().filter(el -> el instanceof TreeTitle)
                .map(el -> (TreeTitle) el)
                .filter(el -> el.getText().equals(name))
                .findAny();
        return optional;
    }

//    public void find();
}
