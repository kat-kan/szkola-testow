package module3volunteers;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class EmployeeReportServiceTest {
    private EmployeeReportService employeeReportService;

    @BeforeEach
    void setUp() {
        employeeReportService = new EmployeeReportService(List.of());
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
        //given
        List<Employee> employees = List.of(new Employee("Jan Kowalski", 17), new Employee("Barbara Kowalska", 40));
        employeeReportService = new EmployeeReportService(employees);

        //when
        List<Employee> adultEmployees = employeeReportService.getAdultEmployees();

        //then
        Assertions.assertThat(adultEmployees)
                .isNotEmpty();
    }

    /*    public static final int ADULT_AGE = 18;

    @Test
    @DisplayName("Should return list of employees who are 18 or older")
    void shouldReturnListOfEmployeesWhoAre18orOlder() {
        //given

        EmployeeReport employeeReport = new EmployeeReport();
        EmployeeRepository employeeRepository = new EmployeeRepository();

        //when

        List<Employee> adults = employeeReport.getAdultReport(employeeRepository);

        //then
        Assertions.assertThat(adults)
                .isNotEmpty()
                .allMatch(e-> (e.getAge()>= ADULT_AGE));
    }*/
}
