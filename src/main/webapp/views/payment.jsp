<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Razorpay Payment</title>
    <link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://checkout.razorpay.com/v1/checkout.js"></script>
</head>
<body>
    <h1>Razorpay Payment Page</h1>

    <form id="paymentForm" method="POST">
        <input type="text" name="billId" id="billId" />
        <button type = "button" id="payButton" onclick="javascript:initiatePayment()">Pay Now</button>
    </form>

    <script type="text/javascript">
        function initiatePayment() {
        	
            var billId = document.getElementById("billId").value;
            console.log("billid=",billId);
            // Make an API call to your backend to fetch the bill and create an order
            // Replace 'YOUR_BACKEND_ENDPOINT' with the actual backend endpoint for fetching bill and creating an order
            fetch('/payment/createOrder/'+billId, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ billId: billId })
            })
            .then(response => response.json())
            .then(data => {
                // Capture the order ID from the response
                const orderId = data.orderId;

                // Initialize the Razorpay checkout form
                var options = {
                    key: 'rzp_test_Y9WtUj185aAZ1G',  // Replace with your Razorpay API key
                    amount: data.amount,  // Payment amount in paise or smallest currency unit
                    currency: data.currency,
                    order_id: orderId,
                    name: 'Electricity Bill Payment',
                    description: 'Payment for bill'+orderId,
                    handler: function(response) {
                        // Handle the payment success response
                        console.log(response);
                        // Redirect the user to a success page or perform any necessary actions
                    },
                    prefill: {
                        name: '',
                        email: ''
                    }
                };
                var rzp = new Razorpay(options);
                rzp.open();
            })
            .catch(error => {
                console.error('Error:', error);
                // Handle the error response
            });
        }
    </script>
</body>
</html>
