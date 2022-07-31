package module3volunteers;

import java.util.List;

public class EmployeeReportService {

    private final List<Employee> employees;

    public EmployeeReportService(List<Employee> employees) {

        this.employees = employees;
    }

    public List<Employee> getAdultEmployees() {
        return employees;
    }
}
