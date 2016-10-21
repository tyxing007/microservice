/**
 * Project service process request to project api.
 *
 * @author Sem Babenko
 */
app
    .factory('projectService', function ($q, serverService) {

        return{

            getProjects: function () {
                var deferred = $q.defer();
                serverService.getProjects()
                    .then(function (response) {
                        deferred.resolve(response)
                    });
                return deferred.promise;
            },

            saveProject: function(project){
                var deferred = $q.defer();
                serverService.saveProject(project).then(function(response){
                    deferred.resolve(response);
                });
                return deferred.promise;
            }
        }
    });