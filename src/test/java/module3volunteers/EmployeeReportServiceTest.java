package module3volunteers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class EmployeeReportServiceTest {
    public static final int ADULT_AGE = 18;
    private EmployeeReportService employeeReportService;
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        employeeRepository = new EmployeeRepository();
        employeeReportService = new EmployeeReportService(employeeRepository);
    }

    @Test
    @DisplayName("Should return list of employees")
    void shouldReturnListOfEmployees() {
        //when
        List<Employee> adultEmployees = employeeReportService.getAdultEmployees();

        //then
        assertThat(adultEmployees)
                .isNotNull();

    }

    @Test
    @DisplayName("Should return grocery store employees")
    void shouldReturnGroceryStoreEmployees() {
        //when
        List<Employee> adultEmployees = employeeReportService.getAdultEmployees();

        //then
        assertThat(adultEmployees)
                .isNotEmpty();
    }

    @Test
    @DisplayName("Should return list of employees who are 18 or older")
    void shouldReturnListOfEmployeesWhoAre18orOlder() {
        //given
        List<Employee> underAgeEmployees = employeeRepository.getEmployees().stream()
                .filter(e -> e.getAge() < ADULT_AGE)
                .toList();

        //when
        List<Employee> adultEmployees = employeeReportService.getAdultEmployees();

        //then
        assertThat(adultEmployees)
                .isNotEmpty()
                .allMatch(e-> (e.getAge()>= ADULT_AGE))
                .doesNotContainAnyElementsOf(underAgeEmployees);
    }

    @Test
    @DisplayName("Should return list of employees sorted by name")
    void shouldReturnListOfEmployeesSortedByName() {
        //given
        List<Employee> sortedAdultEmployees = employeeRepository.getEmployees().stream()
                .sorted()
                .filter(e -> e.getAge() >= ADULT_AGE)
                .toList();

        //when
        List<Employee> adultEmployees = employeeReportService.getAdultEmployees();

        //then
        assertThat(adultEmployees)
                .isEqualTo(sortedAdultEmployees);
    }
}
