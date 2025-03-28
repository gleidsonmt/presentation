package io.github.gleidsonmt.presentation;

import io.github.gleidsonmt.blockcode.CodeType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  28/03/2025
 */
public class Code {

    private final String content;
    private final CodeType type;

    @Contract(pure = true)
    public Code(String content) {
        this(content, CodeType.JAVA);
    }

    public Code(String content, @NotNull String type) {
        this(content, CodeType.valueOf(type.toUpperCase()));
    }

    @Contract(pure = true)
    public Code(String content, CodeType type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public CodeType getType() {
        return type;
    }

    @Override
    public String toString() {
        String name = getType().toString();
        return name.charAt(0) + name.substring(1).toLowerCase();
    }
}
