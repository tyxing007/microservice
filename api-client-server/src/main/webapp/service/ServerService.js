/**
 * Server Service
 * Service that holds all possible server requests
 *
 * @author Dmitry Sheremet, Andrii Blyznuk, Sem Babenko
 */
app
    .factory('serverService', function ($http, $q, Upload, $rootScope) {

        String.prototype.format = String.prototype.formatf = function(){
            var args = arguments;
            return this.replace(/\{(\d+)\}/g, function(m, n){
                return args[n] ? args[n] : m;
            });
        };

        var server = {
            //User section
            usersGET: '/api/users',
            userGET: '/api/user/{0}',
            userCreatePOST: '/api/user/create',
            sentTokenToEmailPOST: '/api/token/send',
            checkUserByToken: '/api/user/{0}}/check/{1}',
            bindUserWithDepartmentGET: '/api/user/{0}/bind/{1}',
			sentToEmailTokenPOST: '/api/sentToEmailToken',

            //Department section
            departmentsGET: '/api/departments',
            departmentGET: '/api/department/{0}',
            departmentCreatePOST: '/api/department/create',
            bindDepartmentWithUser: '/api/department/{0}/bind/{1}',

            //Image section
            uploadImagePOST: '/api/image/upload/user/',
            deleteImageDELETE: '/api/image/delete/user/',

            //Vacancy section
            vacanciesGET: '/api/vacancies',
			createVacancyPOST: '/api/vacancy/create',

            //Task section
            tasksGET: '/api/task',
            taskPOST: '/api/task',
            taskPUT: '/api/task',
            taskByIdGET: '/api/task/{0}',
            taskDELETE: '/api/task/{0}',

            statusesGET: '/api/task/status',
            statusPOST: '/api/task/status/{0}',

            labelsGET: '/api/task/label',
            labelPOST: '/api/task/label/{0}',

            prioritiesGET: '/api/task/priority',
            priorityPOST: '/api/task/priority/{0}',

            //Project section
            projectsGET: "/api/project",
            projectPOST: "/api/project",
            projectByIdGET: "/api/project/{0}",

            //Bridging section
            skillsGET: '/api/skills',
            autocompleteDataFromServerGET: '/api/autocomplete/'
        };

        $rootScope.autocomplete = {
            departmentNames: []
        };

        return {

            getUsers: function() {
                var deferred = $q.defer();
                $http.get(server.usersGET).then(function (response) {
                    deferred.resolve(response.data)
                });
                return deferred.promise;
            },

            getUser: function (userId) {
                var deferred = $q.defer();
                $http.get(server.userGET.formatf(userId))
                    .then(function (response) {
                        deferred.resolve(response.data);
                    });
                return deferred.promise;
            },

            uploadImage: function (file, userId) {
                if (file != null) {
                    Upload.upload({
                        url: server.uploadImagePOST + userId,
                        data: {
                            file: file
                        }
                    });
                }
            },

            deleteImage: function (userId) {
                $http.delete(server.deleteImageDELETE + userId)
            },

            createUser: function (user) {
                return $http.post(server.userCreatePOST, user);
            },

            sendToEmail: function (email, type, candidate) {
                if(!candidate){
                    return $http.post(server.sentToEmailTokenPOST, {email: email, type:type});
                } else {
                    return $http.post(server.sentToEmailTokenPOST, {email: email, type:type});
                }
            },

            getAutocompleteDataFromServer: function(itemName) {
               return $http.get(server.autocompleteDataFromServerGET + itemName);
            },

            getVacancies: function () {
                var deferred = $q.defer();
                $http.get(server.vacanciesGET)
                    .then(function (response) {
                        deferred.resolve(response.data)
                    });
                return deferred.promise;
            },

            getProjects: function(){
                var deferred = $q.defer();
                $http.get(server.projectsGET).then(function(response){
                    deferred.resolve(response.data);
                });
                return deferred.promise;
            },

            saveProject: function(project){
                var deferred = $q.defer();
                $http.post(server.projectPOST, project).then(function(response){
                    deferred.resolve(response.data);
                });
                return deferred.promise;
            },

            getProjectById: function(id){
                var deferred = $q.defer();
                $http.get(server.projectByIdGET.formatf(id)).then(function(response){
                    deferred.resolve(response.data);
                });
                return deferred.promise;
            },

			saveVacancy: function (vacancy) {
                return $http.post(server.createVacancyPOST, vacancy);
            },

            saveTask: function(task){
                var deferred = $q.defer();
                $http.post(server.taskPOST, task).then(function(response){
                    deferred.resolve(response.data);
                });
                return deferred.promise;
            },


            getTasks: function() {
                var deferred = $q.defer();
                $http.get(server.tasksGET).then(function (response) {
                    deferred.resolve(response.data)
                });
                return deferred.promise;
            },

            getPriorities: function(){
                var deferred = $q.defer();
                $http.get(server.prioritiesGET).then(function(response){
                    deferred.resolve(response.data);
                });
                return deferred.promise;
            }
    }
    });
