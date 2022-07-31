package module3volunteers;

import java.util.List;

public class EmployeeReportService {

    public static final int ADULT_AGE = 18;
    private final List<Employee> employees;

    public EmployeeReportService(List<Employee> employees) {

        this.employees = employees;
    }

    public List<Employee> getAdultEmployees() {
        return employees.stream()
                .filter(e -> e.getAge() >= ADULT_AGE)
                .toList();
    }
}
