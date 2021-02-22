$(document).ready(function () {
    getRentals();

    $("#myBtn").click(function () {
        $("#myModal").modal();
    });

})

function myFunction() {
    var x = document.getElementById("myDIV");
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}

function getRentals() {
    $.ajax({
        type: "POST",
        url: 'http://localhost:8080/final_war_exploded/test',
        data: {
            action: 'view_user_rentals'
        },
        success: function (answer) {
            console.log(answer)
            console.log("user rentals received")
            showRentals(answer)
        },
        error: function () {
            alert("can't get user rentals");
        }
    });

    function showRentals(answer) {
        console.log('showing rentals')
        answer = $.parseJSON(answer)
        var content = "";
        $.each(answer, function (i, item) {
            content = content + "<tr>";
            content = content + showRow(item.rentalDate);
            content = content + showRow(item.returnDate);
            content = content + showRow(item.status);
            content = content + "</tr>";
            $('#products').html(content);
        })

        function showRow(value) {
            return "<td>" + value + "</td>";
        }
    }

}

