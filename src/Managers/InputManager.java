package Managers;

import Models.Coordinates;
import Models.Flat;
import Models.Furnish;
import Models.Transport;
import Models.View;
import Models.House;

import java.util.NoSuchElementException;

/**
 * Реализует ввод данных квартиры
 * @author buffer
 */
public class InputManager {

    public static class InputBreak extends Exception {
    }

    public static Flat inputFlat(Console console, int id) throws InputBreak {
        try {
            String name;
            while (true) {
                console.print("Введите название квартиры ");
                name = console.readln().trim();
                if (name.equals("exit")) throw new InputBreak();
                if (!name.isEmpty()) break;
            }

            double area;
            while (true) {
                console.print("Введите площадь:");
                var line = console.readln().trim();
                if (line.equals("exit")) throw new InputBreak();
                if (!line.isEmpty()) {
                    try {
                        area = Double.parseDouble(line);
                        if (area > 0)
                            break;
                    } catch (NumberFormatException e) {}
                }
            }

            int numberOfRooms;
            while (true) {
                console.print("Введите количество комнат (от 1 до 7):");
                var line = console.readln().trim();
                if (line.equals("exit")) throw new InputBreak();
                if (!line.isEmpty()) {
                    try {
                        numberOfRooms = Integer.parseInt(line);
                        if (numberOfRooms > 0 && numberOfRooms < 8)
                            break;
                    } catch (NumberFormatException e) {}
                }
            }

            Coordinates coordinates = inputCoordinates(console);
            Furnish furnish = inputFurnish(console);
            House house = inputHouse(console);
            View view = inputView(console);
            Transport transport = inputTransport(console);

            return new Flat(id, name, coordinates, java.time.LocalDateTime.now(), area, numberOfRooms, furnish, view, transport, house);
        } catch (Exception e) {
            console.printError("Ошибка чтения");
            return null;
        }

    }

    public static Coordinates inputCoordinates(Console console) throws InputBreak {
        try {
            float x;
            while (true) {
                console.print("Введите координату x:");
                var line = console.readln().trim();
                if (line.equals("exit")) throw new InputBreak();
                if (!line.equals("")) {
                    try {
                        x = Float.parseFloat(line);
                        if (x > -689)
                            break;
                    } catch (NumberFormatException e) {
                    }
                }
            }
            Long y;
            while (true) {
                console.print("Введите координату y:");
                var line = console.readln().trim();
                if (line.equals("exit")) throw new InputBreak();
                if (!line.equals("")) {
                    try {
                        y = Long.parseLong(line);
                        if (x <= 337)
                            break;
                    } catch (NumberFormatException e) {
                    }
                }
            }
            return new Coordinates(x, y);
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }
    public static Furnish inputFurnish(Console console) throws InputBreak {
        try {
            Furnish f;
            while (true) {
                console.print("Введите тип мебели ("+Furnish.names()+"): ");
                var line = console.readln().trim();
                if (line.equals("exit")) throw new InputBreak();
                if (!line.equals("")) {
                    try {
                        f = Furnish.valueOf(line); break;
                    } catch (NullPointerException | IllegalArgumentException  e) { }
                } else return null;
            }
            return f;
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }

    public static Transport inputTransport(Console console) throws InputBreak {
        try {
            Transport t;
            while (true) {
                console.print("Введите тип транспорта ("+Transport.names()+"): ");
                var line = console.readln().trim();
                if (line.equals("exit")) throw new InputBreak();
                if (!line.equals("")) {
                    try {
                        t = Transport.valueOf(line); break;
                    } catch (NullPointerException | IllegalArgumentException  e) { }
                } else return null;
            }
            return t;
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }

    public static View inputView(Console console) throws InputBreak {
        try {
            View v;
            while (true) {
                console.print("Введите тип транспорта ("+View.names()+"): ");
                var line = console.readln().trim();
                if (line.equals("exit")) throw new InputBreak();
                if (!line.equals("")) {
                    try {
                        v = View.valueOf(line); break;
                    } catch (NullPointerException | IllegalArgumentException  e) { }
                } else return null;
            }
            return v;
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }

    public static House inputHouse(Console console) throws InputBreak {
        try {
            String name;
            while (true) {
                console.print("Имя дома: ");
                name = console.readln().trim();
                if (name.equals("exit")) throw new InputBreak();
                if (!name.isEmpty()) break;
            }
            Integer year;
            while (true) {
                console.print("Год дома:");
                var line = console.readln().trim();
                if (line.equals("exit")) throw new InputBreak();
                if (!line.equals("")) {
                    try {
                        year = Integer.parseInt(line);
                        if (year > 0)
                            break;
                    } catch (NumberFormatException e) {
                    }
                }
            }
            int n;
            while (true) {
                console.print("Число квартир на этаже:");
                var line = console.readln().trim();
                if (line.equals("exit")) throw new InputBreak();
                if (!line.equals("")) {
                    try {
                        n = Integer.parseInt(line);
                        if (n > 0)
                            break;
                    } catch (NumberFormatException e) {
                    }
                }
            }
            Long number;
            while (true) {
                console.print("Число лифтов:");
                var line = console.readln().trim();
                if (line.equals("exit")) throw new InputBreak();
                if (!line.equals("")) {
                    try {
                        number = Long.parseLong(line);
                        if (number > 0)
                            break;
                    } catch (NumberFormatException e) {
                    }
                }
            }
            return new House(name, year, n, number);
        } catch (NoSuchElementException | IllegalStateException e) {
            console.printError("Ошибка чтения");
            return null;
        }
    }
}
