/**
 * People Controller on client
 *
 * @author Andrii Blyznuk
 */
app
    .controller('PeopleStaffController', function ($scope, userService, UserTypeEnum) {

        //The method we obtain a list of users and immediately
        // filter it to see how many users have certain types
        userService.getUsers()
            .then(function (answer) {
                $scope.users = answer;
                $scope.EMPLOYEE = 0;
                $scope.RELEASED = 0;
                $scope.CANDIDATE = 0;
                $scope.CUSTOMER = 0;
                angular.forEach($scope.users, function (user) {
                    if(user.type == UserTypeEnum.EMPLOYEE) $scope.EMPLOYEE += 1;
                    if(user.type == UserTypeEnum.RELEASED) $scope.RELEASED += 1;
                    if(user.type == UserTypeEnum.CANDIDATE) $scope.CANDIDATE += 1;
                    if(user.type == UserTypeEnum.CUSTOMER) $scope.CUSTOMER += 1;
                });
            });

        //The method filterByType is called at people.staff.html
        //and dynamically filter on it when users
        //clicks on their specific type
        //Returns boolean
        $scope.filterByType = function(item){
            if (!$scope.typeUser
                || (item.type.toLowerCase().indexOf($scope.typeUser.toLowerCase()) != -1)){
                return true;
            }
            return false;
        };


        //The method search follows the search page
        //and dynamically filter people.staff.html
        //vacancies on the name, position, email, phone the
        //user enters in the search.
        //Returns boolean
        $scope.search = function(item){
            if (!$scope.searchText
                || (item.name.toLowerCase().indexOf($scope.searchText.toLowerCase()) != -1)
                || (item.position.toLowerCase().indexOf($scope.searchText.toLowerCase()) != -1)
                || (item.email.toLowerCase().indexOf($scope.searchText.toLowerCase()) != -1)
                || (item.phone.toLowerCase().indexOf($scope.searchText.toLowerCase()) != -1)) {
                return true;}
            return false;
        };
        

    });