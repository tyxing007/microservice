describe("EditTaskController", function () {
    var $scope,
        httpBackend,
        rootScope,
        taskService,
        $mdDialog,
        $controller;

    beforeEach(function () {
        module("ERP");
        inject(function ($httpBackend, _$controller_, $injector, _$mdDialog_, _taskService_) {
            httpBackend = $httpBackend;
            $mdDialog = _$mdDialog_;
            taskService = _taskService_;

            httpBackend.whenGET('template/404.html').respond(200);

            rootScope = $injector.get('$rootScope');

            spyOn(rootScope, '$broadcast').and.callThrough();

            $scope = {};
            $controller = _$controller_('EditTaskController', {$scope: $scope});
            httpBackend.flush();

        });
    });

    afterEach(function () {
        httpBackend.verifyNoOutstandingExpectation();
        httpBackend.verifyNoOutstandingRequest();
    });

    it('Test $scope.requestTypes in edit task controller', function () {
        expect($scope.requestTypes).toEqual([{requests: 'Story'},{requests: 'Task'},{requests: 'Bug'}])
    });

    it('Test $scope.requestPriotitets in edit task controller', function () {
        expect($scope.requestPriotitets).toEqual([{requests: 'Low'},{requests: 'Normal'},{requests: 'Hot'}])
    });

    it('Test function $scope.cancel and $mdDialog.cancel() in edit task controller', function () {
        spyOn($mdDialog, 'cancel').and.callThrough();
        $scope.cancel();
    });

    it('Test function $scope.hide and $mdDialog.hide in edit task controller', function () {
        spyOn($mdDialog, 'hide').and.callThrough();
        $scope.hide();
    });

    it('Test function $scope.answer and $mdDialog.hide() in edit task controller', function () {
        spyOn($mdDialog, 'hide').and.callThrough();
        $scope.answer();
    });

});

