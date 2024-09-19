package Commands;

import Managers.Console;
import Managers.CommandManager;

import java.util.stream.Collectors;

/**
 * Команда 'help'. Выводит справку по доступным командам
 * @author buffer
 */
public class Help extends Command {
    private final Console console;
    private final CommandManager commandManager;

    public Help(Console console, CommandManager commandManager) {
        super("help", "вывести справку по доступным командам");
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

        return new ExecutionResponse(commandManager.getCommands().values().stream().map(command -> String.format("%s - %s%n", command.getName(), command.getDescription())).collect(Collectors.joining("\n")));
    }
}