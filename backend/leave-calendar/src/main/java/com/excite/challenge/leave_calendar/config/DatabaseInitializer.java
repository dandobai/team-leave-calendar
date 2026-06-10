package com.excite.challenge.leave_calendar.config;

import com.excite.challenge.leave_calendar.model.TeamMember;
import com.excite.challenge.leave_calendar.repository.TeamMemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class DatabaseInitializer {

    @Bean
    CommandLineRunner initDatabase(TeamMemberRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.saveAll(List.of(
                        new TeamMember(null, "Alice"),
                        new TeamMember(null, "Bob"),
                        new TeamMember(null, "Charlie"),
                        new TeamMember(null, "Diana")
                ));
                System.out.println(">> Database pre-populated with default team members.");
            }
        };
    }
}
