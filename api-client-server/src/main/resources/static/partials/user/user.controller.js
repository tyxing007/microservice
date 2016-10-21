ssta.controller('UserController',
    function ($scope, UserService) {

        $scope.getUsers = function() {
            UserService.getUsers().then(function(promise){
            console.log(promise.data);
                $scope.users = promise.data;
            });
        };
    }
);