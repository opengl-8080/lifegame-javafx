package gl8080.lifegame.util;

/**
 * 2つのint値オペランドを受け取って結果を返さないオペレーションを表します。
 */
@FunctionalInterface
public interface BiIntSupplier<T> {
    
    T get(int i, int j);
}
