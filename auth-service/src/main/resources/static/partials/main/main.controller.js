ssta.controller('MainController',
    function ($scope, MainService) {

        $scope.user = {
            username: "",
            password: ""
        };

        $scope.initUsers = function() {
            MainService.initUsers();
        };

        $scope.login = function() {
            MainService.login($scope.user).then(function(promise){

            });
        };

        $scope.login2 = function() {
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
                        console.log("oKi");
                        console.log(data)
                    },
                    error: function () {
                        console.log("error");
                        // removeOauthTokenFromStorage();
                    }
                });


        };

        $scope.logout = function() {
            MainService.logout();
        }
    }
);