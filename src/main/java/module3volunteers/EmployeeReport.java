package module3volunteers;

import java.util.List;

public class EmployeeReport {

    public static final int ADULT_AGE = 18;

    public List<Employee> getAdultReport(EmployeeRepository company) {
        List<Employee> employees = company.getEmployees();
        return employees.stream()
                .filter(e -> e.getAge() >= ADULT_AGE).toList();

    }
}
