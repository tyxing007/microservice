/**
 * Projects info Controller
 *
 * @author Kulinenko Roman, Sem Babenko
 */
app.controller('ProjectsInfoController', function ($scope, $sce, serverService) {

    $scope.lock_description = true;
    $scope.icon_description = 'edit';

    $scope.controlDescription = function (ev) {
        if($scope.lock_description){
            $scope.lock_description = false;
            $scope.icon_description = 'done';
        }else{
            //TODO make request to server update description
            $scope.lock_description = true;
            $scope.icon_description = 'edit';
        }
    }

    $scope.lock_technology = true;
    $scope.icon_technology = 'edit';

    $scope.controlTechnology = function (ev) {
        if($scope.lock_technology){
            $scope.lock_technology = false;
            $scope.icon_technology = 'done';
        }else{
            //TODO make request to server update technology
            $scope.lock_technology = true;
            $scope.icon_technology = 'edit';
        }
    }

    $scope.project = {
        description: "Lorem Ipsum is simply dummy text of the printing and typesetting industry.Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,when an unknown printer took a galley of type and scrambled it to make a type specimen book.It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
    }

    var self = this;
    self.readonly = false;
    self.selectedItem = null;
    self.searchText = null;
    self.querySearch = querySearch;
    self.vegetables = loadVegetables();
    self.selectedVegetables = [
        {'name':'Broccoli', 'type' : 'Brassica'},
        {'name':'Cabbage', 'type' : 'Brassica'}];
    self.numberChips = [];
    self.numberChips2 = [];
    self.numberBuffer = '';
    self.autocompleteDemoRequireMatch = false;
    self.transformChip = transformChip;

    self.notFound = function(key) {
        console.log("key", key);
        transformChip(key);
    }
    /**
     * Return the proper object when the append is called.
     */
    function transformChip(chip) {
        // If it is an object, it's already a known chip
        if (angular.isObject(chip)) {
            return chip;
        }

        // Otherwise, create a new one
        return {
            name: chip,
            type: 'new'
        }
    }

    /**
     * Search for vegetables.
     */
    function querySearch(query) {
        var results = query ? self.vegetables.filter(createFilterFor(query)) : [];
        return results;
    }

    /**
     * Create filter function for a query string
     */
    function createFilterFor(query) {
        var lowercaseQuery = angular.lowercase(query);

        return function filterFn(vegetable) {
            return (vegetable._lowername.indexOf(lowercaseQuery) === 0) ||
                   (vegetable._lowertype.indexOf(lowercaseQuery) === 0);
        };

    }

    function loadVegetables() {
        var veggies = [{
            'name': 'Broccoli',
            'type': 'Brassica'
        }, {
            'name': 'Cabbage',
            'type': 'Brassica'
        }, {
            'name': 'Carrot',
            'type': 'Umbelliferous'
        }, {
            'name': 'Lettuce',
            'type': 'Composite'
        }, {
            'name': 'Spinach',
            'type': 'Goosefoot'
        }];

        return veggies.map(function(veg) {
            veg._lowername = veg.name.toLowerCase();
            veg._lowertype = veg.type.toLowerCase();
            return veg;
        });
    }

    $scope.personPopover = $sce.trustAsHtml('<h5 class=\'top-info-team__popover-name\'>Roman Kulinenko</h5><span class=\'top-info-team__popover-role\'>Software developer</span>');
});