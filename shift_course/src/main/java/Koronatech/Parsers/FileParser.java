package Koronatech.Parsers;

import Koronatech.Models.Employee;
import Koronatech.Models.Manager;
import Koronatech.Models.Person;
import Koronatech.Utils.Validator;
import Koronatech.Utils.Writer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileParser {
    private final File currentDir = new File(System.getProperty("user.dir"));
    private int amountOfManagers = 0;

    public List<String> getInputFiles() {
        List<String> inputFiles = new ArrayList<>();
        String[] files = currentDir.list();
        if (files!=null) {
            for (String name : files) {
                if (checkFileFormat(name)) inputFiles.add(name);
            }
        }
        return inputFiles;
    }

    public static boolean checkFileFormat(String name) {
        int dot = name.lastIndexOf('.');
        String extension = (dot == -1) ? "" : name.substring(dot);
        return extension.equals(".sb");
    }

    public List<Person> getWorkers() {
        List<Person> workers = new ArrayList<>();
        List<String> files = getInputFiles();
        if (files.isEmpty()) {
            System.out.println("В директории нет подходящих файлов для обработки.");
            return null;
        }
        try {
            for (String name : files) {
                BufferedReader fileReader = new BufferedReader(new FileReader(new File(currentDir, name)));
                String buff;

                while ((buff = fileReader.readLine()) != null) {
                    String[] fields = buff.split(",");
                    for (int i = 0; i < fields.length; i++) {
                        fields[i] = fields[i].trim();
                    }
                    if (Validator.checkLine(fields)) {
                        if (Validator.isManager(fields[0])) {
                            Manager manager = new Manager(fields);
                            if (workers.contains(manager)) {
                                Writer.makeNote(manager.toString());
                            } else {
                                workers.add(manager);
                                amountOfManagers++;
                            }
                        } else {
                            Employee employee = new Employee(fields);
                            if (workers.contains(employee)) {
                                Writer.makeNote(employee.toString());
                            } else {
                                workers.add(employee);
                            }
                        }
                    } else {
                        Writer.makeNote(buff);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Произошла ошибка при чтении файла");
        }
        return workers;
    }

    public int getAmountOfManagers() { return amountOfManagers; }
}
