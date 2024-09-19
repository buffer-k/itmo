package Commands;

import Managers.CollectionManager;
import Managers.Console;

/**
 * Команда 'filter_greater_than_number_of_rooms'. Выводит элементы, значение поля numberOfRooms которых больше заданного
 * @author buffer
 */
public class FilterGreaterThanNumberOfRooms extends Command{
    private final Console console;
    private final CollectionManager collectionManager;

    public FilterGreaterThanNumberOfRooms(Console console, CollectionManager collectionManager) {
        super("filter_greater_than_number_of_rooms numberOfRooms", "вывести элементы, значение поля numberOfRooms которых больше заданного");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    public ExecutionResponse apply(String[] arguments) {
        if (arguments[1].isEmpty()) return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");
        int numberOfRooms = -1;
        try { numberOfRooms = Integer.parseInt(arguments[1].trim()); } catch (NumberFormatException e) { return new ExecutionResponse(false, "Число комнат не распознано!"); }
        int count = 0;
        for (var f: collectionManager.getCollection()) {
            if (f.getNumberOfRooms() > numberOfRooms) {
                console.println(f);
                count++;
            }
        }
        if (count == 0) return new ExecutionResponse(false, "Элементы не найдены!");
        return new ExecutionResponse("Найдено элементов: " + count);
    }
}

