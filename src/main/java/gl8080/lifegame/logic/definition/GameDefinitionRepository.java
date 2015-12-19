package gl8080.lifegame.logic.definition;

import gl8080.lifegame.logic.exception.NotFoundEntityException;

/**
 * ゲーム定義の永続化を行うリポジトリ。
 */
public interface GameDefinitionRepository {
    
    /**
     * ゲーム定義を登録する。
     * @param gameDefinition 登録するゲーム定義
     */
    void register(GameDefinition gameDefinition);
    
    /**
     * 指定した ID のゲーム定義を検索する。
     * @param id ID
     * @return ゲーム定義
     * @throws NotFoundEntityException ID に紐づくゲーム定義が存在しない場合
     */
    GameDefinition search(long id);
    
    /**
     * 指定した ID のゲーム定義を検索し、排他ロックする。
     * @param id ID
     * @return ゲーム定義
     * @throws NotFoundEntityException ID に紐づくゲーム定義が存在しない場合
     */
    GameDefinition searchWithLock(long id);
    
    /**
     * 指定したゲーム定義を削除する。
     * @param gameDefinition 削除するゲーム定義
     */
    void remove(GameDefinition gameDefinition);
}
