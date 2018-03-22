// 主控制器
function ApplicationController($scope,$rootScope,$state,$stateParams,USER_ROLES,AuthService) {
    $scope.currentUser = null;
    $scope.userRoles = USER_ROLES;
    $scope.isAuthorized = AuthService.isAuthorized;

    $scope.setCurrentUser = function (user) {
        $scope.currentUser = user;
    };

    // 退出登录
    $scope.logout = function () {
        
    }
}

// 用户登录
function UserLoginCtrl($scope,$rootScope,$state,AUTH_EVENTS,AuthService) {
    $scope.credentials = {
        account: '',
        password: ''
    };
    $scope.userLogin = function (credentials) {
        AuthService.login(credentials).then(function (user) {
            $rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
            $scope.setCurrentUser(user);
            $state.go('index.main');
        }, function () {
            $rootScope.$broadcast(AUTH_EVENTS.loginFailed);
        });
        // $.get("userLogin",{account:$scope.account,password:$scope.password},function (data,statusText) {
        //     if(data.exception){
        //         alert("与服务器交互出现异常："+data.exception)
        //     }else{
        //         if(!!data){
        //             var user = data;
        //             $state.go("index.main",{'user':angular.toJson(user)});
        //         }else{
        //             // 反馈账号和密码错误
        //             $('#password').get(0).setCustomValidity("登录名或密码错误");
        //             $('#password').get(0).reportValidity();
        //         }
        //     }
        // });
    };
    javascript:void(0);
}

/// 用户注册
function UserRegisterCtrl($scope,REG_CONSTANT,CODE_CONSTANT) {
    $scope.validateTelephone = REG_CONSTANT.telephonePattern;
    $scope.validateAuthCode = REG_CONSTANT.authCodePattern;
    $scope.userRegister = function () {
        $.get("verifyVerificationCode",{msgId:$scope.msgId,authCode:$scope.authCode},function (data,statusText) {
            if(data.exception){
                alert("与服务器交互出现异常："+data.exception)
            }else{
                var result = data;
                if(result==CODE_CONSTANT.SUCCESS){
                    $.post("userRegister",{userName:$scope.userName,telephone:$scope.telephone,password:$scope.password,type:0},function (data,statusText) {
                        if(data.exception){
                            alert("与服务器交互出现异常："+data.exception)
                        }else{
                            var returnResult = data;
                            if(returnResult==CODE_CONSTANT.REPEAT) {
                                $('#telephone').get(0).setCustomValidity("手机号码已注册");
                                $('#telephone').get(0).reportValidity();
                            }else if(data!=""){
                                alert("用户注册成功！");
                            }
                        }
                    },"json");
                }else if(result==CODE_CONSTANT.LOSE){
                    $('#authCode').get(0).setCustomValidity("验证码已过期");
                    $('#authCode').get(0).reportValidity();
                }else if(result==CODE_CONSTANT.NULL){
                    $('#authCode').get(0).setCustomValidity("请先发送验证码");
                    $('#authCode').get(0).reportValidity();
                }else if(result==CODE_CONSTANT.ERROR){
                    // 反馈验证码错误
                    $('#authCode').get(0).setCustomValidity("验证码错误");
                    $('#authCode').get(0).reportValidity();
                }else if(result==CODE_CONSTANT.TRIED){
                    // 反馈验证码错误
                    $('#authCode').get(0).setCustomValidity("验证码已失效");
                    $('#authCode').get(0).reportValidity();
                } else{
                    $('#authCode').get(0).setCustomValidity("未知错误");
                    $('#authCode').get(0).reportValidity();
                }
            }
        })
    }
    return false; // 阻止默认的提交
}

angular
    .module('fishing-path')
    .controller('ApplicationController',['$scope','$rootScope','$state','$stateParams',
        'USER_ROLES','AuthService',ApplicationController])
    .controller('UserLoginCtrl',['$scope','$rootScope','$state','AUTH_EVENTS',
        'AuthService', UserLoginCtrl])
    .controller('UserRegisterCtrl',['$scope','REG_CONSTANT','CODE_CONSTANT',UserRegisterCtrl])