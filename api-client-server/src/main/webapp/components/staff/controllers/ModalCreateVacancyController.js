/**
 * Modal Create Vacancy Controller on client
 *
 * @author Andrii Blyznuk
 */
app
    .controller('ModalCreateVacancyController', function ($scope, vacancyService, VacancyTypeEnum, $mdDialog, autocompleteService) {

        $scope.getAutocompleteData = autocompleteService.getAutocompleteDataFromServer;

        $scope.typeVacancy = VacancyTypeEnum;


        //Creates a new object (vacancy)
        $scope.newVacancy = {
            id: null,
            name: '',
            date: '',
            nameStep: ['Interview'],
            responsible: {},
            candidates: [],
            dutiesAndResponsible: '',
            requiredSkills: '',
            willByAPlus: '',
            offer: '',
            type: new Array(),
            hotVacancy: false
        };

        //This function is performed when the user
        // adds steps that will have a vacancy.
        // The function we obtain the name of the step and checks
        // whether this name if this name is then the name is
        // added to the number of steps.
        // For example (as that is the name Interview step and if the user writes again Interview it will Interview 2
        $scope.addChip = function (chip) {
            var countRepeat = 1;
            for (var i = 0; i < $scope.newVacancy.nameStep.length; i++) {
                if ($scope.newVacancy.nameStep[i] == chip) {
                    countRepeat += 1;
                }
                if ($scope.newVacancy.nameStep[i] == chip+ " " + countRepeat) {
                    countRepeat += 1;
                }
            };
            if(countRepeat == 1){
                $scope.newVacancy.nameStep[$scope.newVacancy.nameStep.length] = chip;
            }else{
                $scope.newVacancy.nameStep[$scope.newVacancy.nameStep.length] = chip + " " + countRepeat;
            }
        };


        $scope.saveVacansy = function () {
            console.log($scope.newVacancy )
            vacancyService.createVacancy($scope.newVacancy);
            $mdDialog.cancel();
        };

        $scope.cancel = function() {
            $mdDialog.cancel();
        };

    });