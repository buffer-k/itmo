package Commands;

/**
 * Класс ExecutionResponse представляет собой простой объект для хранения результата выполнения команды.
 * Он содержит информацию о статусе выполнения
 */
public class ExecutionResponse {
    private boolean exitCode;
    private String massage;

    public ExecutionResponse(boolean code, String s) {
        exitCode = code;
        massage = s;
    }

    public ExecutionResponse(String s) {
        this(true, s);
    }

    public boolean getExitCode() { return exitCode; }
    public String getMassage() { return massage; }
    public String toString() { return String.valueOf(exitCode)+";"+massage; }
}