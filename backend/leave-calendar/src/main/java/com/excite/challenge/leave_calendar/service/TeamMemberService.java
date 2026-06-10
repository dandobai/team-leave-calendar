package com.excite.challenge.leave_calendar.service;

import com.excite.challenge.leave_calendar.model.TeamMember;
import java.util.List;

public interface TeamMemberService {
    List<TeamMember> getAllMembers();
    TeamMember getMemberById(Long id);
}
