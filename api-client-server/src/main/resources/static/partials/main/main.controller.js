ssta.controller('MainController',
    function ($scope, MainService) {

        $scope.user = {
            username: "",
            password: ""
        };

        $scope.token = "null";

        $scope.login = function() {
                    $.ajax({
                        url: '/uaa/oauth/token',
                        datatype: 'json',
                        type: 'post',
                        headers: {'Authorization': 'Basic YnJvd3Nlcjo='},
                        async: false,
                        data: {
                            scope: 'ui',
                            username: $scope.user.username,
                            password: $scope.user.password,
                            grant_type: 'password'
                        },
                        success: function (data) {
                            localStorage.setItem('token', data.access_token);
                            $scope.token = data;
                            console.log("----token----");
                            console.log(data)
                        },
                        error: function () {
                            console.log("error")
                        }
                    });

        };

        function getOauthTokenFromStorage() {
            return localStorage.getItem('token');
        }


        function removeOauthTokenFromStorage() {
            return localStorage.removeItem('token');
        }

        $scope.getPrincipal = function() {

            var token = getOauthTokenFromStorage();


                $.ajax({
                    url: '/uaa/api/principal',
                    datatype: 'json',
                    type: 'get',
                    headers: {'Authorization': 'Bearer ' + token},
                    success: function (data) {
                        console.log("-------uaa------");
                        console.log(data)
                    },
                    error: function () {
                        console.log("error");
                    }
                });


        };

        $scope.getPrincipal2 = function() {

            var token = getOauthTokenFromStorage();


            $.ajax({
                url: '/tachometer/api/principal',
                datatype: 'json',
                type: 'get',
                headers: {'Authorization': 'Bearer ' + token},
                success: function (data) {
                    console.log("--------tachometer-------");
                    console.log(data)
                },
                error: function () {
                    console.log("error");
                }
            });


        };

        $scope.getPrincipal3 = function() {

            var token = getOauthTokenFromStorage();


            $.ajax({
                url: '/uaa/api/tach/test',
                datatype: 'json',
                type: 'get',
                headers: {'Authorization': 'Bearer ' + token},
                success: function (data) {
                    console.log("--------uaa through tachometer-------");
                    console.log(data)
                },
                error: function () {
                    console.log("error");
                }
            });


        };

        $scope.logout = function() {
            removeOauthTokenFromStorage();
        }
    }
);