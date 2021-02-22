$(document).ready(function () {

    $("button[name='cart_add_button']").click(function () {
        console.log("cart add button action received");
        var productId = $(this).attr("productId");
        addProduct(productId);
    });

    $("button[name='cart_increment_button']").click(function () {
        console.log("cart increment button action received");
        var productId = $(this).attr("productId");
        addProduct(productId);
    });

    $("button[name='cart_decrement_button']").click(function () {
        console.log("cart decrement button action received");
        var productId = $(this).attr("productId");
        deleteProduct(productId);
    });

    $("button[name='cart_delete_button']").click(function () {
        console.log("cart delete button action received");
        var productId = $(this).attr("productId");
        deleteAllProducts(productId);
    });

    function getCart() {
        console.log("Getting cart info")
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/final_war_exploded/test',
            data: {action: 'view_cart'},
            success: function (answer) {
                console.log("cart info received");
                setCartHeader(answer);
            },
            error: function () {
                alert("can't get cart info")
            }
        })
    }

    function addProduct(productId) {
        $.ajax({
            type: "POST",
            url: 'http://localhost:8080/final_war_exploded/test',
            data: {
                productId: productId,
                action: 'add_product_to_cart'
            },
            success: function (answer) {
                console.log("product added to cart");
                setProductCount(answer, productId);
                setCartHeader(answer);
            },
            error: function () {
                alert("can't add product to cart");
            }
        });
    }

    function deleteProduct(productId) {
        $.ajax({
            type: "POST",
            url: 'http://localhost:8080/final_war_exploded/test',
            data: {
                productId: productId,
                action: 'remove_product_from_cart'
            },
            success: function (answer) {
                console.log("product removed from cart");
                setProductCount(answer, productId);
                setCartHeader(answer);
            },
            error: function () {
                alert("can't remove product from cart");
            }
        });
    }

    function deleteAllProducts(productId) {
        $.ajax({
            type: "POST",
            url: 'http://localhost:8080/final_war_exploded/test',
            data: {
                productId: productId,
                action: 'remove_all_products_from_cart'
            },
            success: function (answer) {
                console.log("all same products removed from cart");
                if (answer.size == 0) {
                    window.location.href = 'http://localhost:8080/final_war_exploded/controller?command=to_cart_page';
                } else {
                    setCartHeader(answer);
                    setProductCount(answer, productId);
                }
            },
            error: function () {
                alert("can't remove all products from cart");
            }
        });
    }

    function setCartHeader(answer) {
        console.log("setting cart header");
        $("#cart_size").html(answer.size);
        $('#total_price').html(answer.totalPrice);
    }

    function setProductCount(answer, productId) {
        console.log("setting product cart info");
        var tableRow = document.getElementById("product_" + productId);
        var productCount = document.getElementById("product_count_" + productId);
        var productCost = document.getElementById("product_cost_" + productId);
        if (answer.productCount === 0) {
            tableRow.remove();
        } else {
            productCost.innerHTML = (answer.productCost);
            productCount.innerHTML = (answer.productCount);
        }
    }

    getCart();
})

