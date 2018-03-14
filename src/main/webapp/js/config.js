function config($stateProvider, $urlRouterProvider, $ocLazyLoadProvider) {
    // 指定默认地址
    $urlRouterProvider.otherwise("/login");

    $ocLazyLoadProvider.config({
        // 设置是否查看动态加载的内容和时间
        debug: false
    });

    $stateProvider

        .state('index', {
            abstract: true,
            url: "/index",
            templateUrl: "res/views/common/content.html",
        })
        .state('index.main', {
            url: "/main",
            templateUrl: "res/views/main.html",
            data: { pageTitle: '首页' }
        })
        .state('index.minor', {
            url: "/minor",
            templateUrl: "res/views/minor.html",
            data: { pageTitle: 'Example view' }
        })
        .state('login', {
            url: "/login",
            controller:UserLoginCtrl,
            templateUrl: "res/views/login.html",
            data: { pageTitle: '登录', specialClass: 'gray-bg' }
        })
        .state('register', {
            url: "/register",
            templateUrl: "res/views/register.html",
            data: { pageTitle: '注册', specialClass: 'gray-bg' }
        })
}
angular
    .module('fishing-path')
    .config(config)
    .run(function($rootScope, $state) {
        $rootScope.$state = $state;
    });
