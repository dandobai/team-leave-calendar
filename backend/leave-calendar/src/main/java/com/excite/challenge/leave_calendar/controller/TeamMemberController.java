package com.excite.challenge.leave_calendar.controller;

import com.excite.challenge.leave_calendar.model.TeamMember;
import com.excite.challenge.leave_calendar.service.TeamMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/team-members")
@RequiredArgsConstructor
public class TeamMemberController {

    private final TeamMemberService teamMemberService;

    @GetMapping
    public List<TeamMember> getAllMembers() {
        return teamMemberService.getAllMembers();
    }
}
