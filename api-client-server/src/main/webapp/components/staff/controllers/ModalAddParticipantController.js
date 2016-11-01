/**
 * Modal Create Vacancy Controller on client
 *
 * @author Andrii Blyznuk
 */
app
    .controller('ModalAddParticipantController', function ($scope, CandidateTypeEnum, $mdDialog, autocompleteService, data, vacancyService, userService) {

        $scope.getAutocompleteData = autocompleteService.getAutocompleteDataFromServer;
        $scope.candidateType = CandidateTypeEnum;
        $scope.vacancy = data.vacancy;
        $scope.selectCandidate = null;
        $scope.stepDate = null;
        $scope.stepDate = [];
        var count = 0;


        //Creating the object you want to go a step candidate
        _.filter(data.vacancy.nameStep, function (step) {
            $scope.stepDate[count] = {
                                name: step,
                                stepToCandidate: {
                                    id: null,
                                    date: new Date(),
                                    interviewer: [],
                                    marks: [],
                                    time: "12:38:58.492",
                                    file: null}
                                };
            count++;
        });


        //Creating object candidate
        $scope.newCandidate = {
            id: null,
            email: "",
            name: "",
            lastName: "",
            type: "CANDIDATE",
            statusToCandidate: new Array(),
            stepToCandidate: []
        };

        //The function is executed when the user clicks
        // on the Save button candidate.
        // This feature adds the candidate to the vacancy
        // that is sent to the server where it is stored
        $scope.saveVacancy = function () {

            //This condition responds to a particular candidate
            // elected from existing or create a new candidate
            if ($scope.selectCandidate == null) {

                //This cycle adds that the number of steps for the candidate indicated in vacancy
                for (var i = 0; i < $scope.vacancy.nameStep.length; i++) {
                    for (var j = 0; j <  $scope.stepDate.length; j++) {
                        if ($scope.vacancy.nameStep[i] == $scope.stepDate[j].name){
                            $scope.newCandidate.stepToCandidate[i] = $scope.stepDate[j].stepToCandidate;
                            $scope.newCandidate.stepToCandidate[i].date = moment($scope.newCandidate.stepToCandidate[j].date).format("YYYY-MM-DD");
                        }
                    }
                }

                //Use a function that sends a candidate
                // for the server where it is stored.
                // It returns the id candidate is added to vacancy
                userService.createUser($scope.newCandidate).then(function (answer) {
                    $scope.newCandidate.id = answer.data.id;
                    $scope.vacancy.candidates[$scope.vacancy.candidates.length] = $scope.newCandidate;
                    vacancyService.createVacancy($scope.vacancy);
                })
            }else {

                //This cycle adds that the number of steps for the candidate indicated in vacancy
                for (var i = 0; i < $scope.vacancy.nameStep.length; i++) {
                    for (var j = 0; j <  $scope.stepDate.length; j++) {
                        if ($scope.vacancy.nameStep[i] == $scope.stepDate[j].name){
                            $scope.selectCandidate.stepToCandidate[i] = $scope.stepDate[j].stepToCandidate;
                            $scope.selectCandidate.stepToCandidate[i].date = moment($scope.selectCandidate.stepToCandidate[j].date).format("YYYY-MM-DD");
                        }
                    }
                }
                $scope.vacancy.candidates[$scope.vacancy.candidates.length] = $scope.selectCandidate;
                vacancyService.createVacancy($scope.vacancy);
            }
            $mdDialog.cancel();
        };

        $scope.cancel = function () {
            $mdDialog.cancel();
        };

    });