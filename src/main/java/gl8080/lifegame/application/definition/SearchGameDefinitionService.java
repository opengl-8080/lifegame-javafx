package gl8080.lifegame.application.definition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gl8080.lifegame.logic.definition.GameDefinition;
import gl8080.lifegame.logic.definition.GameDefinitionRepository;

@Service
public class SearchGameDefinitionService {
    private static final Logger logger = LoggerFactory.getLogger(SearchGameDefinitionService.class);
    
    @Autowired
    private GameDefinitionRepository repository;
    
    public GameDefinition search(long id) {
        logger.debug("search game definition (id={})", id);
        
        GameDefinition gameDefinition = this.repository.search(id);
        
        return gameDefinition;
    }
}
