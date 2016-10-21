describe("CreateTaskController", function () {
    var $scope,
        httpBackend,
        rootScope,
        taskService,
        $mdDialog,
        projectObj = {
            startDate: new Date(),
            dueDate: new Date()
        },
        taskObj = {
            labels: [],
            priority: null,
            title: "title",
            project: projectObj
        },
        priorityObj = {
            id: 1,
            level: "High"
        },
        $controller;

    beforeEach(function () {
        module("ERP");
        inject(function ($httpBackend, _$controller_, $injector, _$mdDialog_, _taskService_) {
            httpBackend = $httpBackend;
            $mdDialog = _$mdDialog_;
            taskService = _taskService_;

            httpBackend.whenGET('template/404.html').respond(200);
            httpBackend.whenGET('/api/task/priority').respond(200, [priorityObj, priorityObj]);

            rootScope = $injector.get('$rootScope');

            spyOn(rootScope, '$broadcast').and.callThrough();

            $scope = {};
            $controller = _$controller_('CreateTaskController', {$scope: $scope});
            httpBackend.flush();
        });
    });

    afterEach(function () {
        httpBackend.verifyNoOutstandingExpectation();
        httpBackend.verifyNoOutstandingRequest();
    });


    it('Test function taskService.getPriorities()', function () {
        expect($scope.priorities).toEqual([priorityObj, priorityObj]);
    });

    it('Test function $scope.answer in this function use getProject and saveTask', function () {
        taskService.setCurrentProject(projectObj);
        httpBackend.expect('POST', '/api/task', taskObj).respond(200, [taskObj, taskObj]);
        $scope.task.priority = null;
        $scope.answer();
        httpBackend.flush();
        expect($scope.task).toEqual(taskObj)
    });

    it('Test function $scope.cancel and $mdDialog.cancel() in create task controller', function () {
        spyOn($mdDialog, 'cancel').and.callThrough();
        $scope.cancel();
    });

    it('Test function $scope.hide and $mdDialog.cancel() in create task controller', function () {
        spyOn($mdDialog, 'hide').and.callThrough();
        $scope.hide();
    });




});

