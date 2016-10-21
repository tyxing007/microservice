describe("VacancyStaffController", function () {
   var $scope,
       httpBackend,
       $controller,
       $mdDialog,
       rootScope,
       vacancyObj = {
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
       };


    beforeEach(function () {
        module("ERP");
        inject(function ($httpBackend, _$controller_, _$mdDialog_, $injector) {
            httpBackend = $httpBackend;

            $httpBackend.whenGET('/api/vacancies').respond(200, [vacancyObj, vacancyObj, vacancyObj]);
            $httpBackend.whenGET('template/404.html').respond(200);

            rootScope = $injector.get('$rootScope');
            $mdDialog = _$mdDialog_;
            spyOn(rootScope, '$on').and.callThrough();

            $scope = {};
            $controller = _$controller_('VacancyStaffController', {$scope: $scope});
            httpBackend.flush();
        });
    });

    afterEach(function() {
        httpBackend.verifyNoOutstandingExpectation();
        httpBackend.verifyNoOutstandingRequest();
    });

    it('Test function $rootScope.$on(CreateVacancyStaff) in projects controller', function () {
        expect(rootScope.$on).toHaveBeenCalled();
        expect(rootScope.$on).toHaveBeenCalledWith('CreateVacancyStaff', jasmine.any(Function));
    });

    it('Test function $rootScope.$on(EditVacancyStaff) in projects controller', function () {
        expect(rootScope.$on).toHaveBeenCalled();
        expect(rootScope.$on).toHaveBeenCalledWith('EditVacancyStaff', jasmine.any(Function));
    });

    it('Test function get vacancy and count type', function() {
        expect($scope.vacancies).toEqual([vacancyObj, vacancyObj, vacancyObj]);
        expect($scope.OPENED).toBe(3)
    });

    it('Test function filter vacancy by type ', function() {
        $scope.typeVacancy = "OPENED";
        expect($scope.filterByType(vacancyObj)).toBe(true)
    });

    it('Test function filter by search ', function() {
        $scope.searchText = "Test";
        expect($scope.search(vacancyObj)).toBe(true)
    });

    it('Test function open and count gpa', function() {
        $scope.vacancies = [vacancyObj];
        $scope.open(true, 1);
        expect($scope.vacancies[0].candidates[0].totalScore).toBe(7.5);
        expect($scope.vacancies[0].candidates[0].stepToCandidate[0].gpaMark).toBe(7.5);
    });

    it('Test function openStepInfo and test $mdDialog.show', function (){
        httpBackend.whenGET('template/modal.add.interview.html').respond(200);
        httpBackend.whenGET('template/modal.show.step.html').respond(200);
        spyOn($mdDialog, 'show').and.callThrough();

        $scope.openStepInfo(vacancyObj.id, vacancyObj.candidates[0].id, vacancyObj.candidates[0].stepToCandidate[0].id, null, '2016-08-29', vacancyObj);

        $scope.openStepInfo(vacancyObj.id, vacancyObj.candidates[0].id, vacancyObj.candidates[0].stepToCandidate[0].id, null, '0000-01-01', vacancyObj);
        expect($mdDialog.show).toHaveBeenCalled();
        httpBackend.flush();
    });

    it('Test function openCreateVacancy and test $mdDialog.show', function (){
        httpBackend.whenGET('template/modal.create.vacancy.html').respond(200);
        spyOn($mdDialog, 'show').and.callThrough();
        $scope.openCreateVacancy();
        expect($mdDialog.show).toHaveBeenCalled();
        httpBackend.flush();
    });

    it('Test function openEditVacancy and test $mdDialog.show', function (){
        httpBackend.whenGET('template/modal.edit.vacancy.html').respond(200);
        spyOn($mdDialog, 'show').and.callThrough();
        $scope.openEditVacancy();
        expect($mdDialog.show).toHaveBeenCalled();
        httpBackend.flush();
    });

    it('Test function openAddParticipant and test $mdDialog.show', function (){
        httpBackend.whenGET('template/modal.add.participant.html').respond(200);
        spyOn($mdDialog, 'show').and.callThrough();
        $scope.openAddParticipant(null, vacancyObj);
        expect($mdDialog.show).toHaveBeenCalled();
        httpBackend.flush();
    });

});