package com.excite.challenge.leave_calendar.controller;

import com.excite.challenge.leave_calendar.dto.OnCallResponseDto;
import com.excite.challenge.leave_calendar.service.LeaveCalendarFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/on-call")
@RequiredArgsConstructor
public class OnCallController {

    private final LeaveCalendarFacade leaveCalendarFacade;

    @GetMapping
    public List<OnCallResponseDto> getOnCallSchedule(
            @RequestParam(defaultValue = "8") int weeks) {
        return leaveCalendarFacade.getOnCallSchedule(weeks);
    }
}
