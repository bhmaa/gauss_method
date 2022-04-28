public class SolutionException extends Exception {
    private final String message;

    public SolutionException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
