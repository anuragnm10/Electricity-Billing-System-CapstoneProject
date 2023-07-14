package com.group2.capstone.EBPaymentSystem.notification;

import com.group2.capstone.EBPaymentSystem.authentication.models.User;

public interface NotificationService {
    void notify(User user, String message);

//    public void notify(String message);
}
