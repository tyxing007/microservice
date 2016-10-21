/**
 * Department Controller
 *
 * @author Dmitry Sheremet
 */
app
    .controller('DepartmentController', function ($scope, serverService) {
        
        serverService.getDepartments()
            .then(function (answer) {
                $scope.departments = answer;
            })
    });