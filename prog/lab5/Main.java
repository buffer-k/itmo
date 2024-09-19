import Commands.*;
import Managers.*;
import Models.Coordinates;
import Models.Flat;
import Models.Furnish;
import Models.Transport;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


public class Main {
    static List<Flat> flats = new ArrayList<Flat>();

    public static void main(String[] args) {
        var console = new ConsoleManager();

        System.out.print(System.getenv("LAB5"));
        var fileManager = new FileManager(System.getenv("LAB5"), console);
        var collectionManager = new CollectionManager(fileManager);
        if (!collectionManager.loadCollection()) {
            System.exit(1);
        }

        var commandManager = new CommandManager() {{
            register("help", new Help(console,  this));
            register("info", new Info(console, collectionManager));
            register("show", new Show(console, collectionManager));
            register("add", new Add(console, collectionManager));
            register("update", new Update(console, collectionManager));
            register("remove_by_id", new RemoveById(console, collectionManager));
            register("clear", new Clear(console, collectionManager));
            register("save", new Save(console, collectionManager));
            register("execute_script", new ExecuteScript(console));
            register("exit", new Exit(console));
            register("add_if_min", new AddIfMin(console, collectionManager));
            register("shuffle", new Shuffle(console, collectionManager));
            register("history", new History(console, this));
            register("filter_contains_name", new FilterContainsName(console, collectionManager));
            register("filter_greater_than_number_of_rooms", new FilterGreaterThanNumberOfRooms(console, collectionManager));
            register("print_field_descending_number_of_rooms", new PrintFieldDescendingNumberOfRooms(console, collectionManager));
        }};
        new Runner(console, commandManager).interactiveMode();
    }
}
