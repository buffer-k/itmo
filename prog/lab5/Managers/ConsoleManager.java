package Managers;
import java.util.Scanner;

/**
 * Реализация интерфейса консоли для ввода команд и вывода результата

 */
public class ConsoleManager implements Console{
    private static final String P = "$ ";
    private static Scanner fileScanner = null;
    private static Scanner defaultScanner = new Scanner(System.in);


    @Override
    public void print(Object obj) {
        System.out.print(obj);
    }


    @Override
    public void println(Object obj) {
        System.out.println(obj);
    }


    @Override
    public String readln()  {
        return (fileScanner!=null?fileScanner:defaultScanner).nextLine();
    }

    public boolean isCanReadln()  {
        return (fileScanner!=null?fileScanner:defaultScanner).hasNextLine();
    }


    public void printTable(Object elementLeft, Object elementRight) {
        System.out.printf(" %-35s%-1s%n", elementLeft, elementRight);
    }


    public void prompt() {
        print(P);
    }


    public String getPrompt() {
        return P;
    }

    public void selectFileScanner(Scanner scanner) {
        this.fileScanner = scanner;
    }

    public void selectConsoleScanner() {
        this.fileScanner = null;
    }


    public void printError(Object obj) {
        System.err.println("Error: " + obj);
    }
}
