describe("UserShowController", function () {
    var $scope,
        httpBackend,
        userObj = {
            id: 1,
            name: "Test Name",
            lastName: "Test lastName",
            email: "Test email"
        },
        $controller;
    
    beforeEach(function () {
        module("ERP");
        inject(function ($httpBackend, _$controller_) {
            httpBackend = $httpBackend;

            httpBackend.expect('GET', '/api/user/{0}').respond(200, userObj);
            httpBackend.whenGET('template/404.html').respond(200);

            $scope = {};
            $controller = _$controller_('UserShowController', {$scope: $scope});
            httpBackend.flush();
        });
    });

    afterEach(function() {
        httpBackend.verifyNoOutstandingExpectation();
        httpBackend.verifyNoOutstandingRequest();
    });

    it('Test function get user by id in UserShowController', function() {
        expect($scope.user).toEqual(userObj)
    });


});