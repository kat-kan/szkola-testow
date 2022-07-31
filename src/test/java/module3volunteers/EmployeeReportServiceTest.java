package module3volunteers;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeReportServiceTest {
    public static final int ADULT_AGE = 18;
    private EmployeeReportService employeeReportService;
    private List<Employee> employees;

    @BeforeEach
    void setUp() {
        employees = List.of(new Employee("Jan Kowalski", 17), new Employee("Barbara Kowalska", 40));
        employeeReportService = new EmployeeReportService(employees);
    }

    @Test
    @DisplayName("Should return list of employees")
    void shouldReturnListOfEmployees() {
        //when
        List<Employee> adultEmployees = employeeReportService.getAdultEmployees();

        //then
        Assertions.assertThat(adultEmployees)
                .isNotNull();

    }

    @Test
    @DisplayName("Should return grocery store employees")
    void shouldReturnGroceryStoreEmployees() {
        //when
        List<Employee> adultEmployees = employeeReportService.getAdultEmployees();

        //then
        Assertions.assertThat(adultEmployees)
                .isNotEmpty();
    }

    @Test
    @DisplayName("Should return list of employees who are 18 or older")
    void shouldReturnListOfEmployeesWhoAre18orOlder() {
        //given
        List<Employee> underAgeEmployees = employees.stream()
                .filter(e -> e.getAge() < ADULT_AGE)
                .toList();

        //when
        List<Employee> adultEmployees = employeeReportService.getAdultEmployees();

        //then
        Assertions.assertThat(adultEmployees)
                .isNotEmpty()
                .allMatch(e-> (e.getAge()>= ADULT_AGE))
                .doesNotContainAnyElementsOf(underAgeEmployees);
    }
}
