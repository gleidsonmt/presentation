package io.github.gleidsonmt.presentation;

import io.github.gleidsonmt.blockcode.CodeType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author Gleidson Neves da Silveira | <a href="mailto:gleidisonmt@gmail.com">gleidisonmt@gmail.com</a> <br>
 * Created on 07/06/2026
 */
@SuppressWarnings("unused")
public record Code(String name, String content, CodeType type) {

    @Contract(pure = true)
    public Code(String content) {
        this("Java", content, CodeType.JAVA);
    }

    @Contract(pure = true)
    public Code(String name, String content) {
        this(name, content, CodeType.JAVA);
    }

    @Contract(pure = true)
    public Code(String content, CodeType type) {
        this(type.name(), content, type);
    }

    public Code(String name,String content, @NotNull String type) {
        this(name, content, CodeType.valueOf(type.toUpperCase()));
    }

    @Override
    public @NotNull String toString() {
        String name = type().toString();
        return name.charAt(0) + name.substring(1).toLowerCase();
    }
}
