/**
 * User Service
 * Service for creating user
 *
 * @author Andrii Blyznuk
 */
app
    .factory('userService', function ($q, serverService) {

        return {

            getUsers: function () {
                var deferred = $q.defer();
                serverService.getUsers().then(function (response) {
                    deferred.resolve(response)
                });
                return deferred.promise;
            },

            getUser: function (userId) {
                var deferred = $q.defer();
                serverService.getUser(userId).then(function (response) {
                    deferred.resolve(response);
                });
                return deferred.promise;
            },

            uploadImage: function (file, userId) {
                return serverService.uploadImage(file,userId);
            },

            deleteImage: function (userId) {
                return serverService.deleteImage(userId);
            },

            createUser: function (user) {
                return serverService.createUser(user);
            },

            sendToEmail: function (email, type) {
                return serverService.sendToEmail(email, type);
            }


        }
        
    });