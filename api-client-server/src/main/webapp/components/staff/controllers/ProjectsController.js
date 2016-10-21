/**
 * Project Controller
 *
 * @author Kulinenko Roman, Sem Babenko
 */
app.controller('ProjectsController', function ($scope, serverService, projectService, taskService, $mdDialog, $mdMedia, $rootScope) {
    var originatorEv;

    $scope.projectLabel = "Select project";

    projectService.getProjects().then(function(response){
        $scope.projects = response;
    });

    $rootScope.$on("append-project", function(event, project){
        $scope.projects.push(project);
    });

    $scope.selectProject = function($event, project){
        $scope.currentProject = project;
        $scope.projectLabel = project.name;
    };

    $scope.openMenu = function($mdOpenMenu, ev) {
        originatorEv = ev;
        $mdOpenMenu(ev);
    };

    $scope.names = ('Booking Twaddle Perry').split(' ').map(function(name) {
        return {projects: name};
    });

    $scope.showCreteProject = function(ev) {
        $mdDialog.show({
           controller: 'CreateProjectController as createProject',
           templateUrl: 'template/modal.create.project.html',
           parent: angular.element(document.body),
           targetEvent: ev,
           preserveScope: true,
           clickOutsideToClose:true
       }).then(function(answer) {
            $scope.status = 'You said the information was "' + answer + '".';
        }, function() {
            $scope.status = 'You cancelled the dialog.';
        });
    };

    $scope.showCreteTask = function(ev) {
        if($scope.currentProject){
            taskService.setCurrentProject($scope.currentProject);
            $mdDialog.show({
               controller: 'CreateTaskController as createTask',
               templateUrl: 'template/modal.create.task.html',
               parent: angular.element(document.body),
               targetEvent: ev,
               clickOutsideToClose: true
           }).then(function(answer) {
                $scope.status = 'You said the information was "' + answer + '".';
            }, function() {
                $scope.status = 'You cancelled the dialog.';
            });
        }else{
            //TODO instead alert open dialog with warning message
            alert("Choose project!");
        }
    };

    $scope.showEditTask = function(ev) {
        $mdDialog.show({
           controller: 'EditTaskController as editTask',
           templateUrl: 'template/modal.edit.task.html',
           parent: angular.element(document.body),
           targetEvent: ev,
           clickOutsideToClose:true
       }).then(function(answer) {
            $scope.status = 'You said the information was "' + answer + '".';
        }, function() {
            $scope.status = 'You cancelled the dialog.';
        });
    };

    $scope.showMoreDetail = function(ev) {
        $mdDialog.show({
           controller: 'MoreDetailController as moreDetail',
           templateUrl: 'template/modal.more.detail.html',
           parent: angular.element(document.body),
           targetEvent: ev,
           clickOutsideToClose:true
        }).then(function(answer) {
            $scope.status = 'You said the information was "' + answer + '".';
        }, function() {
            $scope.status = 'You cancelled the dialog.';
        });
    };
});

