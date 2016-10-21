describe("ModalAddParticipantController", function () {
    var $scope,
        httpBackend,
        $controller,
        rootScope,
        $mdDialog,
        userObj = {
            id: null,
            email: "",
            name: "",
            lastName: "",
            type: "CANDIDATE",
            statusToCandidate: "PENDING",
            stepToCandidate: [{
                                id:null,
                                date: null,
                                interviewer:[],
                                vacancy:{
                                        id:1,
                                        name: ""
                                        },
                                marks: [],
                                time: null,
                                gpaMark: 0,
                                file: null
                             }],
            totalScore: 0
        },
        data = {
            vacancy: {
                id: 1,
                name: '',
                date: '',
                nameStep: ['Interview'],
                responsible: {},
                candidates: [],
                dutiesAndResponsible: '',
                requiredSkills: '',
                willByAPlus: '',
                offer: '',
                type: new Array(),
                hotVacancy: false
            }
        };

    beforeEach(function () {
        module("ERP");
        inject(function ($httpBackend, _$controller_, $injector, _$mdDialog_) {
            httpBackend = $httpBackend;
            $mdDialog = _$mdDialog_;

            httpBackend.whenGET('template/404.html').respond(200);

            rootScope = $injector.get('$rootScope');
            spyOn(rootScope, '$broadcast').and.callThrough();

            $scope = {};
            $controller = _$controller_('ModalAddParticipantController', {$scope: $scope, data: data});
            httpBackend.flush();
        });
    });

    afterEach(function () {
        httpBackend.verifyNoOutstandingExpectation();
        httpBackend.verifyNoOutstandingRequest();
    });


    it('Test function add participant and refresh vacancy in modal add participant controller', function () {
        httpBackend.expect('POST', '/api/user/create', $scope.newCandidate).respond(200, $scope.newCandidate);
        httpBackend.expect('POST', '/api/vacancy/create', data.vacancy).respond(200, data.vacancy);
        $scope.saveVacancy();
        httpBackend.flush();
        expect($scope.vacancy.candidates.length).toBe(1);
        expect($scope.vacancy.candidates[0]).toEqual(userObj);

        httpBackend.expect('POST', '/api/vacancy/create', data.vacancy).respond(200, data.vacancy);
        userObj.name = "Select candidate";
        userObj.id = 1;
        $scope.selectCandidate = userObj;
        $scope.saveVacancy();
        httpBackend.flush();
        expect($scope.vacancy.candidates.length).toBe(2);
        expect($scope.vacancy.candidates[1]).toEqual(userObj);

        spyOn($mdDialog, 'cancel').and.callThrough();
        $scope.cancel();

        expect(rootScope.$broadcast).toHaveBeenCalled();
        expect(rootScope.$broadcast).toHaveBeenCalledWith("EditVacancyStaff", data.vacancy);

    });

    it('Test function $scope.cancel and $mdDialog.cancel() in modal add participant controller', function () {
        spyOn($mdDialog, 'cancel').and.callThrough();
        $scope.cancel();
    });

});