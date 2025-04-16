$('.hero-buttons a').on('click', function (e) {
    e.preventDefault();
    selectedRole = $(this).data('role');
    console.log("Selected Role:", selectedRole);
});

// Registration form submit with SweetAlert
$('#regbtn').on('click', function (e) {
    e.preventDefault();

    const email = $('#email').val();
    const password = $('#password').val();
    const confirmPassword = $('#confirmPassword').val();
    const phone = $('#phone').val();

        if (password !== confirmPassword) {
            Swal.fire({
                icon: 'warning',
                title: 'Oops...',
                text: 'Passwords do not match!'
            });
            return;
        }

        const userData = {
            email: email,
            password: password,
            phone: phone,
            role: selectedRole
        };

        $.ajax({
            url: "http://localhost:8080/api/v1/user/register",
            method: "POST",
            data: JSON.stringify(userData),
            contentType: "application/json",
            success: function () {
                Swal.fire({
                    icon: 'success',
                    title: 'Registered Successfully!',
                    text: 'Redirecting to login...',
                    timer: 2000,
                    showConfirmButton: false
                }).then(() => {
                    window.location.href = "LoginForm.html";
                });
            },
            error: function (xhr) {
                const errorMessage = xhr.responseJSON?.message || "Registration failed.";
                Swal.fire({
                    icon: 'error',
                    title: 'Registration Failed',
                    text: errorMessage
                });

            }

        });
});

