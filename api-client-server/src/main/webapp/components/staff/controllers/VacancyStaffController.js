/**
 * Vacancy Controller on client
 *
 * @author Andrii Blyznuk
 */
app
    .controller('VacancyStaffController', function ($scope, vacancyService, VacancyTypeEnum, $mdDialog) {

        //The method getVacancies we obtain a list of jobs that just sort jobs
        //by types and types of these variables are used
        //at vacancy.staff.html order to lead number
        //of a certain type vacancy
        vacancyService.getVacancies()
            .then(function (answer) {
                $scope.vacancies = answer;
                $scope.OPENED = 0;
                $scope.INACTIVE = 0;
                $scope.CLOSED = 0;
                angular.forEach($scope.vacancies, function (vacancy) {
                    if (vacancy.type == VacancyTypeEnum.OPENED) $scope.OPENED += 1;
                    if (vacancy.type == VacancyTypeEnum.INACTIVE) $scope.INACTIVE += 1;
                    if (vacancy.type == VacancyTypeEnum.CLOSED) $scope.CLOSED += 1;
                });
                $scope.date = new Date();
            });


        //The method filterByType is called at vacancy.staff.html
        //and dynamically filter on it when vacancies
        //clicks on their specific type
        //Returns boolean
        $scope.filterByType = function (item) {
            if (!$scope.typeVacancy
                || (item.type.toLowerCase().indexOf($scope.typeVacancy.toLowerCase()) != -1)) {
                return true;
            }
            return false;
        };

        //The method search follows the search page
        //and dynamically filter vacancy.staff.html
        //vacancies on the name the user enters in the search.
        //Returns boolean
        $scope.search = function (item) {
            if (!$scope.searchText
                || (item.name.toLowerCase().indexOf($scope.searchText.toLowerCase()) != -1)
            ) {
                return true;
            }
            return false
        };

        //The method open responds to click that made a user to open a vacancy,
        //and this method takes a boolean change openMoreInfo and Int changing vacancyId,
        //variable openMoreInfo which is used in order to perform a body condition
        //only when openMoreInfo be true (when the user opens a vacancy),
        //if the user closes the vacancy condition is not fulfilled.
        //The method does not return anything.
        //In the Filter method used in order to find us the vacancy opened
        //by the user and transmit it to the processing funksiyi GPAMark
        $scope.open = function (openMoreInfo, vacancyId) {
            if (openMoreInfo) {
                _.filter($scope.vacancies, function (value) {
                    if (value.id == vacancyId) {
                        console.log(value);
                        value = $scope.GPAMark(value)
                    }
                    ;
                });
            }
            ;
        };

        //The method GPAMark which comprises three series of searches
        //that edge to get estimates vacancies which receives
        //method _.meanBy vyrahovuyuye their arithmetic mean
        //and create additional fields in the object recording medium value;
        $scope.GPAMark = function (vancancy) {
            _.forEach(vancancy.candidates, function (candidate) {
                _.forEach(candidate.stepToCandidate, function (step) {
                    if (step.interviewer.length == step.marks.length && step.marks.length != 0) {
                        step.gpaMark = _.meanBy(step.marks, 'mark');
                    } else {
                        step.gpaMark = 0.0;
                    }
                });
                candidate.totalScore = _.meanBy(candidate.stepToCandidate, "gpaMark");
                _.forEach(candidate.stepToCandidate, function (step) {
                    if (step.gpaMark == 0) {
                        candidate.totalScore = 0;
                    }
                });
            });

        };

        //The method openStepInfo is executed when the user clicked to open the step.
        //This method takes Id vacancy, the candidate moves and steps GPA.
        //the method is the condition that will then true steps when
        //the average score is greater than 0 and then execute method $mdDialog.show
        //popup window will open to display information about a step,
        //or a window that notifies the user that not all intervievers laid assessment by step
        $scope.openStepInfo = function (vacancyId, candidateId, stepId, ev, stepGPAMark) {
            if (stepGPAMark != 0) {
                $mdDialog.show({
                    controller: 'ModalStepController',
                    locals: {
                        data: {
                            vacancy: $scope.vacancies,
                            vacancyId: vacancyId,
                            candidateId: candidateId,
                            stepId: stepId
                        }
                    },
                    templateUrl: 'template/modal.show.step.html',
                    parent: angular.element(document.body),
                    targetEvent: ev,
                    hasBackdrop: true,
                    clickOutsideToClose: true
                });
            } else {
                $mdDialog.show({
                    controller: 'VacancyStaffController',
                    contentElement: '#myDialog',
                    parent: angular.element(document.body),
                    targetEvent: ev,
                    clickOutsideToClose: true
                });
            }
        };

        //This method openCreateVacancy opens a popup create vacancy
        $scope.openCreateVacancy = function (ev) {
            $mdDialog.show({
                controller: 'ModalCreateVacancyController',
                templateUrl: 'template/modal.create.vacancy.html',
                parent: angular.element(document.body),
                targetEvent: ev,
                hasBackdrop: true,
                clickOutsideToClose: false
            })
        };

        $scope.openEditVacancy = function (ev, vacancy) {
            $mdDialog.show({
                controller: 'ModalEditVacancyController',
                templateUrl: 'template/modal.edit.vacancy.html',
                parent: angular.element(document.body),
                targetEvent: ev,
                hasBackdrop: true,
                clickOutsideToClose: false,
                locals: {
                    data: {
                        vacancy: vacancy
                    }
                },
            })
        };
        $scope.openAddParticipant = function (ev, vacancy) {
            $mdDialog.show({
                controller: 'ModalAddParticipantController',
                templateUrl: 'template/modal.add.participant.html',
                parent: angular.element(document.body),
                targetEvent: ev,
                hasBackdrop: true,
                clickOutsideToClose: false,
                locals: {
                    data: {
                        vacancy: vacancy
                    }
                },
            })
        };

    });