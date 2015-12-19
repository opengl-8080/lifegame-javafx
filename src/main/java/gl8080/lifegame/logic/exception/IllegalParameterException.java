package gl8080.lifegame.logic.exception;

public class IllegalParameterException extends BusinessException {
    private static final long serialVersionUID = 1L;

    public IllegalParameterException(String message) {
        super(message);
    }
}
