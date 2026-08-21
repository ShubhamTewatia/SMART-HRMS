package employee_management.controller;

import employee_management.entity.Employee;
import employee_management.repository.AttendanceRepository;
import employee_management.repository.EmployeeRepository;
import employee_management.repository.LeaveRequestRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;

@Controller
public class EmployeeProfileController {
    private final EmployeeRepository employeeRepository;
    private final AttendanceRepository attendanceRepository;
    private final LeaveRequestRepository leaveRepository;

    public EmployeeProfileController(EmployeeRepository employeeRepository,
                                     AttendanceRepository attendanceRepository,
                                     LeaveRequestRepository leaveRepository) {
        this.employeeRepository = employeeRepository;
        this.attendanceRepository = attendanceRepository;
        this.leaveRepository = leaveRepository;
    }

    @GetMapping("/employees/{id}/profile")
    public String profile(@PathVariable Long id, Model model) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        var attendance = attendanceRepository.findAll().stream()
                .filter(a -> a.getEmployee() != null && id.equals(a.getEmployee().getId()))
                .sorted((a,b) -> b.getAttendanceDate().compareTo(a.getAttendanceDate()))
                .limit(8).toList();
        var leaves = leaveRepository.findAll().stream()
                .filter(l -> l.getEmployee() != null && id.equals(l.getEmployee().getId()))
                .sorted((a,b) -> b.getStartDate().compareTo(a.getStartDate()))
                .limit(6).toList();
        long present = attendanceRepository.findAll().stream()
                .filter(a -> a.getEmployee() != null && id.equals(a.getEmployee().getId()))
                .filter(a -> "PRESENT".equalsIgnoreCase(a.getStatus())).count();
        long approvedLeaves = leaves.stream().filter(l -> "APPROVED".equalsIgnoreCase(l.getStatus())).count();
        model.addAttribute("employee", employee);
        model.addAttribute("attendance", attendance);
        model.addAttribute("leaves", leaves);
        model.addAttribute("presentCount", present);
        model.addAttribute("approvedLeaves", approvedLeaves);
        model.addAttribute("today", LocalDate.now());
        return "employee-profile";
    }
}
