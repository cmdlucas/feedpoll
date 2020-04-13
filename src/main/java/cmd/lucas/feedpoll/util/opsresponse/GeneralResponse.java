package cmd.lucas.feedpoll.util.opsresponse;

public final class GeneralResponse {
    private final ResponseType type;
    private final String message;

    public GeneralResponse(ResponseType type, String message) {
        this.type = type;
        this.message = message;
    }

    public ResponseType getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}
