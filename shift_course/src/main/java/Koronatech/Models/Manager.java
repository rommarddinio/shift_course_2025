package Koronatech.Models;

import java.util.Locale;

public class Manager extends Person{
    protected String department;

    public Manager (String[] fields) {
        super("Manager", Integer.parseInt(fields[1]) , fields[2], Double.parseDouble(fields[3]));
        this.department = fields[4];
    }

    public String getDepartment() { return this.department; }

    @Override

    public String toString() {
        String salaryStr;
        if (salary == (long) salary) {
            salaryStr = String.format(Locale.US, "%.0f", salary);
        } else {
            salaryStr = String.format(Locale.US, "%.2f", salary);
        }
        return String.format(Locale.US,"%s,%d,%s,%s", position, id, name, salaryStr);
    }
}
