/**
 * Project Controller
 *
 * @author Kulinenko Roman, Sem Babenko
 */
app.controller('ProjectsController', function ($scope, serverService, projectService, taskService) {
    var originatorEv;

    $scope.projectLabel = "Select project";

    projectService.getProjects().then(function(response){
        $scope.projects = response;
    });

    $scope.$on("append-project", function(event, project){
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

    $scope.names = ('Booking, Twaddle, Perry, + Create new project').split(', ').map(function(name) {
        return {projects: name};
    });

    $scope.dropProject = function(cl) {
        if (cl.$index === ($scope.names.length - 1)) {
            $scope.showCreteProject();
        }
    };

    $scope.showCreteProject = function(ev) {
       //  $mdDialog.show({
       //     controller: 'CreateProjectController as createProject',
       //     templateUrl: 'template/modal.create.project.html',
       //     parent: angular.element(document.body),
       //     targetEvent: ev,
       //     preserveScope: true,
       //     clickOutsideToClose:true
       // }).then(function(answer) {
       //      $scope.status = 'You said the information was "' + answer + '".';
       //  }, function() {
       //      $scope.status = 'You cancelled the dialog.';
       //  });
    };

    $scope.showCreteTask = function(ev) {
        if($scope.currentProject){
            taskService.setCurrentProject($scope.currentProject);
           //  $mdDialog.show({
           //     controller: 'CreateTaskController as createTask',
           //     templateUrl: 'template/modal.create.task.html',
           //     parent: angular.element(document.body),
           //     targetEvent: ev,
           //     clickOutsideToClose: true
           // }).then(function(answer) {
           //      $scope.status = 'You said the information was "' + answer + '".';
           //  }, function() {
           //      $scope.status = 'You cancelled the dialog.';
           //  });
        }else{
            //TODO instead alert open dialog with warning message
            alert("Choose project!");
        }
    };

    $scope.showEditTask = function(ev) {
       //  $mdDialog.show({
       //     controller: 'EditTaskController as editTask',
       //     templateUrl: 'template/modal.edit.task.html',
       //     parent: angular.element(document.body),
       //     targetEvent: ev,
       //     clickOutsideToClose:true
       // }).then(function(answer) {
       //      $scope.status = 'You said the information was "' + answer + '".';
       //  }, function() {
       //      $scope.status = 'You cancelled the dialog.';
       //  });
    };

    $scope.showMoreDetail = function(ev) {
        // $mdDialog.show({
        //    controller: 'MoreDetailController as moreDetail',
        //    templateUrl: 'template/modal.more.detail.html',
        //    parent: angular.element(document.body),
        //    targetEvent: ev,
        //    clickOutsideToClose:true
        // }).then(function(answer) {
        //     $scope.status = 'You said the information was "' + answer + '".';
        // }, function() {
        //     $scope.status = 'You cancelled the dialog.';
        // });
    };
});
app.controller('RatingDemoCtrl', function ($scope) {
    $scope.rate = 7;
    $scope.max = 10;
    $scope.isReadonly = false;

    $scope.hoveringOver = function(value) {
        $scope.overStar = value;
        $scope.percent = 100 * (value / $scope.max);
    };

    $scope.ratingStates = [
        {stateOn: 'glyphicon-ok-sign', stateOff: 'glyphicon-ok-circle'},
        {stateOn: 'glyphicon-star', stateOff: 'glyphicon-star-empty'},
        {stateOn: 'glyphicon-heart', stateOff: 'glyphicon-ban-circle'},
        {stateOn: 'glyphicon-heart'},
        {stateOff: 'glyphicon-off'}
    ];
});

app.controller('TabsDemoCtrl', function ($scope, $window) {
    $scope.tabs = [
        { title:'Dynamic Title 1', content:'Dynamic content 1' },
        { title:'Dynamic Title 2', content:'Dynamic content 2'}
    ];

    $scope.alertMe = function() {
        setTimeout(function() {
            $window.alert('You\'ve selected the alert tab!');
        });
    };

    $scope.model = {
        name: 'Tabs'
    };
});

app.controller('PaginationCtrl', function ($scope, $log) {
    $scope.totalItems = 26;
    $scope.currentPage = 1;
});