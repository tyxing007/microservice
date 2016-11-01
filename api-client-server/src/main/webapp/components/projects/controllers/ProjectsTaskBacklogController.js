/**
 * Projects task Controller
 *
 * @author Kulinenko Roman, Sem Babenko
 */
app.controller('ProjectsTaskBacklogController', function ($scope, serverService, taskService) {

    taskService.getTasks().then(function (answer) {
        $scope.tasks = answer;
        console.log(answer);
    })

});