package gl8080.lifegame.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * 二重ループをラムダで実行できるようにするためのクラス。
 * <p>
 * このクラスが扱う二重ループは、縦と横が同じ大きさのループになります。
 */
public class NestedLoop {
    
    /**
     * 二重ループを実行して、入れ子の {@code List} を生成する。
     * @param size サイズ
     * @param supplier 入れ子の {@code List} に入れる各要素を供給する処理
     * @return 入れ子の {@code List}
     */
    public static <T> List<List<T>> collectList(int size, BiIntSupplier<T> supplier) {
        List<List<T>> matrix = new ArrayList<>();
        
        for (int i=0; i<size; i++) {
            List<T> row = new ArrayList<>();
            for (int j=0; j<size; j++) {
                row.add(supplier.get(i, j));
            }
            matrix.add(row);
        }
        
        return matrix;
    }
    
    /**
     * 二重ループを実行して、 {@code Map} を生成する。
     * @param size サイズ
     * @param keySupplier キーを生成する処理
     * @param valueSupplier バリューを生成する処理
     * @return 生成された {@code Map}
     */
    public static <K, V> Map<K, V> collectMap(int size, BiIntSupplier<K> keySupplier, BiIntSupplier<V> valueSupplier) {
        Map<K, V> map = new HashMap<>();
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                K key = keySupplier.get(i, j);
                V value = valueSupplier.get(i, j);
                map.put(key, value);
            }
        }
        return map;
    }
    
    /**
     * 二重ループを実行して、 {@code Map} を生成する。
     * <p>
     * バリューの生成にはインデックスを利用しない場合はこのメソッドを使用してください。
     * 
     * @param size サイズ
     * @param keySupplier キーを生成する処理
     * @param valueSupplier バリューを生成する処理
     * @return 生成された {@code Map}
     */
    public static <K, V> Map<K, V> collectMap(int size, BiIntSupplier<K> keySupplier, Supplier<V> valueSupplier) {
        return collectMap(size, keySupplier, (i, j) -> valueSupplier.get());
    }
    
    /**
     * 入れ子の {@code List} を反復処理する。
     * @param nestedList 入れ子の {@code List}
     * @param iterator 反復処理（１つ目と２つ目の引数にはループインデックスが渡され、３つ目の引数に {@code List} の要素が渡されます）
     */
    public static <T> void each(List<List<T>> nestedList, TriConsumer<Integer, Integer, T> iterator) {
        for (int i=0; i<nestedList.size(); i++) {
            List<T> row = nestedList.get(i);
            for (int j=0; j<row.size(); j++) {
                iterator.accept(i, j, row.get(j));
            }
        }
    }
}
