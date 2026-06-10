package com.excite.challenge.leave_calendar.service;

import com.excite.challenge.leave_calendar.dto.LeaveRequestDto;
import com.excite.challenge.leave_calendar.dto.OnCallResponseDto;
import com.excite.challenge.leave_calendar.model.LeaveRequest;
import java.util.List;

public interface LeaveCalendarFacade {
    LeaveRequest createLeaveRequest(LeaveRequestDto dto);
    List<OnCallResponseDto> getOnCallSchedule(int numberOfWeeks);
}
