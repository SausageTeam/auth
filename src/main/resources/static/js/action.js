$(document).ready(function () {
    var loginForm = $('form#login-form');
    loginForm.submit(function(e){
        e.preventDefault();

        const params = loginForm.serializeArray().reduce(function (obj, item) {
            obj[item.name] = item.value;
            return obj;
        }, {});

        var getUrlParameter = function getUrlParameter(sParam) {
            var sPageURL = window.location.search.substring(1),
                sURLVariables = sPageURL.split('&'),
                sParameterName,
                i;

            for (i = 0; i < sURLVariables.length; i++) {
                sParameterName = sURLVariables[i].split('=');

                if (sParameterName[0] === sParam) {
                    return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
                }
            }
        };

        params["redirectUrl"] = getUrlParameter("redirect");

        $.ajax({
            url: '/auth/login',
            data: params,
            dataType: 'json',
            type: 'post',
            success: function(res) {
				// console.log(res);
                if(!res.status.success) {
                    const error = $('#error');
                    error.text(res.status.errorMessage);
                    error.show();
                } else {
                    window.location = res.redirectUrl;
                }
            }
        });

    });

    var tokenForm = $('form#token-form');
    tokenForm.submit(function(e){
        e.preventDefault();

        const params = tokenForm.serializeArray().reduce(function (obj, item) {
            obj[item.name] = item.value;
            return obj;
        }, {});

        var getUrlParameter = function getUrlParameter(sParam) {
            var sPageURL = window.location.search.substring(1),
                sURLVariables = sPageURL.split('&'),
                sParameterName,
                i;

            for (i = 0; i < sURLVariables.length; i++) {
                sParameterName = sURLVariables[i].split('=');

                if (sParameterName[0] === sParam) {
                    return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
                }
            }
        };

        console.log(getUrlParameter("redirect"));
        params["redirectUrl"] = getUrlParameter("redirect");

        $.ajax({
            url: '/auth/token',
            data: params,
            dataType: 'json',
            type: 'post',
            success: function(res) {
                // console.log(res);
                if(!res.status.success) {
                    const error = $('#error');
                    error.text(res.status.errorMessage);
                    error.show();
                } else {
                    window.location = res.redirectUrl;
                }
            }
        });

    });
});