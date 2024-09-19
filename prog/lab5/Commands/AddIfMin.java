package Commands;

import Managers.CollectionManager;
import Managers.Console;
import Managers.InputManager;
import Models.Flat;

/**
 * Команда 'add_if_min'. Добавляет новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции.
 * @author buffer
 */
public class AddIfMin extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public AddIfMin(Console console, CollectionManager collectionManager) {
        super("add_if_min {element}", "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public ExecutionResponse apply(String[] arguments) {
        try {
            if (!arguments[1].isEmpty()) return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");
            console.println("* Создание новой квартиры:");
            Flat f = InputManager.inputFlat(console, collectionManager.getFreeId());
            if (f.getNumberOfRooms() < collectionManager.getMinNumberOfRooms()) {
                collectionManager.add(f);
                return new ExecutionResponse("Квартира добавлена!");
            }
            return new ExecutionResponse(false, "Поле не меньше минимального значения, минимальное значение: " + collectionManager.getMinNumberOfRooms());
            }

        catch (InputManager.InputBreak e) {
            return new ExecutionResponse(false,"Отмена...");
        }
    }
    }
