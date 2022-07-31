package module3volunteers;

import java.util.List;

public class EmployeeRepository {
    private final static List<Employee> employees = List.of(new Employee("Jan Kowalski", 17), new Employee("Barbara Kowalska", 50));

    public List<Employee> getEmployees() {
        return employees;
    }
}
