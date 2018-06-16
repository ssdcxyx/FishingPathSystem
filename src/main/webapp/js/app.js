// 设置jQuery发送Ajax的全局选项
jQuery.ajaxSetup({
    error:function (xhr,textStatus,error) {
        SweetAlert.swal({
            title:"网络请求异常",
            text:"向服务器发送请求时出现异常："+textStatus,
            type:"error"
        })
    }
});
(function () {
    angular.module('fishing-path', [
        'ui.router',                    // Routing
        'ngCookies',                    // cookies
        'oc.lazyLoad',                  // ocLazyLoad
        'ui.bootstrap',                 // Ui Bootstrap
        'pascalprecht.translate',       // Angular Translate
        'ngIdle',                       // Idle timer
        'oitozero.ngSweetAlert',
        'ngSanitize',
        'toaster',
        'ngAnimate'
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
        // 过滤器
        .filter('minute',function(){
            // 分
            return function(input){
                var index = input.indexOf('.');
                if(index>=0){
                    input = input.substring(0,index);
                }
                if(input.length==1){
                    return "0"+input;
                }else if(input.length==0){
                    return "00";
                } else{
                    return input;
                }
            }
        })
        .filter('second',function(){
            // 秒
            return function(input){
                if(input.length==1){
                    return "0"+input;
                }else if(input.length==0){
                    return "00";
                } else{
                    return input;
                }
            }
        })
        .filter('chineseNumber',function(){
            // 阿拉巴数字转中文
            return function (input) {
                var array=["零","一","二","三","四","五","六","七","八","九","十"];
                var result = '';
                if(input!=''){
                    var atr=input.replace(/(.)(?=[^$])/g,"$1,").split(",");
                    atr.forEach(function(e){
                        result += array[e];
                    });
                }
                return result;
            }
        })
        // 转化为html
        .filter('trustHtml', function ($sce) {
            return function (input) {
                return $sce.trustAsHtml(input);
            }
        });

})();


