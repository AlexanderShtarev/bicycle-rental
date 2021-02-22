$(document).ready(function () {
    getData();
})

function getData() {
    console.log("getting categories data")
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/final_war_exploded/test',
        data: {action: 'view_categories'},
        success: function (data) {
            console.log("categories data received")
            showCategories(data)
        },
        error: function () {
            alert("can't get categories data");
        }
    })
}

function showCategories(data) {
    console.log("showing categories")
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
                " onclick='deleteCategory(\"" + item.id + "\")'>Delete Category</button>"
            content = content + "</td>";
            content = content + "</tr>";
            $('#categories').html(content);
        }
    );
}

function deleteCategory(id) {
    console.log("removing category")
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/final_war_exploded/test',
        data: {action: 'delete_category', categoryId: id},
        success: function () {
            console.log("category removed")
            getData();
        },
        error: function () {
            alert("can't remove category");
        }
    })
}
