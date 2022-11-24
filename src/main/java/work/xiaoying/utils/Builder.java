package work.xiaoying.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 构建器
 *
 * @author 小樱
 * @date 2022/11/24
 */
public class Builder<T> {

    private final Supplier<T> constructor;

    private List<Consumer<T>> operators=new ArrayList<>();

    private Builder(Supplier<T> constructor){
        this.constructor=constructor;
    }

    @Contract("_ -> new")
    public static <T> @NotNull Builder<T> builder(Supplier<T> constructor){
        return new Builder<>(constructor);
    }

    public <V> Builder<T> with(BiConsumer<T,V> consumer,V v){
        operators.add(instance->consumer.accept(instance,v));
        return this;
    }

    public T build(){
        T instance=constructor.get();
        operators.forEach(dInject->dInject.accept(instance));
        return instance;
    }

}
