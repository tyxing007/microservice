/**
 * Modal Step Controller on client
 *
 * @author Andrii Blyznuk
 */
app
    .controller('ModalStepController', function ($scope, data, $mdDialog) {

        $scope.cancel = function () {
            $mdDialog.cancel();
        };

        //This filter gets to the steps of a vacancy
        // and a candidate gets the name of the
        // candidate and overall step
        _.filter(data.vacancy.candidates, function (candidate) {
            if (candidate.id == data.candidateId) {
                _.filter(candidate.stepToCandidate, function (step) {
                    if (step.id == data.stepId) {
                        $scope.stepInfo = step;
                        $scope.stepInfo.candidate = candidate.name;
                        $scope.stepInfo.totalScore = candidate.totalScore;
                        if ($scope.stepInfo.responsible != null) {
                            $scope.stepInfo.responsibleName = $scope.stepInfo.responsible.name + ' ' + $scope.stepInfo.responsible.lastName;
                        } else if (data.vacancy.responsible != null) {
                            $scope.stepInfo.responsibleName = data.vacancy.responsible.name + " " + data.vacancy.responsible.lastName;
                        }
                    }
                    ;
                });
            }
            ;
        });
    });