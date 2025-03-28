package io.github.gleidsonmt.presentation;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  24/02/2025
 */
@SuppressWarnings("unused")
public class Row {

    private final StringProperty property = new SimpleStringProperty();
    private final StringProperty content =new SimpleStringProperty();

    public Row(String property, String content) {
        this.property.set(property);
        this.content.set(content);
    }

    public String getProperty() {
        return property.get();
    }

    public StringProperty propertyProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property.set(property);
    }

    public String getContent() {
        return content.get();
    }

    public StringProperty contentProperty() {
        return content;
    }

    public void setContent(String content) {
        this.content.set(content);
    }
}
