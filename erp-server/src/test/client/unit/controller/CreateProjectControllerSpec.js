describe("CreateProjectController", function () {
    var $scope,
        httpBackend,
        rootScope,
        $mdDialog,
        projectObj = {
            startDate: new Date(),
            dueDate: new Date()
        },
        $controller;

    beforeEach(function () {
        module("ERP");
        inject(function ($httpBackend, _$controller_, $injector, _$mdDialog_) {
            httpBackend = $httpBackend;
            $mdDialog = _$mdDialog_;

            httpBackend.whenGET('template/404.html').respond(200);

            rootScope = $injector.get('$rootScope');

            spyOn(rootScope, '$broadcast').and.callThrough();

            $scope = {};
            $controller = _$controller_('CreateProjectController', {$scope: $scope});
            httpBackend.flush();
        });
    });

    afterEach(function () {
        httpBackend.verifyNoOutstandingExpectation();
        httpBackend.verifyNoOutstandingRequest();
    });


    it('Test function save project and called $broadcast in function $scope.answer()', function () {
        httpBackend.expect('POST', '/api/project').respond(200, projectObj);
        $scope.answer().then(function(answer){
            spyOn($mdDialog, 'hide').and.callThrough();
            $scope.hide();

            expect(rootScope.$broadcast).toHaveBeenCalled();
            expect(rootScope.$broadcast).toHaveBeenCalledWith("append-project", projectObj);
            expect(answer).toEqual(projectObj);
        });
        httpBackend.flush()
    });

    it('Test function $scope.cancel and $mdDialog.cancel() in create project controller', function () {
        spyOn($mdDialog, 'cancel').and.callThrough();
        $scope.cancel();
    });

    it('Test function $scope.hide and $mdDialog.cancel() in create project controller', function () {
        spyOn($mdDialog, 'hide').and.callThrough();
        $scope.hide();
    });




});
