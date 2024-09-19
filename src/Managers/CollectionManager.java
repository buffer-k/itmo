package Managers;

import java.util.LinkedList;
import java.util.Stack;
import Models.Flat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс CollectionManager оперирует с коллекцией.
 * @author buffer
 */
public class CollectionManager {
    private int currentId = 1;
    private Map<Integer, Flat> flats = new HashMap<>();
    private LinkedList<Flat> collection = new LinkedList<Flat>();
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;
    private final FileManager fileManager;

    public CollectionManager(FileManager fileManager) {
        this.lastInitTime = null;
        this.lastSaveTime = null;
        this.fileManager = fileManager;
    }

    /**
     * @return коллекция.
     */
    public LinkedList<Flat> getCollection() {
        return collection;
    }

    /**
     * @return Последнее время инициализации.
     */
    public LocalDateTime getLastInitTime() {
        return lastInitTime;
    }

    /**
     * @return Последнее время сохранения.
     */
    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }

    /**
     * Сохраняет коллекцию в файл
     */
    public void saveCollection() {
        fileManager.writeCollection(collection);
        lastSaveTime = LocalDateTime.now();
    }



    public Flat byId(Integer id) { return flats.get(id); }


    public boolean isСontain(Flat f) { return f == null || byId(f.getId()) != null; }

    /**
     * Получить свободный ID
     */
    public int getFreeId() {
        while (byId(currentId) != null)
            if (++currentId < 0)
                currentId = 1;
        return currentId;
    }


    public int getMinNumberOfRooms() {
        int min_ = Integer.MAX_VALUE;
        for (Flat flat : collection) {
            if (flat.getNumberOfRooms() < min_) {
                min_ = flat.getNumberOfRooms();
            }
        }
        return min_;
    }

    public String getSortedNumberOfRooms() {
        List<Integer> sorted_numbers = new ArrayList<>();
        for (var f: collection) {
            sorted_numbers.add(f.getNumberOfRooms());
        }
        Collections.sort(sorted_numbers);
        Collections.reverse(sorted_numbers);
        return sorted_numbers.stream().map(Object::toString)
                .collect(Collectors.joining(",\n"));
    }

    public boolean add(Flat f) {
        if (isСontain(f)) return false;
        flats.put(f.getId(), f);
        collection.add(f);

        return true;
    }


    public boolean remove(int id) {
        var a = byId(id);
        if (a == null) return false;
        flats.remove(a.getId());
        collection.remove(a);

        return true;
    }


    public boolean loadCollection() {
        flats.clear();
        fileManager.readCollection(collection);
        lastInitTime = LocalDateTime.now();
        for (var f : collection)
            if (byId(f.getId()) != null) {
                collection.clear();
                return false;
            } else {
                if (f.getId()>currentId) currentId = f.getId();
                flats.put(f.getId(), f);
            }
        return true;
    }

    public void update() {
        Collections.sort(collection);
    }

    public void shuffle() {
        Collections.shuffle(collection);
    }


    @Override
    public String toString() {
        if (collection.isEmpty()) return "Коллекция пуста!";

        StringBuilder info = new StringBuilder();
        for (Flat flat : collection) {
            info.append(flat+"\n\n");
        }
        return info.toString().trim();
    }
}