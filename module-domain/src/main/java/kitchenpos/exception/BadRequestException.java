package kitchenpos.exception;

public class BadRequestException extends IllegalArgumentException {
    public BadRequestException(String message) {
        super(message);
    }
}
