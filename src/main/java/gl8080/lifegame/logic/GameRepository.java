package gl8080.lifegame.logic;

import gl8080.lifegame.logic.exception.NotFoundEntityException;

/**
 * ゲームの永続化を行うリポジトリ。
 */
public interface GameRepository {

    /**
     * ゲームを登録する。
     * @param game 登録するゲーム
     */
    void register(Game game);
    
    /**
     * 指定した ID のゲームを検索する。
     * @param id ID
     * @return 検索結果
     * @throws NotFoundEntityException 指定した ID に紐づくゲームが存在しない場合
     */
    Game search(long id);

    /**
     * 指定した ID のゲームを検索し、排他ロックする。
     * @param id ID
     * @return 検索結果
     * @throws NotFoundEntityException 指定した ID に紐づくゲームが存在しない場合
     */
    Game searchWithLock(long id);

    /**
     * 指定したゲームを削除する。
     * @param game 削除するゲーム。
     */
    void remove(Game game);
}
