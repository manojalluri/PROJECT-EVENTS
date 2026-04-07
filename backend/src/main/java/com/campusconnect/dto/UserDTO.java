package com.campusconnect.dto;

import com.campusconnect.entity.User;
import lombok.Data;
import java.time.LocalDate;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private User.Role role;
    private String department;
    private String year;
    private String avatar;
    private LocalDate joinedAt;
}
