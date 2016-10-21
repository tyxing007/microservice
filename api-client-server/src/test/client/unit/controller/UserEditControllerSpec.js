describe("UserEditController", function () {
    var $scope,
        httpBackend,
        userObj = {
            id: 1,
            name: "Test Name",
            lastName: "Test lastName",
            email: "Test email",
            photo: "Path to photo",
            skills: ["Java", "Php"]
        },
        file = new File([""], "testFile"),
        $controller;

    beforeEach(function () {
        module("ERP");
        inject(function ($httpBackend, _$controller_) {
            httpBackend = $httpBackend;

            httpBackend.expect('GET', '/api/user/{0}').respond(200, userObj);
            httpBackend.whenGET('template/404.html').respond(200);

            $scope = {};
            $controller = _$controller_('UserEditController', {$scope: $scope});
            httpBackend.flush();
        });
    });

    afterEach(function () {
        httpBackend.verifyNoOutstandingExpectation();
        httpBackend.verifyNoOutstandingRequest();
    });

    it('Test function get user by id in UserEditController', function () {
        expect($scope.user).toEqual(userObj)
    });

    it('Test function delete image from user', function () {
        httpBackend.expect('DELETE', '/api/image/delete/user/1').respond(204);
        $scope.deleteImage().then(function (answer) {
            expect(answer.status).toBe(204)
        });
        httpBackend.flush();
    });

    it('Test function save user in UserEditController', function () {
        httpBackend.expect('POST', '/api/user/create', userObj).respond(200, userObj);
        httpBackend.expect('POST', '/api/image/upload/user/1', file).respond(200);

        httpBackend.whenGET('template/404.html').respond(200);
        httpBackend.whenGET('template/header.html').respond(200);
        httpBackend.whenGET('template/main.html').respond(200);
        $scope.saveUser(file).then(function (answer) {
            expect(answer.status).toBe(200)
        });
        httpBackend.flush()
    });

    it('Test function add kit in UserEditController', function () {
        expect($scope.addKit("Java")).toEqual({name: "Java"})
    });

});