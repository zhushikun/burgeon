<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>玩转spring boot——简单登录认证</title>
    <script src="//cdn.bootcss.com/angular.js/1.5.6/angular.min.js"></script>
    <script type="text/javascript">
        /*<![CDATA[*/
        var app = angular.module('app', []);
        app.controller('MainController', function($rootScope, $scope, $http) {
            $scope.message = '';
            $scope.account = '';
            $scope.password = '';
            //登录
            $scope.login = function() {
                $scope.message = '';
                $http(
                        {
                            url : '/loginPost',
                            method : 'POST',
                            headers : {
                                'Content-Type' : 'application/x-www-form-urlencoded'
                            },
                            data : 'account=' + $scope.account + '&password='
                            + $scope.password
                        }).success(function(r) {
                    if (!r.success) {
                        $scope.message = r.message;
                        return;
                    }
                    window.location.href = '/';
                });
            }
        });
        /*]]>*/
    </script>
</head>
<body ng-app="app" ng-controller="MainController">
<h1>玩转spring boot——登录demo</h1>

<table cellspacing="1" style="background-color: #a0c6e5">
    <tr>
        <td>账号：</td>
        <td><input ng-model="account" /></td>
    </tr>
    <tr>
        <td>密码：</td>
        <td><input type="password" ng-model="password" /></td>
    </tr>
</table>
<input type="button" value="登录" ng-click="login()" />
<br />
<font color="red" ng-show="message">{{message}}</font>
<br />

<a href="/logout">注销</a>
<img src="/picture/20170820.jpg">
</body>
</html>
