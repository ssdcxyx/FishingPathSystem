// 设置jQuery发送Ajax的全局选项
jQuery.ajaxSetup({
    error:function (xhr,textStatus,error) {
        alert("服务器交互出现异常，错误信息："+textStatus);
    }
});
(function () {
    angular.module('fishing-path', [
        'ui.router',                    // Routing
        'oc.lazyLoad',                  // ocLazyLoad
        'ui.bootstrap',                 // Ui Bootstrap
    ])
        // 常量
        .constant('REG_CONSTANT',{
            // 正则表达式
            telephonePattern:'^[1][3,4,5,7,8][0-9]{9}$',
            authCodePattern: '^[0-9]{6}',
        })
        .constant('CODE_CONSTANT',{
            // 返回码
            SUCCESS:'SUCCESS',
            ERROR:'ERROR',
            LOSE:'LOSE',
            NULL:'NULL',
            REPEAT:'REPEAT',
            TRIED:'TRIED',
            isExist:'isExist'
        })
        .constant('AUTH_EVENTS', {
            loginSuccess: 'auth-login-success',
            loginFailed: 'auth-login-failed',
            logoutSuccess: 'auth-logout-success',
            sessionTimeout: 'auth-session-timeout',
            notAuthenticated: 'auth-not-authenticated',
            notAuthorized: 'auth-not-authorized'
        })
        .constant('USER_ROLES', {
            all: '*',
            enterprise: 'enterprise',
            admin: 'admin',
            staff:'staff',
            guest: 'guest'
        })

})();


