package employee_management.controller;

import employee_management.entity.Employee;
import employee_management.entity.LeaveRequest;
import employee_management.repository.EmployeeRepository;
import employee_management.repository.LeaveRequestRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/leaves")
public class LeavePageController {

    private final LeaveRequestRepository leaveRepository;
    private final EmployeeRepository employeeRepository;

    public LeavePageController(
            LeaveRequestRepository leaveRepository,
            EmployeeRepository employeeRepository) {

        this.leaveRepository = leaveRepository;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public String leavePage(Model model) {

        model.addAttribute(
                "leaves",
                leaveRepository.findAll());

        model.addAttribute(
                "employees",
                employeeRepository.findAll());

        model.addAttribute(
                "leaveRequest",
                new LeaveRequest());

        return "leaves";
    }

    @PostMapping("/apply/{employeeId}")
    public String applyLeave(
            @PathVariable Long employeeId,
            @ModelAttribute LeaveRequest leaveRequest) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found"));

        leaveRequest.setEmployee(employee);
        leaveRequest.setStatus("PENDING");

        leaveRepository.save(leaveRequest);

        return "redirect:/leaves";
    }

    @GetMapping("/approve/{id}")
    public String approveLeave(
            @PathVariable Long id) {

        LeaveRequest leave = leaveRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Leave not found"));

        leave.setStatus("APPROVED");

        leaveRepository.save(leave);

        return "redirect:/leaves";
    }

    @GetMapping("/reject/{id}")
    public String rejectLeave(
            @PathVariable Long id) {

        LeaveRequest leave = leaveRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Leave not found"));

        leave.setStatus("REJECTED");

        leaveRepository.save(leave);

        return "redirect:/leaves";
    }
}