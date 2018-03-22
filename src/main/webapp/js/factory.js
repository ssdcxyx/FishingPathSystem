function AuthService($http,Session) {
    let authService = {};

    authService.login = function (credentials) {
        return $.post("userLogin",credentials,function (data,statusText) {
                if(data.exception){
                    alert("与服务器交互出现异常："+data.exception)
                }else{
                    if(!!data){
                        Session.create(data.id,
                            data.type);
                        return data;
                    }else{
                        // 反馈账号和密码错误
                        $('#password').get(0).setCustomValidity("登录名或密码错误");
                        $('#password').get(0).reportValidity();
                    }
                }
            });
    };

    authService.isAuthenticated = function () {
        return !!Session.userId;
    };

    authService.isAuthorized = function (authorizedRoles) {
        if (!angular.isArray(authorizedRoles)) {
            authorizedRoles = [authorizedRoles];
        }
        return (authService.isAuthenticated() &&
            authorizedRoles.indexOf(Session.userRole) !== -1);
    };
    return authService;
}

angular
    .module('fishing-path')
    .factory('AuthService',['$http','Session',AuthService])