ssta.factory('AdminService', ['ServerService', function (ServerService) {

    return {
        getUsers: function () {
            return ServerService.getUsers();
        }
    };

}]);