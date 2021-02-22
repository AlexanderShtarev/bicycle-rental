$(function () {
    $(document).ajaxError(function (e, xhr, settings) {

        if (xhr.status === 400) {
            console.log("ajaxError, code: 400");
            location = 'http://localhost:8080/final_war_exploded/controller?command=to_home_page';
        }
        if (xhr.status === 401) {
            console.log("ajaxError, code: 401");
            location = 'http://localhost:8080/final_war_exploded/controller?command=to_login_page';
        }
        if (xhr.status === 403) {
            console.log("ajaxError, code: 403");
            location = 'http://localhost:8080/final_war_exploded/jsp/error403.jsp';
        }
        if (xhr.status === 404) {
            console.log("ajaxError, code: 404");
            location = 'http://localhost:8080/final_war_exploded/jsp/error404.jsp';
        }
        if (xhr.status === 500) {
            console.log("ajaxError, code: 500");
            location = 'http://localhost:8080/final_war_exploded/jsp/error500.jsp';
        }

    });
});

