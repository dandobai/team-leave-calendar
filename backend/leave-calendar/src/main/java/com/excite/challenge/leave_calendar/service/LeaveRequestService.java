package com.excite.challenge.leave_calendar.service;

import com.excite.challenge.leave_calendar.model.LeaveRequest;
import com.excite.challenge.leave_calendar.model.TeamMember;
import com.excite.challenge.leave_calendar.constant.LeaveStatus;
import java.time.LocalDate;
import java.util.List;

public interface LeaveRequestService {
    List<LeaveRequest> getAllLeaves();
    boolean hasOverlappingLeave(Long memberId, LocalDate start, LocalDate end, List<LeaveStatus> statuses, Long requestId);
    LeaveRequest saveLeaveRequest(TeamMember member, LocalDate start, LocalDate end, String reason);
    LeaveRequest updateStatus(Long id, LeaveStatus newStatus);
}
