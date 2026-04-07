package com.campusconnect.config;

import com.campusconnect.entity.Activity;
import com.campusconnect.entity.User;
import com.campusconnect.repository.ActivityRepository;
import com.campusconnect.repository.UserRepository;
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
                User admin = new User();
                admin.setName("Super Admin");
                admin.setEmail("admin@campus.edu");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole(User.Role.ADMIN);
                admin.setDepartment("Administration");
                admin.setAvatar("SA");
                admin.setJoinedAt(LocalDate.now());

                userRepository.save(admin);
            }
        };
    }
}
