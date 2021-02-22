$(document).ready(function () {
    getData();
})

function getData() {
    console.log("getting product data")
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/final_war_exploded/test',
        data: {action: 'view_products'},
        success: function (data) {
            console.log("product data received");
            showProducts(data)
        },
        error: function () {
            alert("can't get products data");
        }
    })
}

function showProducts(data) {
    console.log("showing products")
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
            content = content + item.manufacturer;
            content = content + "</td>";
            content = content + "<td>";
            content = content + item.category;
            content = content + "</td>";
            content = content + "<td>";
            content = content + item.price;
            content = content + "</td>";
            content = content + "<td>";
            content = content + "<a href='http://localhost:8080/final_war_exploded/controller?" +
                "command=to_update_product_page&productId=" + item.id + "'>Update Product</a>"
            content = content + "<button id='deleteProduct' class='btn btn-primary btn-xs'" +
                " onclick='deleteProduct(\"" + item.id + "\")'>Delete Product</button>"
            content = content + "</td>";
            content = content + "</tr>";
            $('#products').html(content);
        }
    );
}

function getAdditionalData() {
    console.log("getting additional data")
    getAllCategories();
    getAllManufacturers();

    function getAllManufacturers() {
        console.log("getting manufacturers")
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/final_war_exploded/test',
            data: {action: 'view_manufacturers'},
            success: function (data) {
                console.log("manufacturers received")
                showManufacturers(data)
            },
            error: function () {
                alert("can't get manufacturers data");
            }
        })
    }

    function getAllCategories() {
        console.log("getting categories")
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/final_war_exploded/test',
            data: {action: 'view_categories'},
            success: function (data) {
                console.log("categories received")
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
        $.each(data, function (i, item) {
                $('#categories').append("<option value='" + item.id + "'>" + item.name + "</option>");
            }
        );
    }

    function showManufacturers(data) {
        console.log("showing manufacturers")
        data = $.parseJSON(data)
        $.each(data, function (i, item) {
                $('#manufacturers').append("<option value='" + item.id + "'>" + item.name + "</option>");
            }
        );
    }

}

function deleteProduct(id) {
    console.log("removing product")
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/final_war_exploded/test',
        data: {action: 'delete_product', productId: id},
        success: function () {
            console.log("product removed")
            getData();
        },
        error: function () {
            alert("can't remove product");
        }
    })
}
