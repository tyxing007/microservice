describe("ProjectsController", function () {
    var $scope,
        httpBackend,
        rootScope,
        $mdDialog,
        project = {
            name: "Test project name",
            startDate: new Date(),
            dueDate: new Date()
        },
        $controller;

    beforeEach(function () {
        module("ERP");
        inject(function ($httpBackend, _$controller_, $injector, _$mdDialog_) {
            httpBackend = $httpBackend;

            httpBackend.whenGET('template/404.html').respond(200);
            httpBackend.whenGET('/api/project').respond(200,[project, project]);

            rootScope = $injector.get('$rootScope');
            $mdDialog = _$mdDialog_;

            spyOn(rootScope, '$on').and.callThrough();

            $scope = {};
            $controller = _$controller_('ProjectsController', {$scope: $scope});
            httpBackend.flush();
        });
    });

    afterEach(function () {
        httpBackend.verifyNoOutstandingExpectation();
        httpBackend.verifyNoOutstandingRequest();
    });

    it('Test function projectService.getProjects in projects controller', function () {
       expect($scope.projects).toEqual([project, project])
    });

    it('Test function $rootScope.$on(append-project) in projects controller', function () {
        expect(rootScope.$on).toHaveBeenCalled();
        expect(rootScope.$on).toHaveBeenCalledWith('append-project', jasmine.any(Function));
    });

    it('Test function $scope.selectProject in projects controller', function () {
        expect($scope.projectLabel).toBe('Select project');
        $scope.selectProject(null, project);
        expect($scope.projectLabel).toBe(project.name);
    });

    it('Test function $scope.names', function (){
        expect($scope.names).toEqual([{projects: 'Booking'},{projects: 'Twaddle'},{projects: 'Perry'}]);
    });

    it('Test function showCreteProject and test $mdDialog.show', function (){
        httpBackend.whenGET('template/modal.create.project.html').respond(200);
        spyOn($mdDialog, 'show').and.callThrough();
        $scope.showCreteProject();
        expect($mdDialog.show).toHaveBeenCalled();
        httpBackend.flush();
    });

    it('Test function showCreteTask and test $mdDialog.show', function (){
        httpBackend.whenGET('template/modal.create.task.html').respond(200);
        httpBackend.whenGET('/api/task/priority').respond(200);

        spyOn($mdDialog, 'show').and.callThrough();
        $scope.currentProject = true;

        $scope.showCreteTask();
        expect($mdDialog.show).toHaveBeenCalled();
        httpBackend.flush();
    });

    it('Test function showEditTask and test $mdDialog.show', function (){
        httpBackend.whenGET('template/modal.edit.task.html').respond(200);

        spyOn($mdDialog, 'show').and.callThrough();

        $scope.showEditTask();
        expect($mdDialog.show).toHaveBeenCalled();
        httpBackend.flush();
    });

    it('Test function showMoreDetail and test $mdDialog.show', function (){
        httpBackend.whenGET('template/modal.more.detail.html').respond(200);

        spyOn($mdDialog, 'show').and.callThrough();

        $scope.showMoreDetail();
        expect($mdDialog.show).toHaveBeenCalled();
        httpBackend.flush();
    });


});
