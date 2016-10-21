describe("ModalCreateVacancyController", function () {
    var $scope,
        httpBackend,
        $controller,
        $mdDialog,
        rootScope,
        vacancyObj = {
            id: null,
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
        };

    beforeEach(function () {
        module("ERP");
        inject(function ($httpBackend, _$controller_, _$mdDialog_, $injector) {
            httpBackend = $httpBackend;
            $mdDialog = _$mdDialog_;
            httpBackend.whenGET('template/404.html').respond(200);

            rootScope = $injector.get('$rootScope');
            spyOn(rootScope, '$broadcast').and.callThrough();

            $scope = {};
            $controller = _$controller_('ModalCreateVacancyController', {$scope: $scope});

            httpBackend.flush();
        });
    });

    afterEach(function () {
        httpBackend.verifyNoOutstandingExpectation();
        httpBackend.verifyNoOutstandingRequest();
    });

    it('Test function create new vacancy and save to BD', function () {
        httpBackend.expect('POST', '/api/vacancy/create', vacancyObj).respond(200, vacancyObj);
        $scope.saveVacancy().then(function (answer) {
            expect(answer.status).toBe(200);
            expect(rootScope.$broadcast).toHaveBeenCalled();
            expect(rootScope.$broadcast).toHaveBeenCalledWith("CreateVacancyStaff", vacancyObj);

            spyOn($mdDialog, 'cancel').and.callThrough();
            spyOn($mdDialog, 'hide').and.callThrough();
        });
        httpBackend.flush();
    });

    it('Test function add chip in modal', function () {
        $scope.addChip("Interview");
        expect($scope.newVacancy.nameStep).toEqual(['Interview', 'Interview 2']);
        $scope.addChip("Test");
        expect($scope.newVacancy.nameStep).toEqual(['Interview', 'Interview 2', 'Test']);
        $scope.addChip("Test");
        expect($scope.newVacancy.nameStep).toEqual(['Interview', 'Interview 2', 'Test', 'Test 2']);
    });

    it('Test function $scope.cancel and $mdDialog.cancel() in modal create vacancy controller', function () {
        spyOn($mdDialog, 'cancel').and.callThrough();
        $scope.cancel();
    });

});