describe("PeopleStaffController", function () {
    var $scope,
        UserTypeEnum,
        httpBackend,
        $controller,
        userObj = [{
            id: 1,
            name: "Test Name 1",
            lastName: "Test lastName 1",
            email: "Test email 1",
            type: "RELEASED"
        },{
            id: 2,
            name: "Test Name 2",
            lastName: "Test lastName 2",
            email: "Test email 2",
            type: "EMPLOYEE"
        }];

    beforeEach(function () {
        module("ERP");
        inject(function ($httpBackend, _$controller_, _UserTypeEnum_) {
            httpBackend = $httpBackend;
            UserTypeEnum = _UserTypeEnum_;

            httpBackend.whenGET('/api/users').respond(200, userObj);
            httpBackend.whenGET('template/404.html').respond(200);

            $scope = {};
            $controller = _$controller_('PeopleStaffController', {$scope: $scope});
            httpBackend.flush();
        });
    });

    afterEach(function () {
        httpBackend.verifyNoOutstandingExpectation();
        httpBackend.verifyNoOutstandingRequest();
    });

    it('Test function get user from BD and count they type in people staff controller', function () {
        expect($scope.EMPLOYEE).toBe(1);
        expect($scope.RELEASED).toBe(1);
    });

    it('Test function filter users by type in people staff controller', function () {
        $scope.typeUser = "EMPLOYEE";
        expect($scope.filterByType(userObj[1])).toBe(true);
    });

    it('Test function filter users by search in people staff controller', function () {
        $scope.searchText = '2';
        expect($scope.search(userObj[1])).toBe(true)
    });

});