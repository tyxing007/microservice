/**
 * Modal add interview Controller on client
 *
 * @author Andrii Blyznuk
 */
app
    .controller('ModalAddParticipantController', function ($scope, CandidateTypeEnum, $mdDialog, autocompleteService, $rootScope, formats, data, vacancyService, userService) {

        $scope.getAutocompleteData = autocompleteService.getAutocompleteDataFromServer;
        $scope.candidateType = CandidateTypeEnum;
        $scope.vacancy = data.vacancy;
        $scope.selectCandidate = null;
        $scope.stepDate = [];
        var count = 0;


        //Creating the object you want to go a step candidate
        _.filter(data.vacancy.nameStep, function (step) {
            $scope.stepDate[count] = {
                                name: step,
                                stepToCandidate: {
                                    id: null,
                                    date: null,
                                    interviewer: [],
                                    vacancy: { id: data.vacancy.id,
                                               name: data.vacancy.name},
                                    marks: [],
                                    time: null,
                                    gpaMark: 0,
                                    file: null}
                                };
            count++;
        });

        $scope.date = null;

        //Creating object candidate
        $scope.newCandidate = {
            id: null,
            email: "",
            name: "",
            lastName: "",
            type: "CANDIDATE",
            statusToCandidate: CandidateTypeEnum.PENDING,
            stepToCandidate: [],
            totalScore: 0
        };

        //The function is executed when the user clicks
        // on the Save button candidate.
        // This feature adds the candidate to the vacancy
        // that is sent to the server where it is stored
        $scope.saveVacancy = function () {
            var selectCandidatePresent = false;
            _.filter($scope.vacancy.candidates,function (candidate) {
                if($scope.selectCandidate != null && candidate.id == $scope.selectCandidate.id){
                    selectCandidatePresent = true;
                }
            });

            //This condition responds to a particular candidate
            // elected from existing or create a new candidate
            if ($scope.selectCandidate == null ) {

                //This cycle adds that the number of steps for the candidate indicated in vacancy
                for (var i = 0; i < $scope.vacancy.nameStep.length; i++) {
                    for (var j = 0; j <  $scope.stepDate.length; j++) {
                        if ($scope.vacancy.nameStep[i] == $scope.stepDate[j].name){
                            $scope.newCandidate.stepToCandidate[i] = $scope.stepDate[j].stepToCandidate;
                        }
                    }
                }

                //Use a function that sends a candidate
                // for the server where it is stored.
                // It returns the id candidate is added to vacancy
                userService.createUser($scope.newCandidate).then(function (answer) {
                    $scope.newCandidate.id = answer.data.id;
                    $scope.vacancy.candidates[$scope.vacancy.candidates.length] = $scope.newCandidate;
                    vacancyService.createVacancy($scope.vacancy).then(function (answer) {
                        $rootScope.$broadcast("EditVacancyStaff", answer.data);
                    });
                })
            }else if(!selectCandidatePresent){

                //This cycle adds that the number of steps for the candidate indicated in vacancy
                for (var i = 0; i < $scope.vacancy.nameStep.length; i++) {
                    for (var j = 0; j <  $scope.stepDate.length; j++) {
                        if ($scope.vacancy.nameStep[i] == $scope.stepDate[j].name){
                            $scope.selectCandidate.stepToCandidate[$scope.selectCandidate.stepToCandidate.length] = $scope.stepDate[j].stepToCandidate;
                        }
                    }
                }
                $scope.vacancy.candidates[$scope.vacancy.candidates.length] = $scope.selectCandidate;
                vacancyService.createVacancy($scope.vacancy).then(function (answer) {
                    $rootScope.$broadcast("EditVacancyStaff", answer.data);
                });
            }
            $mdDialog.cancel();
        };

        $scope.cancel = function () {
            $mdDialog.cancel();
        };

    });