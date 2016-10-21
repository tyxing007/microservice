describe('taskService', function () {
    var httpBackend,
        $injector,
        taskService,
        priorityObj = {
            id: 1,
            level: 'Test level'
        },
        taskObj = {
            id: 1,
            title: "Test title",
            type: "Test type"
        },
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
            taskService = $injector.get('taskService');

            httpBackend.whenGET('template/404.html').respond(200);

            httpBackend.flush();
        });
    });

    afterEach(function() {
        httpBackend.verifyNoOutstandingExpectation();
        httpBackend.verifyNoOutstandingRequest();
    });

    it("Test function get list priority in task service", function () {
        httpBackend.when('GET', '/api/task/priority').respond(200, [priorityObj, priorityObj]);
        taskService.getPriorities(priorityObj).then(function (answer) {
            expect(answer).toEqual([priorityObj, priorityObj])
        });
        expect(httpBackend.flush).not.toThrow();
    });

    it("Test function save task in task service", function () {
        httpBackend.when('POST', '/api/task', taskObj).respond(200, taskObj);
        taskService.saveTask(taskObj).then(function (answer) {
           expect(answer).toEqual(taskObj);
        });
        expect(httpBackend.flush).not.toThrow();
    });

    it("Test function set current project in task service", function () {
        expect(taskService.getProject()).toBeUndefined();
        taskService.setCurrentProject(projectObj)
        expect(taskService.getProject()).toEqual(projectObj);
    });



});