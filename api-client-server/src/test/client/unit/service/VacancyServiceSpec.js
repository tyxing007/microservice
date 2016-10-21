describe('vacancyService', function () {
    var httpBackend,
        $injector,
        vacancyService,
        vacancyObj = {
            id: 1,
            name: "Test Name",
            date: new Date(),
        };

    beforeEach(function () {
        module("ERP");
        inject(function ($httpBackend, _$injector_) {
            httpBackend = $httpBackend;
            $injector = _$injector_;
            vacancyService = $injector.get( 'vacancyService' );

            httpBackend.whenGET('template/404.html').respond(200);

            httpBackend.flush();
        });
    });

    afterEach(function() {
        httpBackend.verifyNoOutstandingExpectation();
        httpBackend.verifyNoOutstandingRequest();
    });

    it("Test function create vacancy in vacancy service", function () {
        httpBackend.expectPOST('/api/vacancy/create', vacancyObj).respond(200, vacancyObj);
        vacancyService.createVacancy(vacancyObj).then(function (answer) {
            var response = answer.data;
            expect(response).toEqual(vacancyObj);
        });
        expect(httpBackend.flush).not.toThrow();

    });

    it("Test function get list vacancy in vacancy service", function () {
        httpBackend.expectGET('/api/vacancies').respond(200, [vacancyObj, vacancyObj]);
        vacancyService.getVacancies().then(function(answer) {
            expect(answer).toEqual([vacancyObj, vacancyObj]);
        });
        expect(httpBackend.flush).not.toThrow();
    });


});