package employee_management.config;

import employee_management.entity.Department;
import employee_management.entity.Employee;
import employee_management.repository.DepartmentRepository;
import employee_management.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public DataLoader(
            DepartmentRepository departmentRepository,
            EmployeeRepository employeeRepository) {

        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... args) {

        if (departmentRepository.count() == 0) {

            Department it = new Department();
            it.setDepartmentName("IT");
            it.setLocation("Building A");

            Department hr = new Department();
            hr.setDepartmentName("HR");
            hr.setLocation("Building B");

            Department finance = new Department();
            finance.setDepartmentName("Finance");
            finance.setLocation("Building C");

            departmentRepository.save(it);
            departmentRepository.save(hr);
            departmentRepository.save(finance);

            Employee emp1 = new Employee();
            emp1.setFirstName("John");
            emp1.setLastName("Doe");
            emp1.setEmail("john@company.com");
            emp1.setPhone("9999999999");
            emp1.setDesignation("Developer");
            emp1.setSalary(50000.0);
            emp1.setDepartment(it);

            Employee emp2 = new Employee();
            emp2.setFirstName("Rahul");
            emp2.setLastName("Sharma");
            emp2.setEmail("rahul@company.com");
            emp2.setPhone("8888888888");
            emp2.setDesignation("HR Executive");
            emp2.setSalary(40000.0);
            emp2.setDepartment(hr);

            employeeRepository.save(emp1);
            employeeRepository.save(emp2);

            System.out.println("Sample data loaded.");
        }
    }
}