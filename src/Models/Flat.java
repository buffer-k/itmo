package Models;


import javax.lang.model.type.ArrayType;
import javax.swing.text.DateFormatter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Класс квартиры
 */
public class Flat implements Comparable<Flat>  {
    private final Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private final String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private final java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private final Double area; //Значение поля должно быть больше 0
    private final Integer numberOfRooms; //Максимальное значение поля: 7, Значение поля должно быть больше 0
    private final Furnish furnish; //Поле не может быть null
    private final View view; //Поле не может быть null
    private final Transport transport; //Поле не может быть null
    private final House house; //Поле может быть null


    public Flat(Integer id, String name, Coordinates coordinates, LocalDateTime creationDate, Double area, Integer numberOfRooms, Furnish furnish,
                View view, Transport transport, House house){
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.area = area;
        this.numberOfRooms = numberOfRooms;
        this.furnish = furnish;
        this.view = view;
        this.transport = transport;
        this.house = house;

    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Double getArea() {
        return area;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public Furnish getFurnish() {
        return furnish;
    }

    public View getView() {
        return view;
    }

    public Transport getTransport() {
        return transport;
    }

    public House getHouse() {
        return house;
    }

    public boolean checkFields() {
        if (id < 0) return false;
        if (name == null || name.isEmpty()) return false;
        if (coordinates == null) return false;
        if (numberOfRooms < 1 || numberOfRooms > 7) return false;
        if (furnish == null) return false;
        if (view == null) return false;
        return transport != null;
    }

    public static String[] toList(Flat f) {
        var list = new LinkedList<String>();
        list.add(f.getId().toString());
        list.add(f.getName());
        list.add(f.getCoordinates().toString());
        list.add(f.getCreationDate().format(DateTimeFormatter.ISO_DATE_TIME));
        list.add(f.getArea().toString());
        list.add(f.getNumberOfRooms().toString());
        list.add(f.getFurnish().toString());
        list.add(f.getView().toString());
        list.add(f.getTransport().toString());
        list.add(f.getHouse().toString());
        return list.toArray(new String[0]);
    }

    public static Flat fromArray(String[] a) {
        Integer id;
        String name;
        Coordinates coordinates;
        LocalDateTime creationDate;
        Double area;
        Integer numberOfRooms;
        Furnish furnish;
        View view;
        Transport transport;
        House house;
        try {
            try { id = Integer.parseInt(a[0]); } catch (NumberFormatException e) { id = null; }
            name = a[1];
            coordinates = new Coordinates(a[2]);
            try { creationDate = LocalDateTime.parse(a[3], DateTimeFormatter.ISO_DATE_TIME); } catch (
                    DateTimeParseException e) { creationDate = null; };
            try { area = (a[4].equals("null") ? null : Double.parseDouble(a[4])); } catch (NumberFormatException e) { area = null; }
            try { numberOfRooms = (a[5].equals("null") ? null : Integer.parseInt(a[5])); } catch (NumberFormatException e) { numberOfRooms = null; }
            try { furnish = Furnish.valueOf(a[6]); } catch (NullPointerException | IllegalArgumentException  e) { furnish = null; }
            try { view = View.valueOf(a[7]); } catch (NullPointerException | IllegalArgumentException  e) { view = null; }
            try { transport = Transport.valueOf(a[8]); } catch (NullPointerException | IllegalArgumentException  e) { transport = null; }
            house = (a[9].equals("null") ? null : new House(a[9]));
            return new Flat(id, name, coordinates, creationDate, area, numberOfRooms, furnish, view, transport, house);
        } catch (ArrayIndexOutOfBoundsException e) {}
        return null;
    }


    @Override
    public int compareTo(Flat f) {
        return (int)(this.id - f.getId());
    }

    @Override
    public String toString() {
        return "{\"id\": " + id + ", " +
                "\"name\": \"" + name + "\", " +
                "\"coordinates\": \"" + coordinates + "\", " +
                "\"creationDate\" = \"" + creationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\", " +
                "\"area\": " + (area +"\"") + ", " +
                "\"numberOfRooms\" = \"" + numberOfRooms + "\", " +
                "\"furnish\": \"" + furnish + "\", " +
                "\"view\": " + view + "\"" + ", " +
                "\"transport\": " + transport +"\", " +
                "\"house\": " + house +"\"" +"}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flat that = (Flat) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate);
    }


}