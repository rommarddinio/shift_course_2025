package Koronatech.Utils;

import Koronatech.Models.Manager;
import Koronatech.Models.Person;

import java.io.*;
import java.util.List;

public class Writer {

    public static void createLogFile()  {
        File errors = new File("error.log");
        try {
            if (errors.exists()) {
                errors.delete();
                errors.createNewFile();
            } else errors.createNewFile();
        } catch (IOException e) {
            System.out.println("Не удалось создать файл error.log");
        }
    }

    public static void makeNote(String line) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("error.log", true))) {
            bufferedWriter.write(line);
            bufferedWriter.newLine();
        } catch (IOException e) {
            System.out.println("Не удалось открыть файл для записи");
        }
    }

    public static void printFile(List<Person> buff, String parameter, String ascending) {
        Manager manager = (Manager) buff.get(0);
        String fileName = manager.getDepartment() + ".sb";
        File file = new File(fileName);
        if(parameter!=null && ascending!=null) {
            PersonSorter personSorter = new PersonSorter();
            personSorter.sort(buff, parameter, ascending);
        }
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false))) {
            for (Person person : buff) {
                bufferedWriter.write(person.toString());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Не удалось открыть файл "+ fileName + " для записи");
        }
    }

    public static void printFileStatistics(List<Person> buff, String path) {
        File file = new File(path);
        Manager manager = (Manager) buff.get(0);
        Statistics statistics = new Statistics(manager.getDepartment());
        statistics.calculate(buff);
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(statistics.toString());
            bufferedWriter.newLine();
        } catch (IOException e) {
            System.out.println("Не удалось открыть файл "+ path + " для записи");
        }
    }

    public static void printConsoleStatistics(List<Person> buff) {
        Manager manager = (Manager) buff.get(0);
        Statistics statistics = new Statistics(manager.getDepartment());
        statistics.calculate(buff);
        System.out.println(statistics.toString());
    }

    public static void printStatistics(List<Person> buff, boolean isStat, String path) {
        if (isStat) {
            if(path!=null) {
                printFileStatistics(buff, path);
            } else printConsoleStatistics(buff);
        }
    }

    public static void startStatistics(String path, boolean isStat) {
        if (isStat) {
            if (path != null) {
                File file = new File(path);
                try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false))) {
                    bufferedWriter.write("department, min, max, mid");
                    bufferedWriter.newLine();
                } catch (IOException e) {
                    System.err.println("Не удалось открыть файл " + path + " для записи");
                }
            } else System.out.println("department, min, max, mid");
        }
    }
}
