package io.github.gleidsonmt.presentation;

import io.github.gleidsonmt.blockcode.CodeType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  28/03/2025
 */
@SuppressWarnings("unused")
public record Code(String content, CodeType type) {

    @Contract(pure = true)
    public Code {
    }

    @Contract(pure = true)
    public Code(String content) {
        this(content, CodeType.JAVA);
    }

    public Code(String content, @NotNull String type) {
        this(content, CodeType.valueOf(type.toUpperCase()));
    }

    @Override
    public @NotNull String toString() {
        String name = type().toString();
        return name.charAt(0) + name.substring(1).toLowerCase();
    }
}
