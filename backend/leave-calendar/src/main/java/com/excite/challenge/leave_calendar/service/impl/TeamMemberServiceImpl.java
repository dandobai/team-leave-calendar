package com.excite.challenge.leave_calendar.service.impl;

import com.excite.challenge.leave_calendar.model.TeamMember;
import com.excite.challenge.leave_calendar.repository.TeamMemberRepository;
import com.excite.challenge.leave_calendar.service.TeamMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamMemberServiceImpl implements TeamMemberService {

    private final TeamMemberRepository teamMemberRepository;

    @Override
    public List<TeamMember> getAllMembers() {
        return teamMemberRepository.findAll();
    }

    @Override
    public TeamMember getMemberById(Long id) {
        return teamMemberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Csapattag nem található a következő ID-val: " + id));
    }
}
