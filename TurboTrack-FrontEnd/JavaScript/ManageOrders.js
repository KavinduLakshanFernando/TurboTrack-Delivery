loadallOrders()
getPendingOrders();
function getPendingOrders() {
    let userID = localStorage.getItem("LoggedUserId");
    $.ajax({
        url: "http://localhost:8080/api/v1/orders/pendingOrders", // Your backend endpoint
        method: "GET",
        contentType: "application/json",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("authToken")
        },
        success: function (orders) {
            // console.log(orders);
            const container = $("#ordersCard");
            container.empty();
            // Clear previous cards

            orders.forEach(order => {
                const card = `
             <div class="order-card">
                <div class="row table-header m-0">
                <div id="operator" class="col-lg-2 col-md-2">${order.customer.fname}</div>
                <div id="location" class="col-lg-2 col-md-2">${order.pickupPoint}</div>
                <div id="dueDate" class="col-lg-2 col-md-2">${order.deliveryDate}</div>
                <div id="vtype" class="col-lg-2 col-md-2">${order.vehicleType}</div>
                <div id="priority" class="col-lg-2 col-md-2">${order.priority}</div>
                <div id="status" class="col-lg-2 col-md-2">${order.status}</div>
                <div class="col-lg-2 col-md-2">
                <button class="btn btn-success btn-sm" onclick="confirmOrder('${order.id}')">Confirm</button>
                <button class="btn btn-danger btn-sm" onclick="deleteOrder('${order.id}')">Delete</button>
        </div>
                </div>
            </div>
                `;
                container.append(card);
            });
        },
        error: function (xhr, status, error) {
            console.error("Error fetching orders:", error);
        }
    });
}

$(document).ready(function () {
    let loggedUserID = localStorage.getItem("LoggedUserId");
    console.log("loggedUserID", loggedUserID);

    $.ajax({
        url: `http://localhost:8080/api/v1/customer/getPersonalData/${loggedUserID}`,
        method: "GET",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("authToken")
        },
        success: function (response) {
            console.log(response);

            const customer = response[0];
            localStorage.setItem("loggedCustomerId", customer.cid);
            const loggedCustomerId = customer.cid;


            $('#Orderbtn').on('click', function () {
                console.log("order");
                let userID = localStorage.getItem("LoggedUserId");
                console.log(userID);

                let pickupPoint = $('#pickupPoint').val();
                let deliveryAddress = $('#deliveryAddress').val();
                let vehicleType = $('#vehicleType').val();
                let reciverContact = $('#ReceiverContact').val();
                let deliveryDate = $('#deliveryDate').val();
                let distance = $('#distance').val();
                let deliveryAmount = $('#deliveryAmount').val();
                let status = $('#status').val();
                let priority = $('#priority').val();

                $.ajax({
                    url: "http://localhost:8080/api/v1/orders/save",
                    method: "POST",
                    contentType: "application/json",
                    data: JSON.stringify({
                        pickupPoint: pickupPoint,
                        deliveryAddress: deliveryAddress,
                        vehicleType: vehicleType,
                        receiverContact: reciverContact,
                        deliveryDate: deliveryDate,
                        distance: distance,
                        deliveryAmount: deliveryAmount,
                        status: status,
                        priority: priority,
                        customer: {
                            cid: loggedCustomerId
                        }
                    }),
                    headers: {
                        "Authorization": "Bearer " + localStorage.getItem("authToken")
                    },
                    success: function (response) {
                        Swal.fire({
                            icon: 'success',
                            title: 'Success!',
                            text: 'Your action was completed successfully.',
                            timer: 2500,
                            showConfirmButton: false
                        });
                        window.location.href = "./CustomerDashBoard.html";
                        console.log("Order placed successfully:", response);
                        getPendingOrders();
                    },
                    error: function (xhr, status, error) {
                        Swal.fire({
                            icon: 'error',
                            title: 'Oops...',
                            text: 'Something went wrong. Please try again!',
                        });
                        console.error("Error placing order:", error);
                    }
                });
            })

        }
        }
    );
    loadallOrders();
})

function loadallOrders() {
    $.ajax({
        // url: "http://localhost:8080/api/v1/orders/allOrders",
        method: "GET",
        contentType: "application/json",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("authToken")
        },
        success: function (orders) {
            console.log(orders);
            $("#orderTable").empty();
            orders.forEach(order => {
                $("#orderTable").append(`
                    <tr>
                        <td>${order.deliveryAddress}</td>
                        <td>${order.pickupPoint}</td>
                        <td>${order.deliveryDate}</td>
                        <td>${order.deliveryAmount}</td>
                        <td>${order.distance}</td>
                        <td>${order.receiverContact}</td>
                        <td>${order.priority}</td>
                        <td>${order.vehicleType}</td>
                        <td>${order.status}</td>
                        <td><button class="delete-btn" onclick="deleteRow(this)">Delete</button></td>
                    </tr>
                `);
            });
        },
        error: function () {
            alert("Failed to load orders.");
        }
    });
}