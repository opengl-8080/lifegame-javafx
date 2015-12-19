package gl8080.lifegame.logic.definition;

import javax.persistence.Entity;
import javax.persistence.Table;

import gl8080.lifegame.logic.AbstractEntity;
import gl8080.lifegame.logic.LifeGameCell;

/**
 * セル定義を表すクラス。
 */
@Entity
@Table(name="CELL_DEFINITION")
public class CellDefinition extends AbstractEntity implements LifeGameCell {
    private static final long serialVersionUID = 1L;
    
    private boolean alive;

    /**
     * このセル定義の生死の状態を設定する。
     * @param alive 生存の場合は {@code true} を指定する。
     */
    public void setStatus(boolean alive) {
        this.alive = alive;
    }
    
    @Override
    public boolean isAlive() {
        return this.alive;
    }

    @Override
    public String toString() {
        return "CellDefinition [id=" + this.getId() + ", alive=" + alive + "]";
    }
}
