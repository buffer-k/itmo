package Models;

/**
 * Класс дома
 */
public class House {
    private String name; //Поле может быть null
    private Integer year; //Поле не может быть null, Значение поля должно быть больше 0
    private int numberOfFlatsOnFloor; //Значение поля должно быть больше 0
    private Long numberOfLifts; //Значение поля должно быть больше 0


    public House(String name, Integer year, int numberOfFlatsOnFloor, Long numberOfLifts) {
        this.name = name;
        this.year = year;
        this.numberOfFlatsOnFloor = numberOfFlatsOnFloor;
        this.numberOfLifts = numberOfLifts;
    }

    public House(String s) {
        try {
            this.name = s.split(" ; ")[0];
            try { this.year = s.split(" ; ")[1].equals("null") ? null : Integer.parseInt(s.split(" ; ")[1]); } catch (NumberFormatException e) { return; };
            try { this.numberOfFlatsOnFloor = s.split(" ; ")[2].equals("null") ? null : Integer.parseInt(s.split(" ; ")[2]); } catch (NumberFormatException e) { return; }
            try { this.numberOfLifts = s.split(" ; ")[2].equals("null") ? null : Long.parseLong(s.split(" ; ")[3]); } catch (NumberFormatException e) { return; }
        } catch (ArrayIndexOutOfBoundsException e) {}

    }

    @Override
    public String toString() {
        return name + " ; " + (year == null ? "null": year) + " ; " + (numberOfLifts == null ? "null": numberOfLifts) + " ; " + numberOfFlatsOnFloor;
    }

}