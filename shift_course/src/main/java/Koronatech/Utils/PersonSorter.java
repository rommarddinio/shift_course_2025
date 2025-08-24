package Koronatech.Utils;

import Koronatech.Models.Employee;
import Koronatech.Models.Manager;
import Koronatech.Models.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PersonSorter {

    public List<Person> getDepartmentWorkers(List<Person> workers) {
        List<Person> departmentWorkers = new ArrayList<>();
        for (Person person: workers) {
            if (Validator.isManager(person.getPosition())) {
                departmentWorkers.add(person);
                break;
            }
        }
        if (departmentWorkers.isEmpty()) {
            return null;
        } else {
            Manager manager = (Manager) departmentWorkers.get(0);
            for (Person person: workers) {
                if (Validator.isEmployee(person.getPosition())) {
                    Employee employee = (Employee) person;
                    if(employee.getManagerId() == manager.getId()) {
                        departmentWorkers.add(employee);
                    }
                }
            }
            deleteWorkers(workers, departmentWorkers);
            return departmentWorkers;
        }
    }

    public void deleteWorkers(List<Person> workers, List<Person> departmentWorkers) {
        for (Person person: departmentWorkers) {
            workers.remove(person);
        }
    }

    public void sortByName(List<Person> workers, boolean ascending) {
        if (ascending) workers.sort(Comparator.comparing(Person::getName));
        else workers.sort(Comparator.comparing(Person::getName).reversed());
    }

    public void sortBySalary(List<Person> workers, boolean ascending) {
        if (ascending) workers.sort(Comparator.comparing(Person::getSalary));
        else workers.sort(Comparator.comparing(Person::getSalary).reversed());
    }

    public void sort(List<Person> workers, String parameter, String ascending) {
        boolean order = ascending.equals("asc");
        if (parameter.equals("name")) {
            sortByName(workers, order);
        }
        if (parameter.equals("salary")) {
            sortBySalary(workers, order);
        }
    }
}
