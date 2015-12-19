package gl8080.lifegame.logic;

/**
 * ライフゲームのセルを表すインターフェース。
 */
public interface LifeGameCell {

    /**
     * このセルが生きているかどうかを確認します。
     * @return 生きている場合は {@code true}
     */
    boolean isAlive();
}
