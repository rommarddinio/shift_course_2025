package Koronatech.Models;

import java.util.Locale;

public class Employee extends Person {
    protected int managerId;

    public Employee (String[] fields) {
        super("Employee", Integer.parseInt(fields[1]) , fields[2], Double.parseDouble(fields[3]));
        this.managerId = Integer.parseInt(fields[4]);
    }

    public int getManagerId() { return this.managerId; }

    @Override
    public String toString() {
        String salaryStr;
        if (salary == (long) salary) {
            salaryStr = String.format(Locale.US, "%.0f", salary);
        } else {
            salaryStr = String.format(Locale.US, "%.2f", salary);
        }
        return String.format(Locale.US,"%s,%d,%s,%s,%s", position, id, name, salaryStr, managerId);
    }
}
