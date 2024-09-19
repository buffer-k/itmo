package Models;

/**
 * Класс координат
 */
public class Coordinates {
    private Float x; //Значение поля должно быть больше -689, Поле не может быть null
    private long y; //Максимальное значение поля: 337


    public Coordinates(Float x, long y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates(String s) {
        try {
            try { this.x = Float.parseFloat(s.split(";")[0]); } catch (NumberFormatException e) { }
            try { this.y = Long.parseLong(s.split(";")[1]); } catch (NumberFormatException e) { }
        } catch (ArrayIndexOutOfBoundsException e) {}
    }

    @Override
    public String toString() {
        return x + ";" + y;
    }
}