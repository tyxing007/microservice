/**
 * Step Service
 * Service for CRUD step to vacancy
 *
 * @author Andrii Blyznuk
 */

app
    .factory('stepService', function ($q, serverService) {

        return {

            createStep: function (step) {
                return serverService.createStep(step);
            }
        }
    });