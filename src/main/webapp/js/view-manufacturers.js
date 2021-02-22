$(document).ready(function () {
    getManufacturers();
})

function getManufacturers() {
    console.log("getting manufacturers data")
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/final_war_exploded/test',
        data: {action: 'view_manufacturers'},
        success: function (data) {
            console.log("manufacturers data received");
            showManufacturers(data)
        },
        error: function () {
            alert("can't get manufacturers data");
        }
    })
}

function showManufacturers(data) {
    console.log("showing manufacturers")
    data = $.parseJSON(data)
    var content = "";
    $.each(data, function (i, item) {
            content = content + "<tr>";
            content = content + "<td>";
            content = content + item.id;
            content = content + "</td>";
            content = content + "<td>";
            content = content + item.name;
            content = content + "</td>";
            content = content + "<td>";
            content = content + "<button id='deleteCategory' class='btn btn-primary btn-xs'" +
                " onclick='deleteManufacturer(\"" + item.id + "\")'>Delete Manufacturer</button>"
            content = content + "</td>";
            content = content + "</tr>";
            $('#manufacturers').html(content);
        }
    );
}

function deleteManufacturer(id) {
    console.log("removing manufacturer")
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/final_war_exploded/test',
        data: {action: 'delete_manufacturer', manufacturerId: id},
        success: function () {
            console.log("manufacturer removed")
            getManufacturers();
        },
        error: function () {
            alert("can't remove manufacturer data");
        }
    })
}
