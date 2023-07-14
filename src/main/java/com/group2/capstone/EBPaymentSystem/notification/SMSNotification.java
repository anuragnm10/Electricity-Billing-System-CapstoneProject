package com.group2.capstone.EBPaymentSystem.notification;

import com.group2.capstone.EBPaymentSystem.authentication.models.User;

public class SMSNotification implements NotificationService {
    @Override
    public void notify(User user, String message) {
//Implement method where the message is sent to user along with the bill as a URL
    }
}
