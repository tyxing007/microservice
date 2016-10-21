/**
 * Authorization Controller
 * 
 * @author
 */
app
    .controller('AuthorizationController', function ($scope, authorizationService) {

        $scope.user = {
            username: "",
            password: ""
        };


        $scope.login = function() {
            authorizationService.login($scope.user).then(function(promise){
            });
        };

        $scope.logout = function() {
            authorizationService.logout();
        }

    });

