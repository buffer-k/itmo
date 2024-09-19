package Managers;

import au.com.bytecode.opencsv.*;

import java.io.*;
import java.util.Collection;
import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.LinkedList;
import Managers.Console;
import Models.Flat;

/**
 * Использует файл для сохранения и загрузки коллекции.
 * @author buffer
 */
public class FileManager {
    private final String fileName;
    private final Console console;

    public FileManager(String fileName, Console console) {
        this.fileName = fileName;
        this.console = console;
    }

    /**
     * Преобразует коллекцию в CSV-строку.
     * @return CSV-строка
     */
    private String collection2CSV(Collection<Flat> collection) {
        try {
            StringWriter sw = new StringWriter();
            CSVWriter csvWriter = new CSVWriter(sw, ';');
            for (var f : collection) {
                csvWriter.writeNext(Flat.toList(f));
            }
            String csv = sw.toString();
            return csv;
        } catch (Exception e) {
            console.printError("Ошибка сериализации");
            return null;
        }
    }

    /**
     * Записывает коллекцию в файл.
     * @param collection коллекция
     */
    public void writeCollection(Collection<Flat> collection) {
        OutputStreamWriter writer = null, writer2 = null, writer3 = null;
        try {
            var csv = collection2CSV(collection);
            if (csv == null) return;
            writer = new OutputStreamWriter(new FileOutputStream(fileName));
            try {
                writer.write(csv);
                writer.flush();
                console.println("Коллекция успешна сохранена в файл!");
            } catch (IOException e) {
                console.printError("Неожиданная ошибка сохранения");
            }
        } catch (FileNotFoundException | NullPointerException e) {
            console.printError("Файл не найден");
        } finally {
            try {
                writer.close();
            } catch(IOException e) {
                console.printError("Ошибка закрытия файла");
            }
        }
    }

    /**
     * Преобразует CSV-строку в коллекцию.

     * @return коллекция
     */
    private LinkedList<Flat> CSV2collection(String s) {
        try {
            StringReader sr = new StringReader(s);
            CSVReader csvReader = new CSVReader(sr, ';');
            LinkedList<Flat> ds = new LinkedList<Flat>();
            String[] record = null;
            while ((record = csvReader.readNext()) != null) {
                Flat f = Flat.fromArray(record);
                if (f.checkFields())
                    ds.add(f);
                else
                    console.printError("Файл с колекцией содержит не действительные данные");
            }
            csvReader.close();
            return ds;
        } catch (Exception e) {
            console.printError("Ошибка десериализации");
            return null;
        }
    }

    /**
     * Считывает коллекцию из файл.
     * @return Считанная коллекция
     */
    public void readCollection(Collection<Flat> collection) {
        System.out.println(fileName);
        if (fileName != null && !fileName.isEmpty()) {
            try (var fileInputStream = new FileInputStream(fileName);
                 var bufferedInputStream = new BufferedInputStream(fileInputStream)) {

                var s = new StringBuilder();
                int data;
                while ((data = bufferedInputStream.read()) != -1) {
                    s.append((char) data);
                }
                collection.clear();
                for (var e : CSV2collection(s.toString())) {
                    collection.add(e);
                }

                if (!collection.isEmpty()) {
                    console.println("Коллекция успешно загружена!");
                    return;
                } else {
                    console.printError("В загрузочном файле не обнаружена необходимая коллекция!");
                }
            } catch (FileNotFoundException exception) {
                console.printError("Загрузочный файл не найден!");
            } catch (IOException exception) {
                console.printError("Ошибка при чтении файла!");
            } catch (IllegalStateException exception) {
                console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        } else {
            console.printError("Загрузочный файл, заданный переменным окружением не найден!");
        }
        collection = new LinkedList<>(); // Инициализируем пустую коллекцию, если процесс не удался
    }
}