describe("MoreDetailController", function () {
    var $scope,
        httpBackend,
        $mdDialog,
        $controller;

    beforeEach(function () {
        module("ERP");
        inject(function ($httpBackend, _$controller_, _$mdDialog_) {
            httpBackend = $httpBackend;

            httpBackend.whenGET('template/404.html').respond(200);
            $mdDialog = _$mdDialog_;

            $scope = {};
            $controller = _$controller_('MoreDetailController', {$scope: $scope});
            httpBackend.flush();
        });
    });

    afterEach(function () {
        httpBackend.verifyNoOutstandingExpectation();
        httpBackend.verifyNoOutstandingRequest();
    });

    it('Test function $scope.hide() in more detail controller', function () {
        spyOn($mdDialog, 'hide').and.callThrough();
        $scope.hide()
    });

    it('Test function $scope.cancel() in more detail controller', function () {
        spyOn($mdDialog, 'cancel').and.callThrough();
        $scope.cancel()
    });

    it('Test function $scope.answer() in more detail controller', function () {
        spyOn($mdDialog, 'hide').and.callThrough();
        $scope.hide()
    });

});
