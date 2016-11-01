/**
 * Application router
 * Config that holds all possible routes in client-side application
 *
 * @author Sergey Petrovsky, Andrii Blyznuk, Dmitry Sheremet
 */
app
    .config(function ($stateProvider, $urlRouterProvider) {

        //Main provider
        $stateProvider

            //Main abstract State
            .state('app', {
                abstract: true,
                url: "",
                controller: 'HeaderController',
                templateUrl: 'template/header.html'
            })

            //State for root URL
            .state('app.main', {
                url: "/",
                controller: 'AuthorizationController',
                templateUrl: 'template/main.html'
            })
            
            //Abstract State for User
            .state('app.user', {
                abstract: true,
                url: "^/user",
                template: '<ui-view/>'
            })
            
            //State for showing staff page
            .state('app.staff', {
                abstract: true,
                url: "^/staff",
                template: '<ui-view/>'
            })
            .state('app.staff.navBar', {
                url: "",
                controller: 'NavBarStaffController',
                templateUrl: 'template/nav.bar.staff.html'
            })
            .state('app.staff.navBar.people', {
                url: "/people",
                controller: 'PeopleStaffController',
                templateUrl: 'template/people.staff.html',
                data: {'selectedTab': 'people'}
            })
            .state('app.staff.navBar.departments', {
                url: "/departments",
                controller: 'DepartmentsStaffController',
                templateUrl: 'template/departments.staff.html',
                data: {'selectedTab': 'departments'}
            })
            .state('app.staff.navBar.vacancy', {
                url: "/vacancy",
                controller: 'VacancyStaffController',
                templateUrl: 'template/vacancy.staff.html',
                data: {'selectedTab': 'vacancy'}
            })

            //State for creating user
            .state('app.user.create', {
                url: "/create/{userId}",
                controller: 'UserEditController',
                templateUrl: 'template/user.create.html'
            })
            
            //State for showing user
            .state('app.user.show', {
                url: "/show/{userID:[0-9]}",
                controller: 'UserShowController',
                templateUrl: 'template/user.show.html'
            })

            //State for showing departments's list
            .state('app.departments', {
                url: "^/departments",
                controller: 'DepartmentController',
                templateUrl: 'template/department.list.html'
            })

            .state('app.projects', {
                url: "/projects",
                controller: 'ProjectsController',
                templateUrl: 'template/projects.html'
            })


            .state('app.projects.task', {
                url: "/task",
                templateUrl: 'template/projects.task.sidebar.html'
            })

            .state('app.projects.task.sprint', {
                url: "/sprint",
                controller: 'ProjectsTaskSprintController',
                templateUrl: 'template/projects.task.sprint.html'
            })

            .state('app.projects.task.backlog', {
                url: "/backlog",
                controller: 'ProjectsTaskBacklogController',
                templateUrl: 'template/projects.task.backlog.html'
            })

            .state('app.projects.task.request', {
                url: "/request",
                controller: 'ProjectsTaskRequestController',
                templateUrl: 'template/projects.task.request.html'
            })

            .state('app.projects.info', {
                url: "/info",
                controller: 'ProjectsInfoController',
                templateUrl: 'template/projects.info.html'
            })

            //Temporary routes for emulation superAdmin
            .state('app.user.superAdmin', {
                url: "/superAdmin",
                controller: 'superAdminController',
                templateUrl: 'template/super.admin.html'
            });

        //Root abstract provider for errors
        $stateProvider
            .state('error', {
                abstract: true,
                url: "/error",
                template: '<ui-view/>'
            })

            //State for 404 error
            .state('error.404', {
                url: "^/404",
                templateUrl: 'template/404.html'
            });

        $stateProvider
            .state('email-token', {
                url: '/user/{userId:[0-9]}/check/{token}',
                controller: function ($scope, $stateParams, $http, $state) {
                    $http.get("/api/user/" + $stateParams.userId + "/check/" + $stateParams.token).then(function (response) {
                        if  (response) $state.go('app.user.create', {'userId': $stateParams.userId});
                        else $state.go('error.404');
                    });
                },
                template: '<div>Hello</div>'
            });

        $urlRouterProvider.otherwise('/404');
    });