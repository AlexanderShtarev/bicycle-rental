$(document).ready(function () {
    getData();
})

function getData() {
    console.log("getting users data")
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/final_war_exploded/test',
        data: {action: 'view_users'},
        success: function (data) {
            console.log("users data received")
            showUsers(data)
        },
        error: function () {
            alert("can't get users data");
        }
    })
}

function showUsers(data) {
    console.log(data);
    console.log("showing users");
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
            content = content + item.surname;
            content = content + "</td>";
            content = content + "<td>";
            content = content + item.email;
            content = content + "</td>";
            content = content + "<td>";
            content = content + item.balance;
            content = content + "</td>";
            content = content + "<td>";
            content = content + item.status;
            content = content + "</td>";
            content = content + "<td>";
            if (item.status === 'BLOCKED') {
                content = content + "<button onClick={blockUser(" + item.id + ")}>UNBLOCK</button>"
            } else {
                content = content + "<button onClick={blockUser(" + item.id + ")}>BLOCK</button>"
            }
            content = content + "</tr>";
            $('#users').html(content);
        }
    );
}

function blockUser(userId) {
    console.log("blocking user, id=", userId)
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/final_war_exploded/test',
        data: {action: 'change_user_status', userId: userId},
        success: function () {
            console.log("user with id=", userId, " blocked")
            getData();
        },
        error: function () {
            alert("can't block user");
        }
    })
}
