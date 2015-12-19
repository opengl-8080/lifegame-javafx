package gl8080.lifegame.logic.exception;

public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    public BusinessException(String message) {
        super(message);
    }
}
