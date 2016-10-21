/**
 * Navigator Bar Controller for staff
 *
 * @author Andrii Blyznuk
 */

app
    .controller('NavBarStaffController', function ($scope, $state) {
        $scope.currentNavItem = $state.current.data.selectedTab;
    });
