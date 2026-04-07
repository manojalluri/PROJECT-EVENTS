package com.campusconnect.service;

import com.campusconnect.entity.Activity;
import com.campusconnect.entity.Notification;
import com.campusconnect.entity.Participation;
import com.campusconnect.entity.User;
import com.campusconnect.repository.ActivityRepository;
import com.campusconnect.repository.NotificationRepository;
import com.campusconnect.repository.ParticipationRepository;
import com.campusconnect.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipationService {
    private final ParticipationRepository participationRepository;
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;

    public Participation registerForActivity(Long userId, Long activityId) {
        User user = userRepository.findById(userId).orElseThrow();
        Activity activity = activityRepository.findById(activityId).orElseThrow();

        if (participationRepository.findByUserAndActivity(user, activity).isPresent()) {
            throw new RuntimeException("Already registered for this activity");
        }

        if (activity.getCurrentParticipants() >= activity.getMaxParticipants()) {
            throw new RuntimeException("Activity is full");
        }

        Participation participation = Participation.builder()
                .user(user)
                .activity(activity)
                .status(Participation.Status.REGISTERED)
                .registeredAt(LocalDate.now())
                .build();

        activity.setCurrentParticipants(activity.getCurrentParticipants() + 1);
        activityRepository.save(activity);

        Notification notification = Notification.builder()
                .user(user)
                .message("Your registration for " + activity.getTitle() + " has been confirmed! 🎉")
                .type("success")
                .isRead(false)
                .build();
        notificationRepository.save(notification);

        return participationRepository.save(participation);
    }

    public List<Participation> getUserParticipations(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return participationRepository.findByUser(user);
    }

    public List<Participation> getAllParticipations() {
        return participationRepository.findAll();
    }
}
