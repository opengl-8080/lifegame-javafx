package gl8080.lifegame.util;

@FunctionalInterface
public interface TriConsumer<S, T, U> {
    
    void accept(S s, T t, U u);
}
