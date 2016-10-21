describe("ModalEditVacancyController", function () {
    var $scope,
        httpBackend,
        $mdDialog,
        rootScope,
        $controller,
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
            }
    };

    beforeEach(function () {
        module("ERP");
        inject(function ($httpBackend, _$controller_, _$mdDialog_, $injector) {
            httpBackend = $httpBackend;

            httpBackend.whenGET('template/404.html').respond(200);

            $mdDialog = _$mdDialog_;
            rootScope = $injector.get('$rootScope');

            spyOn(rootScope, '$broadcast').and.callThrough();

            $scope = {};
            $controller = _$controller_('ModalEditVacancyController', {$scope: $scope, data: data});
            httpBackend.flush();
        });
    });

    afterEach(function () {
        httpBackend.verifyNoOutstandingExpectation();
        httpBackend.verifyNoOutstandingRequest();
    });

    it('Test function edit vacancy and save', function () {
        httpBackend.expect('POST', '/api/vacancy/create', data.vacancy).respond(200, data);
        $scope.saveVacancy().then(function (answer) {
            expect(rootScope.$broadcast).toHaveBeenCalled();
            expect(rootScope.$broadcast).toHaveBeenCalledWith("EditVacancyStaff", data);

            expect(answer.status).toBe(200);
            spyOn($mdDialog, 'cancel').and.callThrough();
            $scope.cancel();
        });
        httpBackend.flush()
    });

    it('Test function $scope.cancel and $mdDialog.cancel() in modal edit vacancy controller', function () {
        spyOn($mdDialog, 'cancel').and.callThrough();
        $scope.cancel();
    });

});