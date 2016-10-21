describe('autocompleteService', function () {
    var httpBackend,
        $injector,
        $rootScope,
        autocompleteService;

    beforeEach(function () {
        module("ERP");
        inject(function (_$rootScope_, $httpBackend, _$injector_) {
            httpBackend = $httpBackend;
            $injector = _$injector_;
            $rootScope = _$rootScope_;
            autocompleteService = $injector.get( 'autocompleteService' );

            httpBackend.whenGET('template/404.html').respond(200);

            httpBackend.flush();
        });
    });

    afterEach(function() {
        httpBackend.verifyNoOutstandingExpectation();
        httpBackend.verifyNoOutstandingRequest();
    });

    it("Test function getAutocompleteDataFromServer in autocomplete service", function () {
        httpBackend.expect('GET', '/api/autocomplete/responsible').respond(200, [{id: 1, name: "Test responsible 1"}, {id: 2, name: "Test responsible 2"}]);
        autocompleteService.getAutocompleteDataFromServer("responsible");
        expect(httpBackend.flush).not.toThrow();
        expect($rootScope.autocomplete.responsible).toEqual([{id: 1, name: "Test responsible 1"}, {id: 2, name: "Test responsible 2"}]);
    });

});