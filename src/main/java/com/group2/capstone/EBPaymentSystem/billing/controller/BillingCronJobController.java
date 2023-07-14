package com.group2.capstone.EBPaymentSystem.billing.controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import com.group2.capstone.EBPaymentSystem.authentication.models.User;
import com.group2.capstone.EBPaymentSystem.authentication.services.UserService;
import com.group2.capstone.EBPaymentSystem.billing.models.Property;
import com.group2.capstone.EBPaymentSystem.billing.service.BillingService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Controller
@Transactional
public class BillingCronJobController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BillingService billService;
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Scheduled(cron="00 59 23 28-30 * *")
	public void getAllUsers() {
		List<User> users = userService.getAllConsumers();
		for(User user:users) {
			generateBillEveryMonth(user);
		}
	}
	
	
	public void generateBillEveryMonth(User user) {
       
		System.out.println(user);
		List<Property> properties = billService.getUserProperties(user);
        System.out.println("properties fetched");
       
        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();
        int year = date.getYear();
        for(Property property:properties) {
            billService.calculateBill(property, month, year);
            System.out.println("bill calculated");

		}
		
    }
	
}
