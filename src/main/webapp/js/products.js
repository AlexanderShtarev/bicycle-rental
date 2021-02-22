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

$("button[name='cart_add_button']").click(function () {
    var productId = $(this).attr("productId");
    addProduct(productId);
});
