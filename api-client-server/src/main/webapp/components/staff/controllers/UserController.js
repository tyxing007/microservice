/**
 * User Controller
 *
 * @author
 */
app
    .controller('UserController', function ($scope, userService) {

        userService.getUsers()
            .then(function (answer) {
                $scope.users = answer;
            });

    });