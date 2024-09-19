package Commands;

import Managers.CollectionManager;
import Managers.InputManager;
import Managers.Console;
import Models.Flat;


/**
 * Команда 'add'. Добавляет новый элемент в коллекцию.
 * @author buffer
 */
public class Add extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public Add(Console console, CollectionManager collectionManager) {
        super("add {element}", "добавить новый элемент в коллекцию");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду
     *
     * @return Успешность выполнения команды.
     */
    @Override
    public ExecutionResponse apply(String[] arguments) {
        try {
            if (!arguments[1].isEmpty()) return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");

            console.println("* Создание новой квартиры:");
            Flat f = InputManager.inputFlat(console, collectionManager.getFreeId());

            if (f != null && f.checkFields()) {
                collectionManager.add(f);
                return new ExecutionResponse("Квартира успешно добавлен!");
            } else return new ExecutionResponse(false,"Поля квартиры не валидны! Квартира не создана!");
        } catch (InputManager.InputBreak e) {
            return new ExecutionResponse(false,"Отмена...");
        }
    }
}