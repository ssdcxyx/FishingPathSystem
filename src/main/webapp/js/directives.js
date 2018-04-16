/**
 * INSPINIA - Responsive Admin Theme
 *
 */


/**
 * pageTitle - Directive for set Page title - mata title
 */
function pageTitle($rootScope, $timeout) {
    return {
        link: function(scope, element) {
            var listener = function(event, toState, toParams, fromState, fromParams) {
                // Default title - load on Dashboard 1
                var title = '渔径科技 | 后台管理系统';
                // Create your own title pattern
                var enterprise = '渔径科技';
                if($rootScope.currentUser.role.title==='enterprise'){
                    if($rootScope.currentUser.user.name!=null){
                        enterprise = $rootScope.currentUser.user.name;
                    }
                }else if($rootScope.currentUser.role.title==='staff'){
                    if($rootScope.currentUser.user.enterprise.name!=null){
                        enterprise = $rootScope.currentUser.user.enterprise.name;
                    }
                }
                if (toState.data && toState.data.pageTitle) title = enterprise +' | '+ toState.data.pageTitle;
                $timeout(function() {
                    element.text(title);
                });
            };
            $rootScope.$on('$stateChangeStart', listener);
        }
    }
}

/**
 * sideNavigation - Directive for run metsiMenu on sidebar navigation
 */
function sideNavigation($timeout) {
    return {
        restrict: 'A',
        link: function(scope, element) {
            // Call the metsiMenu plugin and plug it to sidebar navigation
            $timeout(function(){
                element.metisMenu();

            });

            // Colapse menu in mobile mode after click on element
            var menuElement = $('#side-menu a:not([href$="\\#"])');
            menuElement.click(function(){
                if ($(window).width() < 769) {
                    $("body").toggleClass("mini-navbar");
                }
            });

            // Enable initial fixed sidebar
            if ($("body").hasClass('fixed-sidebar')) {
                var sidebar = element.parent();
                sidebar.slimScroll({
                    height: '100%',
                    railOpacity: 0.9,
                });
            }
        }
    };
}

/**
 * iboxTools - Directive for iBox tools elements in right corner of ibox
 */
function iboxTools($timeout) {
    return {
        restrict: 'A',
        scope: true,
        templateUrl: '/res/views/common/ibox_tools.html',
        controller: function ($scope, $element) {
            // Function for collapse ibox
            $scope.showhide = function () {
                var ibox = $element.closest('div.ibox');
                var icon = $element.find('i:first');
                var content = ibox.children('.ibox-content');
                content.slideToggle(200);
                // Toggle icon from up to down
                icon.toggleClass('fa-chevron-up').toggleClass('fa-chevron-down');
                ibox.toggleClass('').toggleClass('border-bottom');
                $timeout(function () {
                    ibox.resize();
                    ibox.find('[id^=map-]').resize();
                }, 50);
            },
                // Function for close ibox
                $scope.closebox = function () {
                    var ibox = $element.closest('div.ibox');
                    ibox.remove();
                }
        }
    };
}

/**
 * iboxTools with full screen - Directive for iBox tools elements in right corner of ibox with full screen option
 */
function iboxToolsFullScreen($timeout) {
    return {
        restrict: 'A',
        scope: true,
        templateUrl: 'views/common/ibox_tools_full_screen.html',
        controller: function ($scope, $element) {
            // Function for collapse ibox
            $scope.showhide = function () {
                var ibox = $element.closest('div.ibox');
                var icon = $element.find('i:first');
                var content = ibox.children('.ibox-content');
                content.slideToggle(200);
                // Toggle icon from up to down
                icon.toggleClass('fa-chevron-up').toggleClass('fa-chevron-down');
                ibox.toggleClass('').toggleClass('border-bottom');
                $timeout(function () {
                    ibox.resize();
                    ibox.find('[id^=map-]').resize();
                }, 50);
            };
            // Function for close ibox
            $scope.closebox = function () {
                var ibox = $element.closest('div.ibox');
                ibox.remove();
            };
            // Function for full screen
            $scope.fullscreen = function () {
                var ibox = $element.closest('div.ibox');
                var button = $element.find('i.fa-expand');
                $('body').toggleClass('fullscreen-ibox-mode');
                button.toggleClass('fa-expand').toggleClass('fa-compress');
                ibox.toggleClass('fullscreen');
                setTimeout(function() {
                    $(window).trigger('resize');
                }, 100);
            }
        }
    };
}

/**
 * minimalizaSidebar - Directive for minimalize sidebar
*/
function minimalizaSidebar($timeout) {
    return {
        restrict: 'A',
        template: '<a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="" ng-click="minimalize()"><i class="fa fa-bars"></i></a>',
        controller: function ($scope, $element) {
            $scope.minimalize = function () {
                $("body").toggleClass("mini-navbar");
                if (!$('body').hasClass('mini-navbar') || $('body').hasClass('body-small')) {
                    // Hide menu in order to smoothly turn on when maximize menu
                    $('#side-menu').hide();
                    // For smoothly turn on menu
                    setTimeout(
                        function () {
                            $('#side-menu').fadeIn(400);
                        }, 200);
                } else if ($('body').hasClass('fixed-sidebar')){
                    $('#side-menu').hide();
                    setTimeout(
                        function () {
                            $('#side-menu').fadeIn(400);
                        }, 100);
                } else {
                    // Remove all inline style from jquery fadeIn function to reset menu state
                    $('#side-menu').removeAttr('style');
                }
            }
        }
    };
}

// 发送验证码按钮
function timerButton($timeout,$interval){
    return {
        restrict: 'AE',
        scope: {
            showTimer: '=', // 支持解析变量
            timeout: '=',
            telephone:'@', // 单项绑定
            msgId:'=',
            telephonePattern:'@'
        },
        link: function(scope, element, attrs){
            scope.timer = false;
            scope.timerCount = scope.timeout / 1000;
            scope.text = "获取验证码";
            element.on('click',function(){
                // 正则表达式验证类
                var reg = new RegExp(scope.telephonePattern);
                if(!reg.test(($('#telephone').val()))){
                    // 兼容性可能存在问题
                    $('#telephone').get(0).setCustomValidity("手机号码格式错误");
                    $('#telephone').get(0).reportValidity();
                }else{
                    scope.timer = true;
                    $.get("sendVerificationCode",{telephone:scope.telephone},function (data,statusText) {
                        if(data.exception){
                            alert("与服务器交互出现异常:"+data.exception);
                            scope.timer = false;
                        }else{
                            if(data!=null&&data!=""){
                                if(data=="isExist"){
                                    $('#telephone').get(0).setCustomValidity("手机号码已注册");
                                    $('#telephone').get(0).reportValidity();
                                    scope.timer=false;
                                }else{
                                    scope.msgId = data;
                                    // 触发时间按钮事件
                                    scope.showTimer = true;
                                    scope.text = "s";
                                    var counter = $interval(function(){
                                        scope.timerCount = scope.timerCount - 1;
                                    }, 1000);
                                    $timeout(function(){
                                        scope.text = "获取验证码";
                                        scope.timer = false;
                                        $interval.cancel(counter);
                                        scope.showTimer = false;
                                        scope.timerCount = scope.timeout / 1000;
                                    }, scope.timeout);
                                }
                            }else{
                                scope.timer=false;
                            }
                        }
                    });
                    return false;
                }
            });
        },
        template: '<button type="button" class="btn btn-primary" style="width: 96px;" ng-disabled="timer"><span ng-if="showTimer">{{ timerCount }}</span>{{text}}</a>'
    };
}

function accessLevel(AuthService) {
    return {
        restrict: 'A',
        link: function($scope, element, attrs) {
            var prevDisp = element.css('display')
                , userRole
                , accessLevel;

            $scope.user = AuthService.user;
            $scope.$watch('user', function(user) {
                if(user.role)
                    userRole = user.role;
                updateCSS();
            }, true);

            attrs.$observe('accessLevel', function(al) {
                if(al) accessLevel = $scope.$eval(al);
                updateCSS();
            });

            function updateCSS() {
                if(userRole && accessLevel) {
                    if(!AuthService.authorize(accessLevel, userRole))
                        element.css('display', 'none');
                    else
                        element.css('display', prevDisp);
                }
            }
        }
    };
}

function activeNav($location) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var anchor = element[0];
            if(element[0].tagName.toUpperCase() != 'A')
                anchor = element.find('a')[0];
            var path = anchor.href;

            scope.location = $location;
            scope.$watch('location.absUrl()', function(newPath) {
                path = normalizeUrl(path);
                newPath = normalizeUrl(newPath);

                if(path === newPath ||
                    (attrs.activeNav === 'nestedTop' && newPath.indexOf(path) === 0)) {
                    element.addClass('active');
                } else {
                    element.removeClass('active');
                }
            });
        }

    };

    function normalizeUrl(url) {
        if(url[url.length - 1] !== '/')
            url = url + '/';
        return url;
    }

}

angular
    .module('fishing-path')
    .directive('pageTitle', pageTitle)
    .directive('sideNavigation', sideNavigation)
    .directive('iboxTools', iboxTools)
    .directive('minimalizaSidebar', minimalizaSidebar)
    .directive('iboxToolsFullScreen', iboxToolsFullScreen)
    .directive('timerButton',timerButton)
    .directive('accessLevel',['AuthService',accessLevel])
    .directive('activeNav',['$location',activeNav])
