package Commands;

import Managers.CollectionManager;
import Managers.Console;

/**
 * Команда 'filter_contains_name'. Выводит элементы, значение поля name которых содержит заданную подстроку
 * @author buffer
 */
public class FilterContainsName extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public FilterContainsName(Console console, CollectionManager collectionManager) {
        super("filter_contains_name name", "вывести элементы, значение поля name которых содержит заданную подстроку");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    public ExecutionResponse apply(String[] arguments) {
        if (arguments[1].isEmpty()) return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");
        String filter = arguments[1];
        int count = 0;
        for (var f: collectionManager.getCollection()) {
            if (f.getName().contains(filter)) {
                console.println(f);
                count++;
            }
        }
        if (count == 0) return new ExecutionResponse(false, "Элементы не найдены!");
        return new ExecutionResponse("Найдено элементов: " + count);
    }
}
