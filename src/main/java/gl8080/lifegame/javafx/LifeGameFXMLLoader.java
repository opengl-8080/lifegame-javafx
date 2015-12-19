package gl8080.lifegame.javafx;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

@Component
public class LifeGameFXMLLoader {

    @Autowired
    private ApplicationContext context;
    
    public Parent load(String path) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        
        loader.setControllerFactory(this.context::getBean);
        
        return loader.load(this.getClass().getClassLoader().getResourceAsStream(path));
    }
}
