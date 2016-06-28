package com.thedevpiece.functions;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

@FunctionalInterface
public interface ComposableFunction<T, R> extends Function<T, R> {
    default Consumer<T> thenConsume(Consumer<R> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.accept(apply(t));
    }
}