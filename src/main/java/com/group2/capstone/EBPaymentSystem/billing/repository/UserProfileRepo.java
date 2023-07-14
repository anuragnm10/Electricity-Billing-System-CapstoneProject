package com.group2.capstone.EBPaymentSystem.billing.repository;

import com.group2.capstone.EBPaymentSystem.billing.models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepo extends JpaRepository<UserProfile, Long> {


}
