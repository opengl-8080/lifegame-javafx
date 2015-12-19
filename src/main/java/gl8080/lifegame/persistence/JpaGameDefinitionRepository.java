package gl8080.lifegame.persistence;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gl8080.lifegame.logic.definition.GameDefinition;
import gl8080.lifegame.logic.definition.GameDefinitionRepository;
import gl8080.lifegame.logic.exception.NotFoundEntityException;

@Repository
public class JpaGameDefinitionRepository implements GameDefinitionRepository {
    
    @Autowired
    private EntityManager em;

    @Override
    public void register(GameDefinition gameDefinition) {
        this.em.persist(gameDefinition);
    }

    @Override
    public GameDefinition search(long id) {
        return this.searchWithLock(id, LockModeType.NONE);
    }

    @Override
    public GameDefinition searchWithLock(long id) {
        return this.searchWithLock(id, LockModeType.PESSIMISTIC_FORCE_INCREMENT);
    }
    
    private GameDefinition searchWithLock(long id, LockModeType lock) {
        GameDefinition gameDefinition = this.em.find(GameDefinition.class, id, lock);
        
        if (gameDefinition == null) {
            throw new NotFoundEntityException(id);
        }
        
        return gameDefinition;
    }

    @Override
    public void remove(GameDefinition gameDefinition) {
        this.em.remove(gameDefinition);
    }
}
