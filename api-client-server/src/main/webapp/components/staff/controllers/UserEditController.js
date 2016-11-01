/**
 * User Edit Controller
 * Controller for editing user
 *
 * @author Andrii Blyznuk
 */
app
    .controller('UserEditController', function ($scope, userService, autocompleteService, $stateParams, $state) {

        $scope.getAutocompleteData = autocompleteService.getAutocompleteDataFromServer;


        $scope.addKit = function(name) {
            var hasSameSkill = false;
            angular.forEach($scope.user.skills, function (skill) {
                if(skill.name == name) hasSameSkill = true;
            });
            return hasSameSkill ? null : {name: name};
        };

        userService.getUser($stateParams.userId)
            .then(function (answer) {
                $scope.user = answer;
                console.log($scope.user);
            });
        
        $scope.deleteImage = function () {
          $scope.file = null;
            userService.deleteImage($scope.user.id);
        };

        $scope.saveUser = function (file) {
            userService.createUser($scope.user)
                .then(function () {
                    userService.uploadImage(file, $scope.user.id);
                    $state.go('app.main');
                });
        };

    });