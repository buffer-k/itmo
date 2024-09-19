package Commands;

import Managers.CommandManager;
import Managers.Console;
import java.util.stream.Collectors;

/**
 * Команда 'history'. Выводит историю команд.
 * @author buffer
 */
public class History extends Command {
    private final Console console;
    private final CommandManager commandManager;

    public History(Console console, CommandManager commandManager) {
        super("history", "Выводит историю команд");
        this.console = console;
        this.commandManager = commandManager;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (!arguments[1].isEmpty()) return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");

        return new ExecutionResponse(commandManager.getCommandHistory().stream().map(command -> " " + command).collect(Collectors.joining("\n")));
    }
}