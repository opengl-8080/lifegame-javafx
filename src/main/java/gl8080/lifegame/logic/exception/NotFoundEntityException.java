package gl8080.lifegame.logic.exception;

/**
 * 処理対象が存在しないことを表す例外。
 */
public class NotFoundEntityException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    public NotFoundEntityException(long id) {
        super("検索対象が存在しません id=" + id);
    }
}
