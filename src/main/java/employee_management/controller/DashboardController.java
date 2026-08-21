package employee_management.controller;

import employee_management.repository.AttendanceRepository;
import employee_management.repository.DepartmentRepository;
import employee_management.repository.EmployeeRepository;
import employee_management.repository.LeaveRequestRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Controller
public class DashboardController {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final AttendanceRepository attendanceRepository;
    private final LeaveRequestRepository leaveRepository;

    public DashboardController(
            EmployeeRepository employeeRepository,
            DepartmentRepository departmentRepository,
            AttendanceRepository attendanceRepository,
            LeaveRequestRepository leaveRepository) {

        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.attendanceRepository = attendanceRepository;
        this.leaveRepository = leaveRepository;
    }

    // Dashboard UI
    @GetMapping("/")
    public String dashboard(Model model) {

        model.addAttribute(
                "employees",
                employeeRepository.count());

        model.addAttribute(
                "departments",
                departmentRepository.count());

        model.addAttribute(
                "attendance",
                attendanceRepository.count());

        model.addAttribute(
                "leaves",
                leaveRepository.count());

        model.addAttribute(
                "today",
                LocalDate.now());

        return "dashboard";
    }

    // Dashboard API
    @GetMapping("/api/dashboard")
    @ResponseBody
    public Map<String, Object> getDashboard() {

        Map<String, Object> dashboard = new HashMap<>();

        dashboard.put(
                "totalEmployees",
                employeeRepository.count());

        dashboard.put(
                "totalDepartments",
                departmentRepository.count());

        dashboard.put(
                "totalAttendanceRecords",
                attendanceRepository.count());

        dashboard.put(
                "totalLeaveRequests",
                leaveRepository.count());

        dashboard.put(
                "generatedDate",
                LocalDate.now());

        return dashboard;
    }
}