package io.github.gleidsonmt.presentation;

import javafx.scene.Node;
import org.jetbrains.annotations.ApiStatus;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Created on 08/06/2026
 */
public class PresentationImpl{

    public PresentationImpl h1(String _title) {
        return h1(null, _title);
    }

    public PresentationImpl h1(Node graphic, String _title) {
        return createH(graphic, _title, "h1");
    }

    @ApiStatus.Internal
    private PresentationImpl createH(Node graphic, String _title, String clazz) {
//        return createHead(graphic, _title, clazz);
        return null;
    }



}
