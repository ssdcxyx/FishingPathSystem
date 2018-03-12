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
})();


