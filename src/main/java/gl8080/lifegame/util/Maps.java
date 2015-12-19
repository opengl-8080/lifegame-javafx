package gl8080.lifegame.util;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link HashMap} を簡潔に作成するためのユーティリティクラス。 
 *
 * @param <K> マップのキー
 * @param <V> マップのバリュー
 */
public class Maps<K, V> {
    
    private Map<K, V> map = new HashMap<>();
    
    /**
     * 単一エントリーのマップを生成する。
     * 
     * @param key キー
     * @param value バリュー
     * @return マップ
     */
    public static <K, V> Map<K, V> map(K key, V value) {
        return new Maps<K, V>().put(key, value).get();
    }
    
    /**
     * 新しいマップを生成する。
     * @return 生成されたビルダーインスタンス
     */
    public static <K, V> Maps<K, V> newMap() {
        return new Maps<K, V>();
    }
    
    /**
     * マップにエントリーを追加する。 
     * @param key キー
     * @param value バリュー
     * @return このビルダー
     */
    public Maps<K, V> put(K key, V value) {
        this.map.put(key, value);
        return this;
    }
    
    /**
     * 作成したマップを取得する。
     * @return マップ
     */
    public Map<K, V> get() {
        return this.map;
    }
    
    private Maps() {}
}
