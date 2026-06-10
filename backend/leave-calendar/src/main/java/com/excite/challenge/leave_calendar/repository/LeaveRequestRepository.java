package com.excite.challenge.leave_calendar.repository;

import com.excite.challenge.leave_calendar.model.LeaveRequest;
import com.excite.challenge.leave_calendar.constant.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

    @Query("SELECT COUNT(l) > 0 FROM LeaveRequest l WHERE l.teamMember.id = :memberId " +
            "AND l.status IN (:statuses) " +
            "AND l.startDate <= :endDate AND l.endDate >= :startDate " +
            "AND (:requestId IS NULL OR l.id != :requestId)")
    boolean existsOverlappingLeave(
            @Param("memberId") Long memberId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("statuses") List<LeaveStatus> statuses,
            @Param("requestId") Long requestId
    );
}
