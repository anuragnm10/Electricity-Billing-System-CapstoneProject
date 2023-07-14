package com.group2.capstone.EBPaymentSystem.billing.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group2.capstone.EBPaymentSystem.billing.models.Bill;
import com.group2.capstone.EBPaymentSystem.billing.models.OrderResponse;
import com.group2.capstone.EBPaymentSystem.billing.service.BillingService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private BillingService billService;

	private RazorpayClient client = null;
	
	

	@PostMapping("/createOrder/{billid}")
	public ResponseEntity<?> createOrder(@PathVariable long billid) throws Exception {

		
		Bill bill = billService.getBillFromBillId(billid);

		client = new RazorpayClient("rzp_test_Y9WtUj185aAZ1G", "muEQErTodUmXh3Fzgxbl41wB");
		try {
			JSONObject ob = new JSONObject();
			ob.put("amount", bill.getAmount() * 100);
			ob.put("currency", "INR");
			ob.put("receipt", "txn_235425");

			Order order = client.Orders.create(ob);

			System.out.println(order);
			OrderResponse response = new OrderResponse(order.get("id"),order.get("amount"),order.get("currency")); 

			return ResponseEntity.ok().body(response);
		} catch (RazorpayException e) {
			// Handle exception and return appropriate response
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating order");
		}
	}


}
