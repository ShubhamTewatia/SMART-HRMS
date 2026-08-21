package employee_management.controller;

import employee_management.repository.AttendanceRepository;
import employee_management.repository.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.YearMonth;

@Controller
public class AttendanceSummaryController {
    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;

    public AttendanceSummaryController(AttendanceRepository attendanceRepository,
                                       EmployeeRepository employeeRepository) {
        this.attendanceRepository = attendanceRepository;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/attendance/summary")
    public String summary(Model model) {
        LocalDate today = LocalDate.now();
        YearMonth month = YearMonth.from(today);
        var records = attendanceRepository.findAll();
        long todayPresent = records.stream().filter(a -> today.equals(a.getAttendanceDate()) && "PRESENT".equalsIgnoreCase(a.getStatus())).count();
        long monthPresent = records.stream().filter(a -> a.getAttendanceDate() != null && YearMonth.from(a.getAttendanceDate()).equals(month) && "PRESENT".equalsIgnoreCase(a.getStatus())).count();
        long monthComplete = records.stream().filter(a -> a.getAttendanceDate() != null && YearMonth.from(a.getAttendanceDate()).equals(month) && a.getCheckOutTime() != null).count();
        model.addAttribute("records", records.stream().sorted((a,b) -> b.getAttendanceDate().compareTo(a.getAttendanceDate())).limit(30).toList());
        model.addAttribute("employees", employeeRepository.findAll());
        model.addAttribute("todayPresent", todayPresent);
        model.addAttribute("monthPresent", monthPresent);
        model.addAttribute("monthComplete", monthComplete);
        model.addAttribute("monthName", month.getMonth().toString());
        return "attendance-summary";
    }
}
