package com.excite.challenge.leave_calendar.controller;

import com.excite.challenge.leave_calendar.dto.LeaveRequestDto;
import com.excite.challenge.leave_calendar.model.LeaveRequest;
import com.excite.challenge.leave_calendar.constant.LeaveStatus;
import com.excite.challenge.leave_calendar.service.LeaveCalendarFacade;
import com.excite.challenge.leave_calendar.service.LeaveRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
@RequiredArgsConstructor
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;
    private final LeaveCalendarFacade leaveCalendarFacade;

    @GetMapping
    public List<LeaveRequest> getAllLeaves() {
        return leaveRequestService.getAllLeaves();
    }

    @PostMapping
    public LeaveRequest createLeave(@RequestBody LeaveRequestDto dto) {
        // A Facade koordinálja a validációt és mentést
        return leaveCalendarFacade.createLeaveRequest(dto);
    }

    @PutMapping("/{id}/status")
    public LeaveRequest updateStatus(
            @PathVariable Long id,
            @RequestParam LeaveStatus status) {
        return leaveRequestService.updateStatus(id, status);
    }
}
