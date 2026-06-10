package com.excite.challenge.leave_calendar.service.impl;

import com.excite.challenge.leave_calendar.model.LeaveRequest;
import com.excite.challenge.leave_calendar.model.TeamMember;
import com.excite.challenge.leave_calendar.constant.LeaveStatus;
import com.excite.challenge.leave_calendar.repository.LeaveRequestRepository;
import com.excite.challenge.leave_calendar.service.LeaveRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private final LeaveRequestRepository leaveRequestRepository;

    @Override
    public List<LeaveRequest> getAllLeaves() {
        return leaveRequestRepository.findAll();
    }

    @Override
    public boolean hasOverlappingLeave(Long memberId, LocalDate start, LocalDate end, List<LeaveStatus> statuses, Long requestId) {
        return leaveRequestRepository.existsOverlappingLeave(memberId, start, end, statuses, requestId);
    }

    @Override
    @Transactional
    public LeaveRequest saveLeaveRequest(TeamMember member, LocalDate start, LocalDate end, String reason) {
        LeaveRequest request = new LeaveRequest();
        request.setTeamMember(member);
        request.setStartDate(start);
        request.setEndDate(end);
        request.setReason(reason);
        request.setStatus(LeaveStatus.PENDING);
        return leaveRequestRepository.save(request);
    }

    @Override
    @Transactional
    public LeaveRequest updateStatus(Long id, LeaveStatus newStatus) {
        LeaveRequest request = leaveRequestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Szabadság kérelem nem található!"));
        request.setStatus(newStatus);
        return leaveRequestRepository.save(request);
    }
}
