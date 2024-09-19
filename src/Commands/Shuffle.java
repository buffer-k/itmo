package Commands;

import Managers.CollectionManager;
import Managers.CommandManager;
import Managers.Console;

import java.util.stream.Collectors;

/**
 * Команда 'shuffle'. Перемешивает элементы коллекции в случайном порядке.
 * @author buffer
 */
public class Shuffle extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public Shuffle(Console console, CollectionManager collectionManager) {
        super("shuffle", "перемешать элементы коллекции в случайном порядке");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (!arguments[1].isEmpty()) return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");
        collectionManager.shuffle();
        return new ExecutionResponse("Коллекция перемешена");
    }
}
