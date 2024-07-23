package com.devoteam.devoteamPoc.EmployeeController;


import com.devoteam.devoteamPoc.Entity.Notification;
import com.devoteam.devoteamPoc.Repo.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationFetchController {
    @Autowired
    private NotificationRepository notificationRepository;

    @GetMapping
    public List<Notification> getUserNotifications(Principal principal) {

        return notificationRepository.findByUserId(principal.getName());
    }



}
