package com.devoteam.devoteamPoc.EmployeeController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationController {

    @Autowired
    private SimpMessagingTemplate template;

    public void notifyUser(String userId, String message) {
        template.convertAndSendToUser(userId, "/queue/notifications", message);
        System.out.println("Sending notification to user: " + userId + " with message: " + message);
        System.out.println("User prefix: " + template.getUserDestinationPrefix());
        System.out.println("Full destination: /user/" + userId + "/queue/notifications");


    }

}
