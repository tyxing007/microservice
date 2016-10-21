/**
 * Autocomplete Service
 * Service for completion
 *
 * @author Andrii Blyznuk
 */
app
    .factory('autocompleteService', function ($rootScope, serverService) {

        return {

            getAutocompleteDataFromServer: function(itemName) {
                serverService.getAutocompleteDataFromServer(itemName)
                    .success(function(data) {
                        $rootScope.autocomplete[itemName] = data;
                    });
            }
        }

    });