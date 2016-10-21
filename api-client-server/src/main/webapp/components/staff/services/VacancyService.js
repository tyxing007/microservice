/**
 * Vacancy Service
 * Service for creating vacancy
 *
 * @author Andrii Blyznuk
 */
app
    .factory('vacancyService', function ($q, serverService) {

        return{

            getVacancies: function () {
                var deferred = $q.defer();
                serverService.getVacancies()
                    .then(function (response) {
                        deferred.resolve(response)
                    });
                return deferred.promise;
            },

            createVacancy: function (vacancy) {
                return serverService.saveVacancy(vacancy);
            }

        }
    });