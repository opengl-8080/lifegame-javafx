package gl8080.lifegame;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Component
public class CommandLineArguments {
    @Autowired
    private ApplicationArguments args;
    
    public int getGameDefinitionId() {
        if (!this.args.containsOption("id")) {
            return 5;
        } else {
            List<String> ids = this.args.getOptionValues("id");
            
            if (ids == null || ids.isEmpty()) {
                System.err.println("--id=2 という形で ID を指定してください。");
                System.exit(-1);
                return -1;
            } else {
                return Integer.parseInt(this.args.getOptionValues("id").get(0));
            }
        }
    }
}
