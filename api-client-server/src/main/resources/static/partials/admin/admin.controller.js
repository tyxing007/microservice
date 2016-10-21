ssta.controller('AdminController',
    function ($scope, AdminService) {

        $scope.getUsers = function() {
            AdminService.getUsers().then(function(promise){
            console.log(promise.data);
                $scope.users = promise.data;
            });
        };
    }
);