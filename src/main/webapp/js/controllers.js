// 主控制器
function ApplicationController($rootScope, $scope, $state, AuthService) {
    $scope.currentUser = null;
    $scope.user = AuthService.user;
    $scope.userRoles = AuthService.userRoles;
    $scope.accessLevels = AuthService.accessLevels;

    $scope.logout = function() {
        AuthService.logout(function() {
            $location.path('/login');
        }, function() {
            $rootScope.error = "Failed to logout";
        });
    };
}

function UserLoginCtrl($rootScope, $scope, $state, $location, $window, AuthService) {

    $scope.login = function() {
        AuthService.login({
                username: $scope.username,
                password: $scope.password
            },
            function(res) {
                $location.path('/');
                // 因为使用jqeury.post,所以需要刷新界面
                $scope.$apply();
            },
            function(err) {
                $rootScope.error = "Failed to login";
            });
    };
    return false;
};

function UserRegisterCtrl($rootScope, $scope, $location, AuthService,REG_CONSTANT) {

    $scope.validateTelephone = REG_CONSTANT.telephonePattern;
    $scope.validateAuthCode = REG_CONSTANT.authCodePattern;

    $scope.role = AuthService.userRoles.user;
    $scope.userRoles = AuthService.userRoles;

    $scope.register = function() {
        AuthService.register({
                userName:$scope.userName,
                telephone:$scope.telephone,
                password:$scope.password,
                msgId:$scope.msgId,
                authCode:$scope.authCode
            },
            function() {
                $location.path('/');
                $scope.$apply();
            },
            function(err) {
                $rootScope.error = err;
            });
    };
    return false;
}



angular
    .module('fishing-path')
    .controller('ApplicationController',['$rootScope', '$scope', '$location', 'AuthService',
        ApplicationController])
    .controller('UserLoginCtrl',['$rootScope', '$scope', '$state','$location', '$window', 'AuthService',
        UserLoginCtrl])
    .controller('UserRegisterCtrl',['$rootScope', '$scope', '$location', 'AuthService','REG_CONSTANT',
        UserRegisterCtrl])