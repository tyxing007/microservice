/**
 * Modal add participant Controller on client
 *
 * @author Andrii Blyznuk
 */
app
    .controller('ModalAddInterviewController', function ($scope, CandidateTypeEnum, $mdDialog, autocompleteService, data, formats, stepService) {

        $scope.getAutocompleteData = autocompleteService.getAutocompleteDataFromServer;

                _.filter(data.vacancy.candidates, function (candidate) {
                    if (candidate.id == data.candidateId) {
                        _.filter(candidate.stepToCandidate, function (step) {
                            if (step.id == data.stepId) {
                                $scope.stepInfo = step;
                                $scope.candidateName = candidate.name + " " + candidate.lastName;
                            };
                        });
                    };
                });

        $scope.addKit = function(chip) {
            return  chip;
        };


        $scope.saveInterview = function () {
            $scope.stepInfo.date = moment($scope.dateForStep).format(formats.date);
             var response = stepService.createStep($scope.stepInfo);
            $mdDialog.cancel();
            return response;
        };

        $scope.cancel = function () {
            $mdDialog.cancel();
        };

    });
