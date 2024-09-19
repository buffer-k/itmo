package Commands;

import Managers.CollectionManager;
import Managers.Console;
import Models.Flat;

/**
 * Команда 'clear'. Очищает коллекцию.
 * @author buffer
 */
public class Clear extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public Clear(Console console, CollectionManager collectionManager) {
        super("clear", "очистить коллекцию");
        this.console = console;
        this.collectionManager = collectionManager;
    }


    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (!arguments[1].isEmpty()) return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");

        while (collectionManager.getCollection().size() > 0) {
            var f = collectionManager.getCollection().getLast();
            collectionManager.remove(f.getId());
        }

        collectionManager.update();
        return new ExecutionResponse("Коллекция очищена!");
    }
}