/**
 * 定义国际化内容
 */
function config($translateProvider) {

    $translateProvider
        .translations('ch', {

            // 定义所有菜单项
            MAIN_PAGE: '首页',
            STAFFS_MANAGEMENT:'员工管理',
            COURSES_MANAGEMENT:'课程管理',
            STUDY_STATISTICS:'学习统计',
            ANNOUNCEMENTS_MANAGEMENT:'公告管理',
            OTHERS:'其他',
            LEARNING_PATH:'学习路径',
            COURSES_LIBRARY:'课程库',
            ANNOUNCEMENTS_BOARD:'公告栏',
            ASSOCIATES:'同事圈'
        })

    $translateProvider.preferredLanguage('ch');

}

angular
    .module('fishing-path')
    .config(config)
