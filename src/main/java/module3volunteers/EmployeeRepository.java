package module3volunteers;

import java.util.List;

public class EmployeeRepository {
    public List<Employee> createEmployees(){
        return List.of(
                new Employee("Jan Kowalski", 17),
                new Employee("Barbara Kowalska", 50),
                new Employee("Janusz Kurdyło", 55),
                new Employee("Zofia Radziwiłł", 16),
                new Employee("Ignacy Smaga", 33),
                new Employee("Ann Cleeves", 53));
    }
}
