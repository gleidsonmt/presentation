package io.github.gleidsonmt.presentation;

import javafx.scene.Node;

/**
 * Description:
 *
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Create on 07/06/2026
 */
public abstract class AbstractPresentation {

    /**
     * Call a method to add items and create a structure.
     * @return this build.
     */
    abstract AbstractPresentation build();

    /**
     * If needed for a layout, to pass a view or set a content.
     * @return the root inside the wrapper.
     */
    abstract Node getRoot();
}
