package module3volunteers;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {
    private final List<Employee> employees = createEmployees();

    private ArrayList<Employee> createEmployees(){
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Jan Kowalski", 50));
        employees.add(new Employee("Janina Kowalska", 20));
        employees.add(new Employee("Bartłomiej Szumowski", 15));
        return employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
