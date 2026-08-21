package employee_management.controller;

import employee_management.entity.Department;
import employee_management.repository.DepartmentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/departments")
public class DepartmentPageController {

    private final DepartmentRepository departmentRepository;

    public DepartmentPageController(
            DepartmentRepository departmentRepository) {

        this.departmentRepository = departmentRepository;
    }

    @GetMapping
    public String listDepartments(Model model) {

        model.addAttribute(
                "departments",
                departmentRepository.findAll()
        );

        model.addAttribute(
                "department",
                new Department()
        );

        return "departments";
    }

    @PostMapping("/save")
    public String saveDepartment(
            @ModelAttribute Department department) {

        departmentRepository.save(department);

        return "redirect:/departments";
    }

    @GetMapping("/delete/{id}")
    public String deleteDepartment(
            @PathVariable Long id) {

        departmentRepository.deleteById(id);

        return "redirect:/departments";
    }
}