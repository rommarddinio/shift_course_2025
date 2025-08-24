package Koronatech;

import Koronatech.Models.Person;
import Koronatech.Parsers.ArgsParser;
import Koronatech.Parsers.FileParser;
import Koronatech.Utils.PersonSorter;
import Koronatech.Utils.Writer;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Writer.createLogFile();
        ArgsParser argsParser = new ArgsParser();
        if (!argsParser.parseArgs(args)) {
            return;
        }
        FileParser fileParser = new FileParser();
        PersonSorter personSorter = new PersonSorter();
        List<Person> people = fileParser.getWorkers();
        int managers = fileParser.getAmountOfManagers();
        boolean isStat = argsParser.isStat();
        String path = argsParser.getPath();
        String sortType = argsParser.getSortType();
        String order = argsParser.getOrder();
        Writer.startStatistics(path, isStat);
        for (int i=0; i<managers; i++) {
            List<Person> workers = personSorter.getDepartmentWorkers(people);
            Writer.printStatistics(workers, isStat, path);
            Writer.printFile(workers, sortType, order);
        }
        for (Person person: people) {
            Writer.makeNote(person.toString());
        }
    }
}