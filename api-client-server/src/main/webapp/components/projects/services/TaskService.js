/**
 * Project service process request to project api.
 *
 * @author Sem Babenko
 */
app
    .factory('taskService', function ($q, serverService) {

        var currentProject;

        return{

            getPriorities() {
                var deferred = $q.defer();
                serverService.getPriorities()
                    .then(function (response) {
                        deferred.resolve(response)
                    });
                return deferred.promise;
            },

            saveTask(task){
                var deferred = $q.defer();
                serverService.saveTask(task).then(function(response){
                    deferred.resolve(response);
                });
                return deferred.promise;
            },

            setCurrentProject(project){
                currentProject = project;
            },

            getProject(){
                return currentProject;
            }
        }
    });