describe('stepService', function () {
    var $injector,
        httpBackend,
        stepService,
        stepObj = {
            id: null,
            name: "Test name",
            date: new Date(),
            time: new Date().getTime(),
            file: "Test file",
            vacancy: {},
            responsible: {},
            interviewer: [{}],
            marks: [{}]
        };

    beforeEach(function () {
        module("ERP");
        inject(function ($httpBackend, _$injector_) {
            httpBackend = $httpBackend;
            $injector = _$injector_;
            stepService = $injector.get('stepService');

            httpBackend.when('GET', 'template/404.html').respond(200)
            httpBackend.flush();
        });
    });

    afterEach(function () {
        httpBackend.verifyNoOutstandingExpectation();
        httpBackend.verifyNoOutstandingRequest();
    });

    it('Test function create step in step service', function () {
        httpBackend.when('POST', '/api/step', stepObj).respond(200, stepObj);
        stepService.createStep(stepObj).then(function (answer) {
            expect(stepObj).toEqual(answer.data);
            expect(answer.status).toBe(200);
        });
        httpBackend.flush();
    });

});