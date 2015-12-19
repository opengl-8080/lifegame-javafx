package gl8080.lifegame.javafx.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gl8080.lifegame.CommandLineArguments;
import gl8080.lifegame.application.definition.SearchGameDefinitionService;
import gl8080.lifegame.javafx.node.LifeGameBoard;
import gl8080.lifegame.logic.Game;
import gl8080.lifegame.logic.definition.GameDefinition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

@Component
public class MainController implements Initializable {
    
    @FXML
    private AnchorPane pane;
    @Autowired
    private CommandLineArguments args;
    @Autowired
    private SearchGameDefinitionService searchService;
    
    private LifeGameBoard board;
    private GameDefinition gameDefinition;
    private Game game;
    
    private ScheduledExecutorService schedule = Executors.newSingleThreadScheduledExecutor();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.gameDefinition = this.searchService.search(this.args.getGameDefinitionId());
        
        this.setupPaneSize();
        this.setupLifeGameBoard();
        this.setupGame();
        
        this.schedule.scheduleWithFixedDelay(this::nextStep, 1000, 200, TimeUnit.MILLISECONDS);
    }
    
    private void setupPaneSize() {
        int size = this.gameDefinition.getSize();
        int width = size * LifeGameBoard.CELL_WIDTH - 10;
        int height = size * LifeGameBoard.CELL_WIDTH - 10;
        
        this.pane.setPrefWidth(width);
        this.pane.setPrefHeight(height);
    }
    
    private void setupLifeGameBoard() {
        this.board = new LifeGameBoard(this.gameDefinition.getSize());
        this.pane.getChildren().add(this.board);
    }
    
    private void setupGame() {
        this.game = new Game(this.gameDefinition);
        this.game.initializeNeighborCells();
        this.refreshBoard();
    }
    
    private void nextStep() {
        this.game.nextStep();
        Platform.runLater(this::refreshBoard);
    }
    
    private void refreshBoard() {
        this.board.refresh(this.game);
    }
    
    @PreDestroy
    public void shutdownSchedule() {
        this.schedule.shutdown();
    }
}
