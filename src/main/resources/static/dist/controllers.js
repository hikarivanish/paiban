'use strict';

/* Controllers */

var paibanControllers = angular.module('paibanControllers', []);


/*
 paibanControllers.controller('UserCtrl', ['$scope', '$http', '$routeParams',
 function ($scope, $http, $routeParams) {
 var User = this;
 $http.get("/user/channels").success(function (data) {
 User.channels = data;
 User.channelId = $routeParams.channelId;
 });
 }]);

 */

paibanControllers.controller('EmployeeCtrl', ['$scope', '$routeParams',
    'EmplFac', '$http', function ($scope, $routeParams, EmplFac, $http) {
        var emplCtrl = this;
        EmplFac.get({
            page: $routeParams.page,
            size: 12
        }, function (data) {
            $scope.emplPage = data;
        });

        $http.get("/employee/departments").success(function (data) {
            $scope.allDepartment = data;
        });


        $scope.edit = function (empl) {
            $scope.selectedEmpl = empl;
            $('#emplModal').modal('show');
        }
    }]);

/*
 paibanControllers.controller('DefaultCtrl', function ($scope) {

 });
 */
