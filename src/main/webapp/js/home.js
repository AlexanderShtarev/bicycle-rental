$(document).ready(function () {
    getData();
})

function getData() {
    console.log("getting home data")
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/final_war_exploded/test',
        data: {action: 'view_home'},
        success: function (data) {
            console.log("home data received")
            getAdditionalData();
            showProducts(data)
        },
        error: function () {
            alert("can't get home data");
        }
    })
}

function addProduct(productId) {
    console.log("Action add product received")
    $.ajax({
        type: "POST",
        url: 'http://localhost:8080/final_war_exploded/test',
        data: {
            productId: productId,
            action: 'add_product_to_cart'
        },
        success: function () {
            console.log("product : id = ", productId, " added to cart")
            $("#getCodeModal").modal('show');
        },
        error: function () {
            alert("can't add product to cart");
        }
    });
}

function showProducts(data) {
    console.log('showing products')
    data = $.parseJSON(data)
    var content = "";
    $.each(data, function (i, item) {
        content = content + "<tr>";
        content = content + showRow(item.name);
        content = content + showRow(item.manufacturer);
        content = content + showRow(item.category);
        content = content + showRow(item.price);
        content = content + "<td>";
        content = content + "<button onclick='addProduct(" + item.id + ")'>Add to cart</button>";
        content = content + "</td>";
        content = content + "</tr>";
        $('#products').html(content);
    })

}

function showRow(value) {
    return "<td>" + value + "</td>";
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
