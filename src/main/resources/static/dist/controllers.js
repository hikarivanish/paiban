'use strict';

/* Controllers */

var paibanControllers = angular.module('paibanControllers', []);


paibanControllers.controller('EmployeeCtrl', ['$scope', '$routeParams',
    'EmplFac', '$http', '$route', function ($scope, $routeParams, EmplFac, $http, $route) {
        var emplCtrl = this;
        $http.get("/department").success(function (data) {
            $scope.allDepartment = data;
        });

        EmplFac.get({
            page: $routeParams.page,
            size: 12
        }, function (data) {
            data.content.forEach(function (empl) {
                for (var i in $scope.allDepartment) {
                    if ($scope.allDepartment[i].id == empl.departmentId) {
                        empl.deptName = $scope.allDepartment[i].name;
                        break;
                    }
                }
            });
            $scope.emplPage = data;
        });

        $scope.add = function () {
            $scope.selectedEmpl = null;
            $('#emplModal').modal('show');
        }

        $scope.edit = function (empl) {
            console.log('edit>' + empl);
            $scope.selectedEmpl = JSON.parse(JSON.stringify(empl));
            $('#emplModal').modal('show');
        }


        $scope.save = function () {
            var empl = $scope.selectedEmpl;
            if (empl) {
                console.log(empl);
                var url = '/employee/' + (empl.id ? 'edit' : 'add');
                $http.post(url, empl).success(function () {
                    $('#emplModal').modal('hide');
                    $('#emplModal').on('hidden.bs.modal', function (e) {
                        $route.reload();
                    });

                }).error(function () {
                    alert('save failed.');
                });
            }
        }

        $scope.del = function (empl) {
            $scope.selectedEmpl = JSON.parse(JSON.stringify(empl));
            $('#warnModal').modal('show');
        }

        $scope.delete = function () {
            $http.get("/employee/delete/" + $scope.selectedEmpl.id).success(function () {
                $('#warnModal').modal('hide');
                $('#warnModal').on('hidden.bs.modal', function (e) {
                    $route.reload();
                }).error(function () {
                    alert('delete failed');
                });
            })
        }

        $scope.downloadTemplate = function () {
            var ranges = [];
            /* original data */
            var data = [
                ['姓名', '部门', '邮箱', '是否排班'],
                ['张三', 'taobao', 'fs@163.com', 'Y'],
                ['李四', 'xiami', 'f543@qq.com', 'N'],
                ['王五', 'zhifubao', 'fj3@fds.me', 'Y']
            ];
            var ws_name = "SheetJS";
            console.log(data);
            var wb = new Workbook(), ws = sheet_from_array_of_arrays(data);
            /* add ranges to worksheet */
            ws['!merges'] = ranges;
            /* add worksheet to workbook */
            wb.SheetNames.push(ws_name);
            wb.Sheets[ws_name] = ws;

            var wbout = XLSX.write(wb, {bookType: 'xlsx', bookSST: false, type: 'binary'});

            saveAs(new Blob([s2ab(wbout)], {type: "application/octet-stream"}), "test.xlsx")
        }


        $scope.import = function () {
            $('#xlsFile').click();
        }
        $('#xlsFile').on('change', function (e) {
            var files = e.target.files;
            var i, f;
            for (i = 0, f = files[i]; i != files.length; ++i) {
                var reader = new FileReader();
                var name = f.name;
                reader.onload = function (e) {
                    var data = e.target.result;
                    var workbook = XLSX.read(data, {type: 'binary'});
                    /* DO SOMETHING WITH workbook HERE */
                    var empls = [];
                    var empl = {};
                    var worksheet = workbook.Sheets[workbook.SheetNames[0]];
                    for (var z in worksheet) {
                        /* all keys that do not begin with "!" correspond to cell addresses */
                        if (z[0] === '!' || z[1] === '1') continue;
                        if (z[0] === 'A') {
                            empl.name = worksheet[z].v;
                        } else if (z[0] === 'B') {
                            empl.departmentName = worksheet[z].v;
                            for (var dept in $scope.allDepartment) {
                                if ($scope.allDepartment[dept].name == empl.departmentName) {
                                    empl.departmentId = $scope.allDepartment[dept].id;
                                    break;
                                }
                            }
                        } else if (z[0] == 'C') {
                            empl.email = worksheet[z].v;
                        } else if (z[0] == 'D') {
                            empl.inSchedule = (worksheet[z].v == 'Y');
                            empls.push(JSON.parse(JSON.stringify(empl)));
                        }
                    }
                    console.log(empls);
                    $scope.checkEmpls(empls);
                };
                reader.readAsBinaryString(f);
            }
        });


        $scope.batchEmpls = [];
        $scope.checkEmpls = function (empls) {
            $scope.$apply(function () {
                $scope.batchEmpls = empls;
            })
            $('#batchModal').modal('show');
        }

        $scope.batchSave = function () {
            console.log($scope.batchEmpls);
            $http.post("/employee/batchAdd", $scope.batchEmpls).success(function () {
                $('#batchModal').modal('hide');
                $('#batchModal').on('hidden.bs.modal', function (e) {
                    $route.reload();
                });
            }).error(function () {
                alert('save failed.');
            });


        }

    }]);

