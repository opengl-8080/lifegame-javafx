package gl8080.lifegame.logic.definition;

import static javax.persistence.CascadeType.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import gl8080.lifegame.logic.AbstractEntity;
import gl8080.lifegame.logic.LifeGame;
import gl8080.lifegame.logic.LifeGameCell;
import gl8080.lifegame.logic.Position;
import gl8080.lifegame.logic.exception.IllegalParameterException;
import gl8080.lifegame.util.NestedLoop;

/**
 * ゲーム定義を表すクラス。
 */
@Entity
@Table(name="GAME_DEFINITION")
public class GameDefinition extends AbstractEntity implements LifeGame {
    private static final long serialVersionUID = 1L;

    /**ゲームのサイズに指定できる最大値*/
    public static final int MAX_SIZE = 50;
    
    private int size;
    @Version
    private Long version;
    
    @OneToMany(cascade={PERSIST, MERGE, REMOVE}, fetch=FetchType.EAGER)
    @JoinColumn(name="GAME_DEFINITION_ID")
    private Map<Position, CellDefinition> cells;
    
    /**
     * ゲーム定義を新規に作成する。
     * <p>
     * サイズで指定した大きさを一辺とする、正方形型の領域を作成します。
     * 
     * @param size サイズ
     * @throws IllegalParameterException サイズが {@code 0} 以下、または {@link GameDefinition#MAX_SIZE} MAXSIZE} で定義されたサイズを越える値が渡された場合。
     */
    public GameDefinition(int size) {
        if (size < 1) {
            throw new IllegalParameterException("サイズに０以外の値は指定できません size =" + size);
        } else if (MAX_SIZE < size) {
            throw new IllegalParameterException("サイズに " + MAX_SIZE + " 以上の値は指定できません size =" + size);
        }
        
        this.size = size;
        this.cells = NestedLoop.collectMap(size, Position::new, CellDefinition::new);
    }

    @Override
    public Map<Position, ? extends LifeGameCell> getCells() {
        return new HashMap<>(this.cells);
    }
    
    /**
     * 指定した位置のセル定義の状態を変更します。
     * 
     * @param position 位置
     * @param status 生存に変更する場合は {@code true}
     * @throws NullPointerException 位置が {@code null} の場合
     * @throws IllegalParameterException 位置に指定した場所にセル定義が存在しない場合
     */
    public void setStatus(Position position, boolean status) {
        Objects.requireNonNull(position, "位置が null です");
        
        if (!this.cells.containsKey(position)) {
            throw new IllegalParameterException("位置が範囲外です (size = " + this.size + ", position = " + position + ")");
        }
        
        this.cells.get(position).setStatus(status);
    }

    /**
     * このゲーム定義のサイズを取得します。
     * 
     * @return ゲーム定義のサイズ
     */
    public int getSize() {
        return this.size;
    }
    
    @Override
    public Long getVersion() {
        return this.version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "GameDefinition [size=" + size + ", version=" + version + ", cells=" + cells + ", getId()=" + getId() + "]";
    }

    /**
     * @deprecated このコンストラクタはフレームワークから使用されることを想定しています。
     */
    @Deprecated @SuppressWarnings("unused")
    private GameDefinition() {}

}
