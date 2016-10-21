/**
 * Create Task Controller
 * @author Kulinenko Roman, Sem Babenko
 */
app.controller('CreateTaskController', function ($scope, serverService, taskService, $mdDialog, moment, formats) {

    taskService.getPriorities().then(function (response) {
        $scope.priorities = response;
    });
    $scope.labels = [];

    $scope.task = {
        labels: $scope.labels
    };
    $scope.taskTypes = 'Story Task Bug'.split(' ').map(function (type) {
        return type;
    });

    var self = this;
    self.readonly = false;
    self.selectedItem = null;
    self.searchText = null;
    self.querySearch = querySearch;
    self.vegetables = loadVegetables();
    self.selectedVegetables = [];
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
            name: chip
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

    $scope.upload = function () {
        angular.element(document.querySelector('#fileInput')).click();
    };

    $scope.requestPriotitets = ('Low Normal Hot').split(' ').map(function(requestPriotitet) {
        return {requests: requestPriotitet};
    });

    $scope.hide = function() {
        $mdDialog.hide();
    };
    $scope.cancel = function() {
        $mdDialog.cancel();
    };
    $scope.answer = function(answer) {
        $scope.task.labels = $scope.task.labels.map(function (label) {
            console.log("label", label);return { label: label.name };
        });
        $scope.task.project = taskService.getProject();
        $scope.task.title = "title";
        $scope.task.priority = JSON.parse($scope.task.priority);
//        TODO: change format to localDateTime
//        $scope.task.dueDate = moment($scope.task.endDate).format(formats.date);
        taskService.saveTask($scope.task);
        $mdDialog.hide(answer);
    };
});