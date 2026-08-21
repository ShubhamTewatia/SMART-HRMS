package employee_management.controller;

import employee_management.entity.Employee;
import employee_management.entity.LeaveRequest;
import employee_management.repository.EmployeeRepository;
import employee_management.repository.LeaveRequestRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
public class LeaveController {

    private final LeaveRequestRepository leaveRepository;
    private final EmployeeRepository employeeRepository;

    public LeaveController(
            LeaveRequestRepository leaveRepository,
            EmployeeRepository employeeRepository) {

        this.leaveRepository = leaveRepository;
        this.employeeRepository = employeeRepository;
    }

    @PostMapping("/{employeeId}")
    public LeaveRequest applyLeave(
            @PathVariable Long employeeId,
            @RequestBody LeaveRequest leaveRequest) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        leaveRequest.setEmployee(employee);
        leaveRequest.setStatus("PENDING");

        return leaveRepository.save(leaveRequest);
    }

    @GetMapping
    public List<LeaveRequest> getAllLeaves() {
        return leaveRepository.findAll();
    }

    @PutMapping("/{leaveId}/approve")
    public LeaveRequest approveLeave(
            @PathVariable Long leaveId) {

        LeaveRequest leave = leaveRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave not found"));

        leave.setStatus("APPROVED");

        return leaveRepository.save(leave);
    }

    @PutMapping("/{leaveId}/reject")
    public LeaveRequest rejectLeave(
            @PathVariable Long leaveId) {

        LeaveRequest leave = leaveRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave not found"));

        leave.setStatus("REJECTED");

        return leaveRepository.save(leave);
    }
}