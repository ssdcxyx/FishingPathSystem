/**
 * INSPINIA - Responsive Admin Theme
 *
 */
function config($translateProvider) {

    $translateProvider
        .translations('ch', {

            // 定义所有菜单项
            MAIN_PAGE: '首页',
            USERS_MANAGEMENT:'员工管理'

        })

    $translateProvider.preferredLanguage('ch');

}

angular
    .module('fishing-path')
    .config(config)
