/**
 * Projects task Controller
 *
 * @author Kulinenko Roman, Sem Babenko
 */
app.controller('ProjectsTaskController', function ($scope, serverService, dragulaService) {
//   TODO: Need refactoring
//    dragulaService.options($scope, 'first-bag', {
//        removeOnSpill: true
//    });

//    $scope.currentNavItem = 'Tasks';
//    $scope.project = {
//        filter : 'All filters'
//    };
//    $scope.names = ('Booking Twaddle Perry').split(' ').map(function(name) {
//        return {projects: name};
//    });
//
//    $scope.boards = ('Kanban dashboards1 dashboards2').split(' ').map(function(board) {
//        return {projects: board};
//    });
//
//    $scope.filters = ('All filters, Only My Issues, Recently Update').split(', ').map(function(filter) {
//        return {projects: filter};
//    });
//
//    var imagePath = 'https://material.angularjs.org/latest/img/list/60.jpeg';
//    $scope.messages = [
//        {
//            face : imagePath,
//            what: 'Brunch this weekend?',
//            who: 'Min Li Chan',
//            when: '3:08PM',
//            notes: " I'll be in your neighborhood doing errands1"
//        },
//        {
//            face : imagePath,
//            what: 'Brunch this weekend?',
//            who: 'Min Li Chan',
//            when: '3:08PM',
//            notes: " I'll be in your neighborhood doing errands2"
//        },
//        {
//            face : imagePath,
//            what: 'Brunch this weekend?',
//            who: 'Min Li Chan',
//            when: '3:08PM',
//            notes: " I'll be in your neighborhood doing errands3"
//        },
//        {
//            face : imagePath,
//            what: 'Brunch this weekend?',
//            who: 'Min Li Chan',
//            when: '3:08PM',
//            notes: " I'll be in your neighborhood doing errands4"
//        },
//        {
//            face : imagePath,
//            what: 'Brunch this weekend?',
//            who: 'Min Li Chan',
//            when: '3:08PM',
//            notes: " I'll be in your neighborhood doing errands5"
//        }
//    ];

    $scope.taskSettings = [
        {
            icon: "home",
            name: "Backlog",
            href: "app.projects.task.backlog"
        },
        {
            icon: "home",
            name: "Active sprints",
            href: "app.projects.task.sprint"
        },
        {
            icon: "home",
            name: "Releases",
            href: "app.projects"
        },
        {
            icon: "home",
            name: "Statistics",
            href: "app.projects"
        },
        {
            icon: "home",
            name: "Requests",
            href: "app.projects"

        },
        {
            icon: "home",
            name: "Components",
            href: "app.projects"
        },
        {
            icon: "home",
            name: "Settings",
            clazz: "project-settings",
            href: "app.projects"
        },
    ];

    $scope.filterTasks = function(name){
        console.log(name);
    };
//
//    $scope.stories = [
//        {
//            name: "DEN-2 Как суперадмін хочу просматривать все розговори менеджеров",
//            rows: [
//                {
//                    columns: [
//                        {
//                            title: "Вивчення можливостей по інтеграції Skype до CRM1",
//                            face : imagePath
//                        },
//                        {
//                            title: "Вивчення можливостей по інтеграції Skype до CRM2",
//                            face : imagePath
//
//                        },
//                        {
//                            title: "Вивчення можливостей по інтеграції Skype до CRM3",
//                            face : imagePath
//
//                        },
//                        {
//                            title: "Вивчення можливостей по інтеграції Skype до CRM4",
//                            face : imagePath
//                        }
//                    ]
//                },
//                {
//                    columns: [
//                        {
//                            title: "Вивчення можливостей по інтеграції Skype до CRM1",
//                            face : imagePath
//                        },
//                        {
//                            title: "Вивчення можливостей по інтеграції Skype до CRM2",
//                            face : imagePath
//
//                        },
//                        {
//                            title: "Вивчення можливостей по інтеграції Skype до CRM3",
//                            face : imagePath
//
//                        },
//                        {
//                            title: "Вивчення можливостей по інтеграції Skype до CRM4",
//                            face : imagePath
//                        }
//                    ]
//                }
//            ]
//        },
//        {
//            name: "DEN-5 Как суперадмін хочу просматривать все розговори менеджеров",
//            rows: [
//                {
//                    columns: [
//                        {
//                            title: "Вивчення можливостей по інтеграції Skype до CRM!!!",
//                            face : imagePath
//                        },
//                        {
//                            title: "Вивчення можливостей по інтеграції Skype до CRM!",
//                            face : imagePath
//
//                        },
//                        {
//                            title: "Вивчення можливостей по інтеграції Skype до CRM!",
//                            face : imagePath
//
//                        },
//                        {
//                            title: "Вивчення можливостей по інтеграції Skype до CRM!",
//                            face : imagePath
//                        }
//                    ]
//                },
//                {
//                    columns: [
//                        {
//                            title: "Вивчення можливостей по інтеграції Skype до CRM!!!",
//                            face : imagePath
//                        },
//                        {
//                            title: "Вивчення можливостей по інтеграції Skype до CRM!",
//                            face : imagePath
//
//                        },
//                        {
//                            title: "Вивчення можливостей по інтеграції Skype до CRM!",
//                            face : imagePath
//
//                        },
//                        {
//                            title: "Вивчення можливостей по інтеграції Skype до CRM!",
//                            face : imagePath
//                        }
//                    ]
//                },
//                {
//                    columns: [
//                        {
//                            title: "Вивчення можливостей по інтеграції Skype до CRM!!!",
//                            face : imagePath
//                        },
//                        {
//                            title: "Вивчення можливостей по інтеграції Skype до CRM!",
//                            face : imagePath
//
//                        },
//                        {
//                            title: "Вивчення можливостей по інтеграції Skype до CRM!",
//                            face : imagePath
//
//                        },
//                        {
//                            title: "Вивчення можливостей по інтеграції Skype до CRM!",
//                            face : imagePath
//                        }
//                    ]
//                }
//            ]
//        },
//        {
//            name: "DEN-10 Как суперадмін хочу просматривать все розговори менеджеров",
//            rows: [
//                {
//                    columns: [
//                        {
//                            title: "Вивчення можливостей по інтеграції Skype до CRM!!!",
//                            face : imagePath
//                        },
//                        {
//                            title: "Вивчення можливостей по інтеграції Skype до CRM!",
//                            face : imagePath
//
//                        },
//                        {
//                            title: "Вивчення можливостей по інтеграції Skype до CRM!",
//                            face : imagePath
//
//                        },
//                        {
//                            title: "Вивчення можливостей по інтеграції Skype до CRM!",
//                            face : imagePath
//                        }
//                    ]
//                }
//            ]
//        },
//        {
//            name: "DEN-20 Как суперадмін хочу просматривать все розговори менеджеров",
//            rows: [
//                {
//                    columns: [
//                        {
//                            title: "Вивчення можливостей по інтеграції Skype до CRM!!!",
//                            face : imagePath
//                        },
//                        {
//                            title: "Вивчення можливостей по інтеграції Skype до CRM!",
//                            face : imagePath
//
//                        },
//                        {
//                            title: "Вивчення можливостей по інтеграції Skype до CRM!",
//                            face : imagePath
//
//                        },
//                        {
//                            title: "Вивчення можливостей по інтеграції Skype до CRM!",
//                            face : imagePath
//                        }
//                    ]
//                },
//                {
//                    columns: [
//                        {
//                            title: "Вивчення можливостей по інтеграції Skype до CRM!!!",
//                            face : imagePath
//                        },
//                        {
//                            title: "Вивчення можливостей по інтеграції Skype до CRM!",
//                            face : imagePath
//
//                        },
//                        {
//                            title: "Вивчення можливостей по інтеграції Skype до CRM!",
//                            face : imagePath
//
//                        },
//                        {
//                            title: "Вивчення можливостей по інтеграції Skype до CRM!",
//                            face : imagePath
//                        }
//                    ]
//                }
//            ]
//        }
//    ];
//
//    $scope.columns = [
//        {
//            name: "To implement (9)"
//        },
//        {
//            name: "In progress (15)"
//        },
//        {
//            name: "Ready to test (2)"
//        },
//        {
//            name: "Done (14)"
//        }
//    ];
//
//    $scope.flexColumn = 100 / $scope.columns.length;

//    $scope.$on('second-bag.drag', function (e, el) {
//        el.removeClass('ex-moved');
//    });
//
//    $scope.$on('second-bag.drop', function (e, el) {
//        el.addClass('ex-moved');
//    });
//
//    $scope.$on('second-bag.over', function (e, el, container) {
//        container.addClass('ex-over');
//    });
//
//    $scope.$on('second-bag.out', function (e, el, container) {
//        container.removeClass('ex-over');
//    });

    $scope.oneAtATime = true;
});
