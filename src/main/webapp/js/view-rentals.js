$(document).ready(function () {
    getData();
})

function getData() {
    console.log("getting rentals data")
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/final_war_exploded/test',
        data: {action: 'view_rentals'},
        success: function (data) {
            console.log("rentals data received")
            showUsers(data)
        },
        error: function () {
            alert("can't get rentals data");
        }
    })
}

function showUsers(data) {
    console.log(data);
    console.log("showing rentals");
    data = $.parseJSON(data)
    var content = "";
    $.each(data, function (i, item) {
            content = content + "<tr>";
            content = content + "<td>";
            content = content + item.id;
            content = content + "</td>";
            content = content + "<td>";
            content = content + item.rentalDate;
            content = content + "</td>";
            content = content + "<td>";
            content = content + item.returnDate;
            content = content + "</td>";
            content = content + "<td>";
            content = content + item.status;
            content = content + "</td>";
            content = content + "<td>";
            if (item.status === 'WAITING') {
                content = content + "<button onClick={acceptRental(" + item.id + ")}>Accept</button>"
                content = content + "<button onClick={declineRental(" + item.id + ")}>Decline</button>"
            }
            content = content + "</tr>";
            $('#rentals').html(content);
        }
    );
}

function acceptRental(rentalId) {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/final_war_exploded/test',
        data: {action: 'accept_rental', rentalId: rentalId},
        success: function () {
            getData();
        },
        error: function () {
            alert("can't accept rental");
        }
    })
}

function declineRental(rentalId) {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/final_war_exploded/test',
        data: {action: 'decline_rental', rentalId: rentalId},
        success: function () {
            getData();
        },
        error: function () {
            alert("can't decline rental");
        }
    })
}