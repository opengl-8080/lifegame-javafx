package gl8080.lifegame.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import gl8080.lifegame.logic.exception.IllegalParameterException;

/**
 * 位置を表すクラス。
 * <p>
 * このクラスは、縦横の座標によってオブジェクトを一意に識別します。<br>
 * つまり、座標が同じ場所を指していれば、異なるインスタンスであっても {@link Position#equals(Object) equals()} メソッドは
 * {@code true} を返します。
 * <p>
 * 座標値は、 {@code 0} オリジンです。
 */
@Embeddable
public class Position {
    
    @Column(name="VERTICAL_POSITION")
    private int vertical;
    @Column(name="HORIZONTAL_POSITION")
    private int horizontal;

    /**
     * 新しい位置を生成する。
     * @param vertical 縦座標
     * @param horizontal 横座標
     * @throws IllegalParameterException 座標にマイナスを指定した場合
     */
    public Position(int vertical, int horizontal) {
        if (vertical < 0 || horizontal < 0) {
            throw new IllegalParameterException("座標にマイナスは指定できません (" + vertical + ", " + horizontal + ")");
        }
        
        this.vertical = vertical;
        this.horizontal = horizontal;
    }

    /**
     * 指定した位置オブジェクトが、この位置と同じ座標を表すかを検証します。
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        
        Position other = (Position) o;
        return this.vertical == other.vertical && this.horizontal == other.horizontal;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.vertical, this.horizontal);
    }
    
    @Override
    public String toString() {
        return Position.class.getSimpleName() + " (" + this.vertical + ", " + this.horizontal + ")";
    }

    /**
     * この位置に隣接する、周囲８つの位置をリストで取得します。
     * <p>
     * 隣接する座標がマイナスになる場合、その座標を指す位置はリストから除外されます。
     * 
     * @return この位置に隣接する周囲８つの位置オブジェクト
     */
    public List<Position> getNeighborPositions() {
        List<Position> neighbors = new ArrayList<>();
        
        for (int v=this.vertical-1; v<this.vertical+2; v++) {
            for (int h=this.horizontal-1; h<this.horizontal+2; h++) {
                if ((0<=v && 0<=h) && !(this.vertical==v && this.horizontal==h)) {
                    neighbors.add(new Position(v, h));
                }
            }
        }
        
        return neighbors;
    }
    
    @Deprecated @SuppressWarnings("unused")
    private Position() {}

    public int getHorizontalPosition() {
        return this.horizontal;
    }

    public int getVerticalPosition() {
        return this.vertical;
    }
}
