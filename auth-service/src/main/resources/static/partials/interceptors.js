ssta.factory('HttpRequestInterceptor', function () {

    return {
        request: function (config) {
            if (localStorage.getItem("JJWT") != null) {
                config.headers['Authorization'] = localStorage.getItem("JJWT");
            }

            return config;
        }
    };
});