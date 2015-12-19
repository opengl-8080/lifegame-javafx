package gl8080.lifegame;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import gl8080.lifegame.javafx.LifeGameFXMLLoader;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@SpringBootApplication
public class Main extends Application {

    private static ConfigurableApplicationContext context;
    
    public static void main(String[] args) throws IOException {
        context = SpringApplication.run(Main.class, args);
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        LifeGameFXMLLoader loader = context.getBean(LifeGameFXMLLoader.class);
        
        Parent root = loader.load("fxml/main.fxml");
        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("LifeGame");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        context.close();
    }

}
