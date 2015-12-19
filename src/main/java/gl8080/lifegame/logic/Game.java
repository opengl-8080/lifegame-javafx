package gl8080.lifegame.logic;

import static javax.persistence.CascadeType.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import gl8080.lifegame.logic.definition.GameDefinition;

/**
 * ゲームを表すクラス。
 */
@Entity
public class Game extends AbstractEntity implements LifeGame {
    private static final long serialVersionUID = 1L;

    private int size;
    
    @OneToMany(cascade={PERSIST, MERGE, REMOVE}, fetch=FetchType.EAGER)
    @JoinColumn(name="GAME_ID")
    private Map<Position, Cell> cells;

    /**
     * 新しいゲームを作成する。
     * 
     * @param gameDef このゲームの元となるゲーム定義
     * @throws NullPointerException ゲーム定義が {@code null} の場合
     */
    public Game(GameDefinition gameDef) {
        Objects.requireNonNull(gameDef, "ゲーム定義が null です。");
        this.cells = new HashMap<>();
        this.size = gameDef.getSize();
        
        this.initializeCells(gameDef);
    }

    private void initializeCells(GameDefinition gameDef) {
        gameDef.getCells().forEach((position, cellDef) -> {
            this.cells.put(position, cellDef.isAlive() ? Cell.alive() : Cell.dead());
        });
    }

    /**
     * このゲームが持つ各セルに、隣接する周囲のセルをセットする。
     */
    public void initializeNeighborCells() {
        this.cells.forEach((position, cell) -> {
            List<Cell> neighbors =
                position
                    .getNeighborPositions().stream()
                    .filter(this.cells::containsKey)
                    .map(this.cells::get)
                    .collect(Collectors.toList());
            
            cell.setNeighbors(neighbors);
        });
    }

    @Override
    public Map<Position, Cell> getCells() {
        return new HashMap<>(this.cells);
    }

    /**
     * このゲームを１ステップ進める。
     */
    public void nextStep() {
        if (this.cells.values().stream().anyMatch(cell -> cell.getNeighbors().isEmpty())) {
            throw new IllegalStateException("隣接セルが初期化されていません。");
        }
        
        this.cells.values().forEach(Cell::reserveNextStatus);
        this.cells.values().forEach(Cell::stepNextStatus);
    }
    
    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public String toString() {
        return "Game [id=" + this.getId() + ", size=" + size + ", cells=" + cells + "]";
    }

    /**
     * @deprecated このコンストラクタはフレームワークから使用されることを想定しています。
     */
    @Deprecated @SuppressWarnings("unused")
    private Game() {}
}
