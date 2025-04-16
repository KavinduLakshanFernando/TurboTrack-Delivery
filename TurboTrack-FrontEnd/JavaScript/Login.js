console.log("login form");

$(document).ready(function () {
    $("#loggin").click(() => {
        let email = $("#email").val();
        let password = $("#password").val();

        $.ajax({
            url: "http://localhost:8080/api/v1/auth/authenticate",
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                email: email,
                password: password,
            }),
            success: function (response) {
                let Authtoken = response.data.token;
                let rolee = response.data.role;
                let name = response.data.name;
                let uid = response.data.id;

                localStorage.setItem("authToken", Authtoken);
                localStorage.setItem("userEmail", email);
                localStorage.setItem("Role", rolee);
                localStorage.setItem("name", name);
                localStorage.setItem("LoggedUserId", uid);

                console.log("userID", uid);
                console.log(Authtoken);
                console.log(rolee);

                Swal.fire({
                    icon: 'success',
                    title: 'Login Successful',
                    text: `Welcome ${name}!`,
                    showConfirmButton: false,
                    timer: 1500
                }).then(() => {
                    if (rolee === "ADMIN") {
                        window.location.href = "./AdminDashboard.html";
                    } else if (rolee === "DRIVER") {
                        window.location.href = "./DriverRegistration.html";
                    } else if (rolee === "CUSTOMER") {
                        window.location.href = "./CustomerRegistration.html";
                    } else {
                        Swal.fire({
                            icon: 'warning',
                            title: 'Unknown Role',
                            text: 'Redirecting to homepage...',
                        }).then(() => {
                            window.location.href = "./index.html";
                        });
                    }
                });
            },
            error: function (xhr, status, error) {
                console.error("Error:", error);
                Swal.fire({
                    icon: 'error',
                    title: 'Login Failed',
                    text: 'Invalid credentials or server error.'
                });
            }
        });
    });
});


//login 2
$('#logging2').on('click', function () {
    const email = $('#email2').val();
    const password = $('#password2').val();

    $.ajax({
        url: "http://localhost:8080/api/v1/auth/authenticate",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            email: email,
            password: password,
        }),
        success: function (response) {
            let Authtoken = response.data.token;
            let rolee = response.data.role;
            let name = response.data.name;
            let uid = response.data.id;

            localStorage.setItem("authToken", Authtoken);
            localStorage.setItem("userEmail", email);
            localStorage.setItem("Role", rolee);
            localStorage.setItem("name", name);
            localStorage.setItem("LoggedUserId", uid);

            console.log("userID", uid);
            console.log(Authtoken);
            console.log(rolee);

            Swal.fire({
                icon: 'success',
                title: 'Login Successful',
                text: `Welcome ${name}!`,
                showConfirmButton: false,
                timer: 1500
            }).then(() => {
                if (rolee === "ADMIN") {
                    window.location.href = "./AdminDashboard.html";
                } else if (rolee === "DRIVER") {
                    window.location.href = "./OrdersManageDriver.html";
                } else if (rolee === "CUSTOMER") {
                    window.location.href = "./CustomerDashBoard.html";
                } else {
                    Swal.fire({
                        icon: 'warning',
                        title: 'Unknown Role',
                        text: 'Redirecting to homepage...',
                    }).then(() => {
                        window.location.href = "./index.html";
                    });
                }
            });
        },
        error: function (xhr, status, error) {
            console.error("Error:", error);
            Swal.fire({
                icon: 'error',
                title: 'Login Failed',
                text: 'Invalid credentials or server error.'
            });
        }
    });
})