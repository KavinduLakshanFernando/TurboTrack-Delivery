$('#regCustomer').on('click', function () {
    let userID = localStorage.getItem("LoggedUserId");
    console.log(userID);

    console.log("registercustomer");
    const fname = $('#firstName').val();
    const lname = $('#lastName').val();
    const birthDate = $('#dateOfBirth').val();
    const gender = $('#gender').val();
    const streetAddress = $('#streetAddress').val();
    const city = $('#city').val();

    if (fname && lname && birthDate && gender && streetAddress && city) {
        $.ajax({
            url: "http://localhost:8080/api/v1/customer/save",
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                fname: fname,
                lname: lname,
                dateOfBirth: birthDate,
                gender: gender,
                streetAddress: streetAddress,
                city: city,
                user: {
                    uid: userID
                }
            }),
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("authToken")
            },
            success: function (response) {
                console.log(response);
                alert("Customer registered successfully!");
                window.location.href = "./CustomerDashBoard.html";
            },
            error: function (xhr, status, error) {
                console.error(error);
            }
        });
    }
})

loadCustomerdataForAdmin();

function loadCustomerdataForAdmin() {
    console.log("loadCustomerdataForAdmin");
    $.ajax({
        url: "http://localhost:8080/api/v1/customer/allCustomers",
        method: "GET",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("authToken")
        },
        success: function (data) {
            $("#customerTableBody").empty();
            data.forEach(customer => {
                $("#customerTableBody").append(`
                    <tr>
                        <td>${customer.fname}</td>
                        <td>${customer.lname}</td>
                        <td>${customer.dateOfBirth}</td>
                        <td>${customer.streetAddress}</td>
                        <td>${customer.city}</td>
                        <td>${customer.gender}</td>
                        <td><button class="delete-btn" onclick="deleteCustomer('${customer.cid}')">Delete</button></td>
                    </tr>
                `);
            });
        },
        error: function () {
            alert("Failed to load customers.");
        }
    });
}

function deleteCustomer(cid) {
    if (confirm("Are you sure you want to delete this customer?")) {
        $.ajax({
            url: `http://localhost:8080/api/v1/customer/delete/${cid}`,
            method: "DELETE",
            contentType: "application/json",
            headers:{
                "Authorization": "Bearer " + localStorage.getItem("authToken")
            },
            success: function(response) {
                alert("Customer deleted successfully!");
                loadDriverdataForAdmin() // refresh table
            },
            error: function(xhr, status, error) {
                alert("Error deleting Customer: " + xhr.responseText);
            }
        });
    }
}

$(document).ready(function () {
    let loggedUserID = localStorage.getItem("LoggedUserId");
    let token = localStorage.getItem("authToken");

    $.ajax({
        url: `http://localhost:8080/api/v1/customer/getPersonalData/${loggedUserID}`,
        method: "GET",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("authToken")
        },
        success: (res) => {
            console.log("res",res)
            console.log("res2",res.data)

            res.forEach(customer => {
                localStorage.setItem("LoggedCustomer", customer.cid);
            })

            let loggedCustomer = localStorage.getItem("LoggedCustomer");
            console.log("loggedCustomer", loggedCustomer);

            $.ajax({

                url: `http://localhost:8080/api/v1/orders/getUserOrder/${loggedCustomer}`,
                method: "GET",
                headers: {
                    "Authorization": "Bearer " + localStorage.getItem("authToken")
                },
                success: (response) => {
                    console.log("response",response)

                    $("#orderTable").empty();
                    response.forEach(order => {
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
                error: function (xhr, status, error) {
                    console.log(error);
                }
            })



        },
        error: function (xhr, status, error) {
            console.log(error);
        }
    })
})