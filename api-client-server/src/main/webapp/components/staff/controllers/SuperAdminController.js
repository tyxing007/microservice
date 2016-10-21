/**
 * Super Admin Controller
 * This temporary controller it is intended to send a message to mail registration confirmation
 *
 * @author Andrii Blyznuk
 */
app
    .controller('superAdminController', function ($scope, userService, UserTypeEnum) {

        $scope.typeUser = UserTypeEnum;

        //The method that sends to the server email user and type.
        $scope.sendToEmail = function (email, type){
            return userService.sendToEmail(email, type).then(function (user) {
                $scope.successfullySend = "Message sent successfully to " + user.data['email'];
                return user.data;
            })
        }

    });
