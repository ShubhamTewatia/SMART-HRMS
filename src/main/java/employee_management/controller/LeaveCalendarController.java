package employee_management.controller;

import employee_management.repository.LeaveRequestRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LeaveCalendarController {
    private final LeaveRequestRepository leaveRepository;
    public LeaveCalendarController(LeaveRequestRepository leaveRepository) { this.leaveRepository = leaveRepository; }

    @GetMapping("/leaves/calendar")
    public String calendar(Model model) {
        model.addAttribute("leaves", leaveRepository.findAll().stream()
                .sorted((a,b) -> b.getStartDate().compareTo(a.getStartDate())).toList());
        return "leave-calendar";
    }
}
