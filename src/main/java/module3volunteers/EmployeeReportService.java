package module3volunteers;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class EmployeeReportService {

    public static final int ADULT_AGE = 18;
    private final EmployeeRepository employeeRepository;

    public EmployeeReportService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAdultEmployees() {
        return employeeRepository.createEmployees().stream()
                .filter(e -> e.getAge() >= ADULT_AGE)
                .peek(e-> e.setName(e.getName().toUpperCase(Locale.ROOT)))
                .sorted(Comparator.reverseOrder())
                .toList();
    }
}
