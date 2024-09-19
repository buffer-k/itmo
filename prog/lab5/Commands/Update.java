package Commands;

import Managers.CollectionManager;
import Managers.Console;
import Managers.InputManager;


/**
 * Команда 'update'. Обновяет значение элемента коллекции по ID.
 * @author buffer
 */
public class Update extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public Update(Console console, CollectionManager collectionManager) {
        super("update <ID> {element}", "обновить значение элемента коллекции по ID");
        this.console = console;
        this.collectionManager = collectionManager;
    }


    @Override
    public ExecutionResponse apply(String[] arguments) {
        try {
            if (arguments[1].isEmpty()) return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");
            int id = -1;
            try { id = Integer.parseInt(arguments[1].trim()); } catch (NumberFormatException e) { return new ExecutionResponse(false, "ID не распознан"); }

            var old = collectionManager.byId(id);
            if (old == null || !collectionManager.getCollection().contains(old)) {
                return new ExecutionResponse(false, "Не существующий ID");
            }

            console.println("Создание новой квартиры:");
            var f = InputManager.inputFlat(console, old.getId());
            if (f != null && f.checkFields()) {
                collectionManager.remove(old.getId());
                collectionManager.add(f);
                return new ExecutionResponse("Обновлено!");
            } else {
                return new ExecutionResponse(false, "Поля Квартиры не валидны! Квартира не создана!");
            }
        } catch (InputManager.InputBreak e) {
            return new ExecutionResponse(false, "Поля Квартиры не валидны! Квартира не создана!");
        }
    }
}