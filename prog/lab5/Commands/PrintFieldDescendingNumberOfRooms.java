package Commands;

import Managers.CollectionManager;
import Managers.Console;

/**
 * Команда 'print_field_descending_number_of_rooms'. Выводит значения поля numberOfRooms всех элементов в порядке убывания.
 * @author buffer
 */
public class PrintFieldDescendingNumberOfRooms extends Command{
    private final Console console;
    private final CollectionManager collectionManager;

    public PrintFieldDescendingNumberOfRooms(Console console, CollectionManager collectionManager) {
        super("print_field_descending_number_of_rooms", "вывести значения поля numberOfRooms всех элементов в порядке убывания");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    public ExecutionResponse apply(String[] arguments) {
        if (!arguments[1].isEmpty()) return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");
        if (collectionManager.getSortedNumberOfRooms().isEmpty()) return new ExecutionResponse(false, "Значения не найдены!");
        return new ExecutionResponse(collectionManager.getSortedNumberOfRooms());
    }
}
