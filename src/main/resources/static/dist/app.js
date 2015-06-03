'use strict';

/* App Module */

var paibanApp = angular.module('paibanApp',
    [ 'ngRoute', 'ngSanitize', 'ngAnimate', 'paibanControllers'   ,
        'paibanServices'/*, 'readerAnimations','readerFilters' */]);

paibanApp.config([ '$routeProvider', function($routeProvider) {
    $routeProvider.when('/employee/:page', {
        templateUrl : 'partials/employee.html',
        controller : 'EmployeeCtrl'
    }).when('/employee/', {
        redirectTo:'/employee/0'
    }).otherwise({
        redirectTo:'/employee/0'
    });
} ]);
