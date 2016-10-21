describe("ModalAddInterviewController", function () {
    var $scope,
        httpBackend,
        $controller,
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
        };

    beforeEach(function () {
        module("ERP");
        inject(function ($httpBackend, _$controller_, _$mdDialog_) {
            httpBackend = $httpBackend;
            $mdDialog = _$mdDialog_;

            httpBackend.whenGET('template/404.html').respond(200);

            $scope = {};
            $controller = _$controller_('ModalAddInterviewController', {$scope: $scope, data: data});
            httpBackend.flush();
        });
    });

    afterEach(function () {
        httpBackend.verifyNoOutstandingExpectation();
        httpBackend.verifyNoOutstandingRequest();
    });


    it('Test function save Interviewer in modal add interviewer controller', function () {
        httpBackend.expect('POST', '/api/step').respond(200, data.vacancy.candidates[0].stepToCandidate[0]);
        $scope.saveInterview().then(function(answer){
            expect(answer.data).toEqual($scope.stepInfo);
            expect(answer.status).toBe(200);

            spyOn($mdDialog, 'cancel').and.callThrough();
            $scope.cancel();
        });
        httpBackend.flush()
    });

    it('Test function $scope.cancel and $mdDialog.cancel() in modal add interview controller', function () {
        spyOn($mdDialog, 'cancel').and.callThrough();
        $scope.cancel();
    });

});
