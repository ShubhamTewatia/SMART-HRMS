package employee_management.controller;

import employee_management.entity.Employee;
import employee_management.export.EmployeeExcelExporter;
import employee_management.repository.DepartmentRepository;
import employee_management.repository.EmployeeRepository;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeePageController {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeePageController(
            EmployeeRepository employeeRepository,
            DepartmentRepository departmentRepository) {

        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping
    public String listEmployees(Model model) {

        model.addAttribute(
                "employees",
                employeeRepository.findAll());

        model.addAttribute(
                "employee",
                new Employee());

        model.addAttribute(
                "departments",
                departmentRepository.findAll());

        return "employees";
    }

    @PostMapping("/save")
    public String saveEmployee(
            @ModelAttribute Employee employee) {

        employeeRepository.save(employee);

        return "redirect:/employees";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(
            @PathVariable Long id) {

        employeeRepository.deleteById(id);

        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(
            @PathVariable Long id,
            Model model) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found"));

        model.addAttribute("employee", employee);

        model.addAttribute(
                "departments",
                departmentRepository.findAll());

        return "employee-form";
    }

    @PostMapping("/update")
    public String updateEmployee(
            @ModelAttribute Employee employee) {

        employeeRepository.save(employee);

        return "redirect:/employees";
    }
    @GetMapping("/search")
    public String searchEmployee(
            @RequestParam String keyword,
            Model model) {

        model.addAttribute(
                "employees",
                employeeRepository
                        .findByFirstNameContainingIgnoreCase(keyword));

        model.addAttribute(
                "employee",
                new Employee());

        model.addAttribute(
                "departments",
                departmentRepository.findAll());

        return "employees";
    }
    @GetMapping("/export")
    public void exportToExcel(
            HttpServletResponse response)
            throws Exception {

        response.setContentType(
                "application/octet-stream");

        response.setHeader(
                "Content-Disposition",
                "attachment; filename=employees.xlsx");

        List<Employee> employees =
                employeeRepository.findAll();

        EmployeeExcelExporter exporter =
                new EmployeeExcelExporter(employees);

        exporter.export(response);
    }
}