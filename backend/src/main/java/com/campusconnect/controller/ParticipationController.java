package com.campusconnect.controller;

import com.campusconnect.entity.Participation;
import com.campusconnect.service.ParticipationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/participation")
@RequiredArgsConstructor
public class ParticipationController {
    private final ParticipationService participationService;

    @PostMapping("/{userId}/{activityId}")
    public ResponseEntity<Participation> register(@PathVariable Long userId, @PathVariable Long activityId) {
        return ResponseEntity.ok(participationService.registerForActivity(userId, activityId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Participation>> getUserParticipations(@PathVariable Long userId) {
        return ResponseEntity.ok(participationService.getUserParticipations(userId));
    }

    @GetMapping
    public ResponseEntity<List<Participation>> getAllParticipations() {
        return ResponseEntity.ok(participationService.getAllParticipations());
    }
}
