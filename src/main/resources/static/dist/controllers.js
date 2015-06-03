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
    'EmplFac', '$http', '$route', function ($scope, $routeParams, EmplFac, $http, $route) {
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


        $scope.add = function () {
            $scope.selectedEmpl = null;
            $('#emplModal').modal('show');
        }

        $scope.edit = function (empl) {
            $scope.selectedEmpl = {id: empl.id, name: empl.name, department: {id: empl.department.id}};
            $('#emplModal').modal('show');
        }

        $('#emplModal').modal('hide');

        $scope.save = function () {
            var empl = $scope.selectedEmpl;
            if (empl) {
                console.log('id:' + empl.id + ' name:' + empl.name + ' dptid:' + empl.department.id);
                var url = '';
                if (empl.id) {
                    url = '/employee/edit';
                } else {
                    url = '/employee/add';
                }

                $http.post(url, empl).success(function () {
                    $('#emplModal').modal('hide');
                    $('#emplModal').on('hidden.bs.modal', function (e) {
                        $route.reload();
                    })

                }).error();
            }
        }

        $scope.del = function (empl) {
            $scope.selectedEmpl = {id: empl.id, name: empl.name, department: {id: empl.department.id}};
            $('#warnModal').modal('show');
        }

        $scope.delete = function () {
            $http.get("/employee/delete/" + $scope.selectedEmpl.id).success(function () {
                $('#warnModal').modal('hide');
                $('#warnModal').on('hidden.bs.modal', function (e) {
                    $route.reload();
                })
            })
        }

    }]);

/*
 paibanControllers.controller('DefaultCtrl', function ($scope) {

 });
 */
