package com.campusconnect.config;

import com.campusconnect.entity.Activity;
import com.campusconnect.entity.User;
import com.campusconnect.repository.ActivityRepository;
import com.campusconnect.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.LocalDate;
import java.util.Arrays;

@Configuration
public class DataSeeder {
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, ActivityRepository activityRepository) {
        return args -> {
            if (userRepository.count() == 0) {
                User admin = User.builder()
                        .name("Super Admin")
                        .email("admin@campus.edu")
                        .password(passwordEncoder.encode("admin123"))
                        .role(User.Role.ADMIN)
                        .department("Administration")
                        .avatar("SA")
                        .joinedAt(LocalDate.now())
                        .build();

                userRepository.save(admin);
            }
        };
    }
}
