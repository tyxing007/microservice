/**
 * Modal Step Controller on client
 *
 * @author Andrii Blyznuk
 */
app
    .controller('ModalStepController', function ($scope, data, $mdDialog) {

        $scope.cancel = function() {
            $mdDialog.cancel();
        };

        //This filter gets to the steps of a vacancy
        // and a candidate gets the name of the
        // candidate and overall step
        _.filter(data.vacancy, function (vacancy) {
            if (vacancy.id == data.vacancyId) {
                _.filter(vacancy.candidates, function (candidate) {
                    if (candidate.id == data.candidateId) {
                        _.filter(candidate.stepToCandidate, function (step) {
                            if (step.id == data.stepId) {
                                $scope.stepInfo = step;
                                $scope.stepInfo.candidate = candidate.name;
                                $scope.stepInfo.totalScore = candidate.totalScore;
                                if (vacancy.responsible != null){
                                    $scope.stepInfo.responsible = vacancy.responsible.name + " " + vacancy.responsible.lastName;
                                }
                            };
                        });
                    };
                });
            };
        });
    });
