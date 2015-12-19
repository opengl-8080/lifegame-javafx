package gl8080.lifegame.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * セルを表すクラス。
 */
@Entity
public class Cell extends AbstractEntity implements LifeGameCell {
    private static final long serialVersionUID = 1L;
    
    private boolean alive;
    @Transient
    private Boolean nextStatus;
    @Transient
    private List<Cell> neighbors = Collections.emptyList();

    /**
     * 死んだセルを生成します。
     * @return 死んだセル
     */
    public static Cell dead() {
        return new Cell(false);
    }

    /**
     * 生きたセルを生成します。
     * @return 生きたセル
     */
    public static Cell alive() {
        return new Cell(true);
    }
    
    private Cell(boolean alive) {
        this.alive = alive;
    }

    @Override
    public boolean isAlive() {
        return this.alive;
    }

    /**
     * 次の状態を予約します。
     * <p>
     * このメソッドは、ライフゲームのルールに従ってこのセルの次の状態を予約します。
     * <p>
     * このメソッドを実行しただけでは、セルの状態は変更されません。<br>
     * 次の状態を確認するには {@link Cell#toBeAlive() toBeAlive()} メソッドを使用してください。<br>
     * 次の状態に遷移するには {@link Cell#stepNextStatus() stepNextStatus()} メソッドを使用してください。
     * 
     * @see
     * <a href="https://ja.wikipedia.org/wiki/%E3%83%A9%E3%82%A4%E3%83%95%E3%82%B2%E3%83%BC%E3%83%A0#.E3.83.A9.E3.82.A4.E3.83.95.E3.82.B2.E3.83.BC.E3.83.A0.E3.81.AE.E3.83.AB.E3.83.BC.E3.83.AB">
     *   ライフゲームのルール | Wikipedia
     * </a>
     */
    public void reserveNextStatus() {
        long liveCellCount = neighbors.stream().filter(Cell::isAlive).count();
        
        if (4 <= liveCellCount) {
            this.nextStatus = false;
        } else if (liveCellCount == 3) {
            this.nextStatus = true;
        } else if (liveCellCount == 2) {
            this.nextStatus = this.alive;
        } else {
            this.nextStatus = false;
        }
    }

    /**
     * 次の状態に遷移します。
     * <p>
     * このメソッドの実行が完了すると、セルの状態は変更されます。<br>
     * そして、このセルは再び<u>予約されていない状態</u>に戻ります。
     * 
     * @throws IllegalStateException 次の状態が予約されていない状態でこのメソッドを実行した場合
     */
    public void stepNextStatus() {
        if (this.nextStatus == null) {
            throw new IllegalStateException("次の状態への遷移が予約されていません。");
        }
        this.alive = this.nextStatus;
        this.nextStatus = null;
    }

    /**
     * 現在予約されている、次の状態を取得します。
     * 
     * @return 次の状態（生存の場合は {@code true}）
     * @throws 次の状態が予約されていない状態でこのメソッドを実行した場合
     */
    boolean toBeAlive() {
        if (this.nextStatus == null) {
            throw new IllegalStateException("次の状態への遷移が予約されていません。");
        }
        return this.nextStatus;
    }

    /**
     * このセルに隣接する周辺のセルを設定する。
     * 
     * @param neighbors 周辺のセル
     * @throws NullPointerException 周辺のセルが {@code null} の場合
     */
    void setNeighbors(List<Cell> neighbors) {
        Objects.requireNonNull(neighbors, "隣接するセルが null です。");
        this.neighbors = new ArrayList<>(neighbors);
    }

    /**
     * このセルに隣接する周辺のセルを取得する。
     * <p>
     * このメソッドが返すリストは、このオブジェクトが持つリストのコピーです。<br>
     * 取得したリストから要素を追加、削除しても、このオブジェクトが持つリストには影響を与えません。
     * 
     * @return このセルに隣接する周辺のセル
     */
    public List<Cell> getNeighbors() {
        return new ArrayList<>(this.neighbors);
    }

    @Override
    public String toString() {
        return "Cell [id=" + this.getId() + ", alive=" + alive + ", nextStatus=" + nextStatus + ", neighbors=***]";
    }

    /**
     * @deprecated このコンストラクタはフレームワークから使用されることを想定しています。
     */
    @Deprecated
    private Cell() {}
}
