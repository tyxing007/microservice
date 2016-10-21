describe("superAdminController", function () {
    var $scope,
        UserTypeEnum,
        httpBackend,
        $controller,
        userObj = {
            id: 1,
            name: "Test Name",
            lastName: "Test lastName",
            email: "Test email"
        };

    beforeEach(function () {
        module("ERP");
        inject(function ($httpBackend, _$controller_, _UserTypeEnum_) {
            httpBackend = $httpBackend;
            UserTypeEnum = _UserTypeEnum_;
            httpBackend.whenGET('template/404.html').respond(200);

            $scope = {};
            $controller = _$controller_('superAdminController', {$scope: $scope});
            httpBackend.flush();
        });
    });

    afterEach(function () {
        httpBackend.verifyNoOutstandingExpectation();
        httpBackend.verifyNoOutstandingRequest();
    });

    it('Test function send mail to user and add this user to BD', function () {
        httpBackend.expect('POST', '/api/token/send', {email: "Andriubliznuk@mail.ru", type: UserTypeEnum.EMPLOYEE}).respond(200, userObj );
        $scope.sendToEmail("Andriubliznuk@mail.ru", UserTypeEnum.EMPLOYEE).then(function (answer) {
            expect(answer).toEqual(userObj)
        });
        httpBackend.flush();
});

});