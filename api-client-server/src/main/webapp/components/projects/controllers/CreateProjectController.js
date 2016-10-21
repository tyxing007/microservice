/**
 * Create Project Controller
 * @author Kulinenko Roman, Sem Babenko
 */
app.controller('CreateProjectController', function ($scope, serverService, projectService, $mdDialog, moment, formats, $rootScope) {

    $scope.project = {
        startDate: moment().format(formats.date)
    };

    $scope.upload = function () {
        angular.element(document.querySelector('#fileInput')).click();
    };

    $scope.hide = function() {
        $mdDialog.hide();
    };
    $scope.cancel = function() {
        $mdDialog.cancel();
    };
    $scope.answer = function(answer) {
        $mdDialog.hide(answer);
        $scope.project.dueDate = moment($scope.dueDate).format(formats.date);
        return projectService.saveProject($scope.project).then(function(response){
            $rootScope.$broadcast("append-project", response);
            return response;
        });
    };

});