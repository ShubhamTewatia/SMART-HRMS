package employee_management.controller;

import employee_management.repository.AttendanceRepository;
import employee_management.repository.DepartmentRepository;
import employee_management.repository.EmployeeRepository;
import employee_management.repository.LeaveRequestRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportsController {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final AttendanceRepository attendanceRepository;
    private final LeaveRequestRepository leaveRepository;

    public ReportsController(
            EmployeeRepository employeeRepository,
            DepartmentRepository departmentRepository,
            AttendanceRepository attendanceRepository,
            LeaveRequestRepository leaveRepository) {

        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.attendanceRepository = attendanceRepository;
        this.leaveRepository = leaveRepository;
    }

    @GetMapping("/reports")
    public String reports(Model model) {

        model.addAttribute("employees",
                employeeRepository.count());

        model.addAttribute("departments",
                departmentRepository.count());

        model.addAttribute("attendance",
                attendanceRepository.count());

        model.addAttribute("leaves",
                leaveRepository.count());

        return "reports";
    }
}