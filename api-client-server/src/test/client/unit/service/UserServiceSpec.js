describe('userService', function () {
    var httpBackend,
        $injector,
        userService,
        userObj = {
            id: 1,
            name: "Test Name",
            lastName: "Test lastName",
            email: "Test email"
        },
        file = new File([""], "testFile");

    beforeEach(function () {
        module("ERP");
        inject(function ($httpBackend, _$injector_) {
            httpBackend = $httpBackend;
            $injector = _$injector_;
            userService = $injector.get( 'userService' );

            httpBackend.whenGET('template/404.html').respond(200);

            httpBackend.flush();
        });
    });

    afterEach(function() {
        httpBackend.verifyNoOutstandingExpectation();
        httpBackend.verifyNoOutstandingRequest();
    });

    it("Test function get user list in user service", function () {
        httpBackend.expect('GET', '/api/users').respond(200, [userObj, userObj]);
        userService.getUsers().then(function (answer) {
            expect(answer).toEqual([userObj, userObj]);
        });
        expect(httpBackend.flush).not.toThrow();
    });

    it("Test function get user by id in user service", function () {
        httpBackend.expect('GET', '/api/user/1').respond(200, userObj);
        userService.getUser(1).then(function (answer) {
            expect(answer).toEqual(userObj);
        });
        expect(httpBackend.flush).not.toThrow();
    });

    it("Test function upload Image in user service", function () {
        httpBackend.expect('POST', '/api/image/upload/user/1', file).respond(200);
        userService.uploadImage(file, 1).then(function (answer) {
            expect(answer.status).toBe(200);
        });
        expect(httpBackend.flush).not.toThrow();
    });

    it("Test function delete Image in user service", function () {
        httpBackend.expect('DELETE', '/api/image/delete/user/1').respond(200);
        userService.deleteImage(1).then(function (answer) {
            expect(answer.status).toBe(200);
        });
        expect(httpBackend.flush).not.toThrow();
    });

    it("Test function create user in user service", function () {
        httpBackend.expect('POST', '/api/user/create', userObj).respond(200, userObj);
        userService.createUser(userObj).then(function (answer) {
            expect(answer.data).toEqual(userObj);
            expect(answer.status).toBe(200);
        });
        expect(httpBackend.flush).not.toThrow();
    });

    it("Test function send message to user email in user service", function () {
        httpBackend.expect('POST', '/api/token/send', {email: "testEmail@example.com", type:"Test type"}).respond(200, {email: "testEmail@example.com", type:"Test type"});
        userService.sendToEmail("testEmail@example.com", "Test type").then(function (answer) {
            expect(answer.data).toEqual({email: "testEmail@example.com", type:"Test type"});
            expect(answer.status).toBe(200);
        });
        expect(httpBackend.flush).not.toThrow();
    });





});