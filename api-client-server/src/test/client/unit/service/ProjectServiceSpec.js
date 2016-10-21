describe('projectService', function () {
    var httpBackend,
        $injector,
        projectService,
        projectObj = {
            id: 1,
            name: "Test name",
            area: "Test area",
            startDate: new Date(),
            dueDate: new Date(),
            customer: "Test customer",
            productOwner: "Test productOwner",
            scrumMaster: "Test scrumMaster",
            tasks: [{}],
        };

    beforeEach(function () {
        module("ERP");
        inject(function ($httpBackend, _$injector_) {
            httpBackend = $httpBackend;
            $injector = _$injector_;
            projectService = $injector.get( 'projectService' );

            httpBackend.whenGET('template/404.html').respond(200);

            httpBackend.flush();
        });
    });

    afterEach(function() {
        httpBackend.verifyNoOutstandingExpectation();
        httpBackend.verifyNoOutstandingRequest();
    });

    it("Test function get list projects in project service", function () {
        httpBackend.expect('GET', '/api/project').respond(200, [projectObj, projectObj]);
        projectService.getProjects().then(function (answer) {
           expect(answer).toEqual([projectObj, projectObj])
        });
        expect(httpBackend.flush).not.toThrow();
    });

    it("Test function save projects in project service", function () {
        httpBackend.expect('POST', '/api/project').respond(200, projectObj);
        projectService.saveProject(projectObj).then(function (answer) {
            expect(answer).toEqual(projectObj)
        });
        expect(httpBackend.flush).not.toThrow();
    });



});