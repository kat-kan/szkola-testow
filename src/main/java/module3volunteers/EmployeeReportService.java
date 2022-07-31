package module3volunteers;

import java.util.List;

public class EmployeeReportService {

    public static final int ADULT_AGE = 18;
    private final EmployeeRepository employeeRepository;

    public EmployeeReportService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAdultEmployees() {
        return employeeRepository.getEmployees().stream()
                .filter(e -> e.getAge() >= ADULT_AGE)
                .toList();
    }
}
