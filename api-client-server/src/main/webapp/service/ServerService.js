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
            usersGET: '/erp/api/users',
            userGET: '/erp/api/user/{0}',
            userCreatePOST: '/erp/api/user/create',
            sentTokenToEmailPOST: '/erp/api/token/send',
            checkUserByToken: '/erp/api/user/{0}/check/{1}',
            bindUserWithDepartmentGET: '/erp/api/user/{0}/bind/{1}',
			sentToEmailTokenPOST: '/erp/api/token/send',
            authPOST: '/uaa/api/auth',

            //Department section
            departmentsGET: '/erp/api/departments',
            departmentGET: '/erp/api/department/{0}',
            departmentCreatePOST: '/erp/api/department/create',
            bindDepartmentWithUser: '/erp/api/department/{0}/bind/{1}',

            //Image section
            uploadImagePOST: '/erp/api/image/upload/user/',
            deleteImageDELETE: '/erp/api/image/delete/user/',

            //Vacancy section
            vacanciesGET: '/erp/api/vacancies',
			createVacancyPOST: '/erp/api/vacancy/create',

            //Step section
            stepPOST: '/erp/api/step',

            //Task section
            tasksGET: '/erp/api/task',
            taskPOST: '/erp/api/task',
            taskPUT: '/erp/api/task',
            taskByIdGET: '/erp/api/task/{0}',
            taskDELETE: '/erp/api/task/{0}',

            statusesGET: '/erp/api/task/status',
            statusPOST: '/erp/api/task/status/{0}',

            labelsGET: '/erp/api/task/label',
            labelPOST: '/erp/api/task/label/{0}',

            prioritiesGET: '/erp/api/task/priority',
            priorityPOST: '/erp/api/task/priority/{0}',

            //Project section
            projectsGET: "/erp/api/project",
            projectPOST: "/erp/api/project",
            projectByIdGET: "/erp/api/project/{0}",

            //Bridging section
            skillsGET: '/erp/api/skills',
            autocompleteDataFromServerGET: '/erp/api/autocomplete/'
        };

        $rootScope.autocomplete = {
            departmentNames: []
        };

        return {
            login: function (credential) {
                return $http.post(server.authPOST, credential).then(function (data) {
                    return data;
                });
            },

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
                  return Upload.upload({
                        url: server.uploadImagePOST + userId,
                        data: {
                            file: file
                        }
                    });
                }
            },

            deleteImage: function (userId) {
               return $http.delete(server.deleteImageDELETE + userId)
            },

            createUser: function (user) {
                return $http.post(server.userCreatePOST, user);
            },

            sendToEmail: function (email, type) {
                return $http.post(server.sentToEmailTokenPOST, {email: email, type:type});
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

            getPriorities: function(){
                var deferred = $q.defer();
                $http.get(server.prioritiesGET).then(function(response){
                    deferred.resolve(response.data);
                });
                return deferred.promise;
            },

            createStep: function (step) {
                return $http.post(server.stepPOST, step);
            },
    }
    });
