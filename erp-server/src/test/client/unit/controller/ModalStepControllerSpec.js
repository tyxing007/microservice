describe("ModalStepController", function () {
    var $scope,
        httpBackend,
        $mdDialog,
        data = {
            vacancy: {
                id: 1,
                name: "Test Name Vacancy",
                date: new Date(),
                type: "OPENED",
                candidates: [{
                    id: 1,
                    name: 'Test name Candidate',
                    stepToCandidate: [{
                        id: 1,
                        vacancy: {
                            id: 1,
                            name: 'Test Name Vacancy'
                        },
                        interviewer: [{
                            id: 1,
                            name: "Test name Interviewer 2"
                        },{
                            id: 2,
                            name: "Test name Interviewer 2"
                        }],
                        marks: [{
                            id: 1,
                            mark: 6.5
                        },{
                            id: 2,
                            mark: 8.5
                        }]
                    }]
                }]
            },
            vacancyId: 1,
            candidateId: 1,
            stepId: 1
        },
        $controller;

    beforeEach(function () {
        module("ERP");
        inject(function ($httpBackend, _$controller_, _$mdDialog_) {
            httpBackend = $httpBackend;

            httpBackend.whenGET('template/404.html').respond(200);

            $mdDialog = _$mdDialog_;

            $scope = {};
            $controller = _$controller_('ModalStepController', {$scope: $scope, data: data});
            httpBackend.flush();
        });
    });

    afterEach(function () {
        httpBackend.verifyNoOutstandingExpectation();
        httpBackend.verifyNoOutstandingRequest();
    });

    it('Test function get user from BD and count they type in people staff controller', function () {
        expect($scope.stepInfo).toEqual(data.vacancy.candidates[0].stepToCandidate[0]);
        expect($scope.stepInfo.candidate).toEqual(data.vacancy.candidates[0].name);
        expect($scope.stepInfo.totalScore).toEqual(data.vacancy.candidates[0].totalScore)
    });

    it('Test function $scope.cancel and $mdDialog.cancel() in people staff controller', function () {
        spyOn($mdDialog, 'cancel').and.callThrough();
        $scope.cancel();
    });

});