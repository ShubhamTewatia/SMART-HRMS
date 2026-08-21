package employee_management.controller;

import employee_management.entity.Attendance;
import employee_management.entity.Employee;
import employee_management.repository.AttendanceRepository;
import employee_management.repository.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@RequestMapping("/attendance")
public class AttendancePageController {

    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;

    public AttendancePageController(
            AttendanceRepository attendanceRepository,
            EmployeeRepository employeeRepository) {

        this.attendanceRepository = attendanceRepository;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public String attendancePage(Model model) {

        model.addAttribute(
                "attendanceList",
                attendanceRepository.findAll());

        model.addAttribute(
                "employees",
                employeeRepository.findAll());

        return "attendance";
    }

    @GetMapping("/checkin/{employeeId}")
    public String checkIn(
            @PathVariable Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found"));

        Attendance attendance = new Attendance();

        attendance.setEmployee(employee);
        attendance.setAttendanceDate(LocalDate.now());
        attendance.setCheckInTime(LocalTime.now());
        attendance.setStatus("PRESENT");

        attendanceRepository.save(attendance);

        return "redirect:/attendance";
    }

    @GetMapping("/checkout/{attendanceId}")
    public String checkOut(
            @PathVariable Long attendanceId) {

        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() ->
                        new RuntimeException("Attendance not found"));

        attendance.setCheckOutTime(LocalTime.now());

        attendanceRepository.save(attendance);

        return "redirect:/attendance";
    }
}