package com.devoteam.devoteamPoc.EmployeeController;

import com.devoteam.devoteamPoc.Entity.Notification;
import com.devoteam.devoteamPoc.Repo.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class NotificationController {

    @Autowired
    private SimpMessagingTemplate template;
    @Autowired
    private NotificationRepository notificationRepository;

    public void notifyUser(String userId, String message) {
        template.convertAndSendToUser(userId, "/queue/notifications", message);
        System.out.println("Sending notification to user: " + userId + " with message: " + message);
        System.out.println("User prefix: " + template.getUserDestinationPrefix());
        System.out.println("Full destination: /user/" + userId + "/queue/notifications");
        System.out.println("Notification sent successfully.");
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setMessage(message);
        notification.setTimestamp(new Date());
        notificationRepository.save(notification);

        System.out.println("Notification sent successfully.");


    }

}
