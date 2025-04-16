$('#addVehicle').on('click', function() {
    window.location.href = './VehicleRegistration.html';
});

$(document).ready(function () {

    let loggedUserID = localStorage.getItem("LoggedUserId");
    console.log("loggedUserID", loggedUserID);

    $.ajax({
        url: `http://localhost:8080/api/v1/driver/getPersonalData/${loggedUserID}`,
        method: "GET",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("authToken")
        },
        success: function (data) {
            console.log("Driver data:", data);

            const driver = data[0];
            localStorage.setItem("loggedDriverID", driver.did);
            const loggedDriverid = driver.did;

            $('#registerVehicle').on('click', function () {

                const vehicleType = $('#vehicleType').val();
                const vehicleModel = $('#vehicleModel').val();
                const vehicleYear = $('#vehicleYear').val();
                const vehicleColor = $('#vehicleColor').val();
                const licencePlate = $('#licensePlate').val();
                const insuranceExpiry = $('#insuranceExpiry').val();

                // Check for empty fields
                if (!vehicleType || !vehicleModel || !vehicleYear || !vehicleColor || !licencePlate || !insuranceExpiry) {
                    Swal.fire({
                        icon: 'warning',
                        title: 'Missing Info',
                        text: 'Please fill in all fields before submitting.',
                    });
                    return;
                }

                // Show loading SweetAlert
                Swal.fire({
                    title: 'Registering Vehicle...',
                    text: 'Please wait',
                    allowOutsideClick: false,
                    didOpen: () => {
                        Swal.showLoading();
                    }
                });

                // Submit via AJAX
                $.ajax({
                    url: "http://localhost:8080/api/v1/vehicle/save",
                    method: "POST",
                    contentType: "application/json; charset=utf-8",
                    dataType: "json",
                    data: JSON.stringify({
                        vehicleType: vehicleType,
                        vehicleModel: vehicleModel,
                        vehicleYear: vehicleYear,
                        vehicleColor: vehicleColor,
                        licensePlate: licencePlate,
                        insuranceExpiry: insuranceExpiry,
                        driver: {
                            did: loggedDriverid
                        }
                    }),
                    headers: {
                        "Authorization": "Bearer " + localStorage.getItem("authToken"),
                    },
                    success: function (data) {
                        Swal.fire({
                            icon: 'success',
                            title: 'Vehicle Registered!',
                            text: 'Your vehicle has been saved successfully!',
                            timer: 3000,
                            showConfirmButton: false
                        });

                        // Optional: Clear form
                        $('#vehicleType').val('');
                        $('#vehicleModel').val('');
                        $('#vehicleYear').val('');
                        $('#vehicleColor').val('');
                        $('#licensePlate').val('');
                        $('#insuranceExpiry').val('');
                    },
                    error: function (xhr, status, error) {
                        console.log("Error registering vehicle:", error);
                        Swal.fire({
                            icon: 'error',
                            title: 'Registration Failed',
                            text: 'Something went wrong while registering the vehicle.',
                        });
                    }
                });

            });
        },
        error: function (xhr, status, error) {
            console.log(error);
            Swal.fire({
                icon: 'error',
                title: 'Error!',
                text: 'Failed to fetch driver details. Please check your login.',
            });
        }
    });

});
