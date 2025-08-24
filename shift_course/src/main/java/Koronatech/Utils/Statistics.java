package Koronatech.Utils;

import Koronatech.Models.Person;

import java.util.List;
import java.util.Locale;

public class Statistics {
    private String department;
    private double min;
    private double max;
    private double mid;

    public Statistics (String department) {
        this.department = department;
        this.min = 0;
        this.max = 0;
        this.mid = 0;
    }

    public void calculate(List<Person> buff) {
        if (buff.size()<=1) return;
        double totalSalary = 0;
        this.min = this.max = buff.get(1).getSalary();
        for (int i = 1; i < buff.size(); i++) {
            double currentSalary = buff.get(i).getSalary();
            totalSalary = totalSalary + currentSalary;
            if (currentSalary > this.max) this.max = currentSalary;
            if (currentSalary < this.min) this.min = currentSalary;
        }
        this.mid = totalSalary / (buff.size() - 1);
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "%s,%.2f,%.2f,%.2f", department, min, max, mid);
    }
}
