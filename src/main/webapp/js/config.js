function config($stateProvider, $urlRouterProvider, $locationProvider, $httpProvider,$ocLazyLoadProvider) {

    var access = routingConfig.accessLevels;

    // 公共路由
    $stateProvider

        .state('public', {
            abstract: true,
            template: "<ui-view/>",
            data: {
                access: access.public
            }
        })
        .state('public.404', {
            url: '/404/',
            templateUrl: 'res/404.html'
        });

    // 访客路由
    $stateProvider
        .state('anon', {
            abstract: true,
            template: "<ui-view/>",
            data: {
                access: access.anon
            }
        })
        .state('anon.login', {
            url: "/login/",
            controller:UserLoginCtrl,
            templateUrl: "res/views/login.html",
            data: { pageTitle: '登录', specialClass: 'gray-bg' }
        })
        .state('anon.register', {
            url: "/register/",
            controller:UserRegisterCtrl,
            templateUrl: "res/views/register.html",
            data: { pageTitle: '注册', specialClass: 'gray-bg' }
        });

    // 员工路由
    $stateProvider
        .state('index', {
            abstract: true,
            templateUrl: "res/views/common/content.html",
            data: {
                access: access.staff
            }
        })
        .state('index.main', {
            url: "/",
            templateUrl: "res/views/main.html"
        })
        .state('index.minor', {
            url: "/minor/",
            templateUrl: "res/views/minor.html",
            data: { pageTitle: 'Example view' }
        });

    // 管理员路由
    $stateProvider
        .state('admin', {
            abstract: true,
            template: "<ui-view/>",
            data: {
                access: access.admin
            }
        })
        .state('admin.admin', {

        });

    // 指定默认地址
    $urlRouterProvider.otherwise("/404");

    $ocLazyLoadProvider.config({
        // 设置是否查看动态加载的内容和时间
        debug: false
    });

    // 解决可能存在的"/"问题
    $urlRouterProvider.rule(function($injector, $location) {
        if($location.protocol() === 'file')
            return;

        var path = $location.path()
            // 返回一个查询对象
            , search = $location.search()
            , params
        ;

        if (path[path.length - 1] === '/') {
            return;
        }

        if (Object.keys(search).length === 0) {
            return path + '/';
        }
        params = [];
        angular.forEach(search, function(v, k){
            params.push(k + '=' + v);
        });
        return path + '/?' + params.join('&');
    });

    $httpProvider.interceptors.push(function($q, $location) {
        return {
            'responseError': function(response) {
                if(response.status === 401 || response.status === 403) {
                    $location.path('/login');
                }
                return $q.reject(response);
            }
        };
    });
}

function run($rootScope, $state, AuthService) {

    // 获得全局的state
    $rootScope.$state = $state;

    // 监听路由改变事件
    $rootScope.$on("$stateChangeStart", function (event, toState, toParams, fromState, fromParams) {

        if(!('data' in toState) || !('access' in toState.data)){
            $rootScope.error = "访问状态未定义";
            event.preventDefault();
        }
        else if (!AuthService.authorize(toState.data.access)) {
            $rootScope.error = "你没有访问的权限.";
            event.preventDefault();

            if(fromState.url === '^') {
                if(AuthService.isLoggedIn()) {
                    $state.go('index.main');
                } else {
                    $rootScope.error = null;
                    $state.go('anon.login');
                }
            }
        }
    });

}

angular
    .module('fishing-path')
    .config(['$stateProvider', '$urlRouterProvider', '$locationProvider', '$httpProvider','$ocLazyLoadProvider',
        config])
    .run(['$rootScope', '$state', 'AuthService',
        run]);
