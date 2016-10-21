describe("ProjectsTaskController", function () {
    var $scope,
        httpBackend,
        rootScope,
        $mdDialog,
        $controller;

    beforeEach(function () {
        module("ERP");
        inject(function ($httpBackend, _$controller_, $injector, _$mdDialog_) {
            httpBackend = $httpBackend;

            httpBackend.whenGET('template/404.html').respond(200);

            rootScope = $injector.get('$rootScope');
            $mdDialog = _$mdDialog_;

            spyOn(rootScope, '$on').and.callThrough();

            $scope = {};
            $controller = _$controller_('ProjectsTaskController', {$scope: $scope});
            httpBackend.flush();
        });
    });

    afterEach(function () {
        httpBackend.verifyNoOutstandingExpectation();
        httpBackend.verifyNoOutstandingRequest();
    });

    it('Test function $rootScope.$on(second-bag.drag) in projects task controller', function () {
        expect(rootScope.$on).toHaveBeenCalled();
        expect(rootScope.$on).toHaveBeenCalledWith('second-bag.drag', jasmine.any(Function));
    });

    it('Test function $rootScope.$on(second-bag.drop) in projects task controller', function () {
        expect(rootScope.$on).toHaveBeenCalled();
        expect(rootScope.$on).toHaveBeenCalledWith('second-bag.drop', jasmine.any(Function));
    });

    it('Test function $rootScope.$on(second-bag.over) in projects task controller', function () {
        expect(rootScope.$on).toHaveBeenCalled();
        expect(rootScope.$on).toHaveBeenCalledWith('second-bag.over', jasmine.any(Function));
    });

    it('Test function $rootScope.$on(second-bag.out) in projects task controller', function () {
        expect(rootScope.$on).toHaveBeenCalled();
        expect(rootScope.$on).toHaveBeenCalledWith('second-bag.out', jasmine.any(Function));
    });

});
