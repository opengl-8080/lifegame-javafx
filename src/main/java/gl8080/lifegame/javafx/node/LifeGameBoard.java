package gl8080.lifegame.javafx.node;

import gl8080.lifegame.logic.LifeGame;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class LifeGameBoard extends Canvas {
    
    public static final int CELL_WIDTH = 20;
    
    public LifeGameBoard(int size) {
        super(size * CELL_WIDTH, size * CELL_WIDTH);
    }
    
    public void refresh(LifeGame lifeGame) {
        GraphicsContext context = this.getGraphicsContext2D();
        
        lifeGame
            .getCells()
            .forEach((position, cell) -> {
                int x = position.getVerticalPosition() * CELL_WIDTH;
                int y = position.getHorizontalPosition() * CELL_WIDTH;
                int w = CELL_WIDTH;
                int h = CELL_WIDTH;
                
                context.clearRect(x, y, w, h);
                
                if (cell.isAlive()) {
                    context.fillRect(x, y, w, h);
                } else {
                    context.strokeRect(x, y, w, h);
                }
            });
    }
}
