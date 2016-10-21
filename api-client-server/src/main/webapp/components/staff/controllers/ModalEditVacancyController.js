/**
 * Modal Edit Vacancy Controller on client
 *
 * @author Andrii Blyznuk
 */
app
    .controller('ModalEditVacancyController', function ($rootScope, $scope, vacancyService, VacancyTypeEnum, $mdDialog, autocompleteService, data) {


        $scope.getAutocompleteData = autocompleteService.getAutocompleteDataFromServer;

        $scope.typeVacancy = VacancyTypeEnum;

        $scope.newVacancy = data.vacancy;

        ////This function is performed when the user
        // adds steps that will have a vacancy.
        // The function we obtain the name of the step and checks
        // whether this name if this name is then the name is
        // added to the number of steps.
        // For example (as that is the name Interview step and if the user writes again Interview it will Interview 2)
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

        $scope.saveVacancy = function () {
            var response = vacancyService.createVacancy($scope.newVacancy).then(function (answer) {
                $rootScope.$broadcast("EditVacancyStaff", answer.data);
                return answer;
            });
            $mdDialog.cancel();
            return response;
        };


        $scope.cancel = function() {
            $mdDialog.cancel();
        };

    });