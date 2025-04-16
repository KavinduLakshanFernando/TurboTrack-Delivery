$(document).ready(function () {
    let loggedUserID = localStorage.getItem("LoggedUserId");
    let token = localStorage.getItem("authToken");

    $.ajax({
        url: `http://localhost:8080/api/v1/driver/getPersonalData/${loggedUserID}`,
        method: "GET",
        headers: {
            "Authorization": "Bearer " + token
        },
        success: (res) => {
            console.log("res",res)
            console.log("res2",res.data)

            res.forEach(driver =>{
                $("#firstName").val(driver.firstName);
                $("#lastName").val(driver.lastName);
                $("#email").val(driver.user?.email || '');
                $("#phone").val(driver.user?.phone || '');
                $("#streetAddress").val(driver.streetAddress);
                $("#city").val(driver.city);

            })



        },
        error: function (xhr, status, error) {
            console.log(error);
        }
    })
})