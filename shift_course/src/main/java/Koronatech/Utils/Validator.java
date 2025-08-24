package Koronatech.Utils;

public class Validator {

    public static boolean isEnoughFields(String[] fields) {
        return fields.length == 5;
    }

    public static boolean isEmployee(String field) {
        return field.equals("Employee");
    }

    public static boolean isManager(String field) {
        return field.equals("Manager");
    }

    public static boolean isValidId(String field) {
        try {
            int id = Integer.parseInt(field);
            if (id<1) return false;
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isValidSalary(String field) {
        try {
            double salary = Double.parseDouble(field);
            if (salary<=0) return false;
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean checkLine(String[] fields) {
        if (!isEnoughFields(fields)) return false;
        if (isEmployee(fields[0])) {
            return isValidSalary(fields[3]) && isValidId(fields[1]) && isValidId(fields[4]);
        }
        if (isManager(fields[0])) {
            return isValidSalary(fields[3]) && isValidId(fields[1]);
        }
        return false;
    }
}
