/**
 *Controller that is provided a users profile page
 *
 * @author Sergey Petrovsky
 */
app
    .controller('UserShowController', function ($scope, $stateParams, userService) {

        userService.getUser($stateParams.userID)
            .then(function (answer) {
                $scope.user = answer;
                console.log($scope.user);
            });
    });