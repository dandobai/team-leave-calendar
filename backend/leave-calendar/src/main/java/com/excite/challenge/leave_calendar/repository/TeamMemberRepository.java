package com.excite.challenge.leave_calendar.repository;

import com.excite.challenge.leave_calendar.model.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {
    Optional<TeamMember> findByName(String name);
}
