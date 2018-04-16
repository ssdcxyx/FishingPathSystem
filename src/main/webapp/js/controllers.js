// 主控制器
function ApplicationController($rootScope, $location, $scope, $state, SweetAlert,AuthService) {
    $rootScope.currentUser = AuthService.user;
    $rootScope.userRoles = AuthService.userRoles;
    $rootScope.accessLevels = AuthService.accessLevels;

    $scope.logout = function() {
        AuthService.logout(function() {
            $state.go('anon.login')
        }, function(err) {
            SweetAlert.swal({
                title:"注销失败",
                text:"与服务器交互出现异常："+err+"！",
                type:"error"
            })
        });
    };
}

function UserLoginCtrl($rootScope, $scope, $state, $location, $window, SweetAlert,AuthService) {

    $scope.login = function() {
        AuthService.login({
                telephone: $scope.telephone,
                password: $scope.password
            },
            SweetAlert,
            function(data) {
                if(data === 1){
                    $state.go('staff.main');
                }else if(data === 2){
                    $state.go('enterprise.main');
                }else if(data === 3){
                    $state.go('admin.main');
                }
            },
            function(err) {
                SweetAlert.swal({
                    title:"登录失败",
                    text:"与服务器交互出现异常："+err+"！",
                    type:"error"
                })
            });
    };
};

function EnterpriseRegisterCtrl($rootScope, $state, $scope, $location,SweetAlert, AuthService,REG_CONSTANT) {

    $scope.validateTelephone = REG_CONSTANT.telephonePattern;
    $scope.validateAuthCode = REG_CONSTANT.authCodePattern;

    $scope.role = AuthService.userRoles.user;
    $scope.userRoles = AuthService.userRoles;

    $scope.enterpriseRegister = function() {
        AuthService.enterpriseRegister({
                telephone:$scope.telephone,
                password:$scope.password,
                msgId:$scope.msgId,
                authCode:$scope.authCode
            },
            SweetAlert,
            function() {
                $state.go('enterprise.main');
            },
            function(err) {
                SweetAlert.swal({
                    title:"注册失败",
                    text:"与服务器交互出现异常："+err + "！",
                    type:"error"
                })
            });

    };
}

function EnterpriseUserInfoCtrl($scope,SweetAlert,EnterpriseService,AuthService){

    $scope.enterprise = $scope.currentUser.user;
    $scope.enterprise_type = {};
    $scope.enterpriseTypes = [];
    EnterpriseService.getAllEnterpriseTypes(
        function (data) {
            $scope.enterpriseTypes = data;
            if($scope.enterprise.enterpriseType!=null){
                $scope.enterprise_type = $scope.enterpriseTypes[$scope.enterprise.enterpriseType.id-1];
                $scope.$apply();
            }
        },
        function (err) {
            SweetAlert.swal({
                title:"获取信息失败",
                text:"与服务器交互出现异常："+err + "！",
                type:"error"
            })
        }
    );
    $scope.updateEnterpriseInfo = function () {
        EnterpriseService.updateEnterpriseInfo({
                "id":$scope.enterprise.id,
                "name":$scope.enterprise.name,
                "enterpriseTypeId":$scope.enterprise_type.id,
                "description":$scope.enterprise.description,
                "linkMan":$scope.enterprise.linkMan,
                "telephone":$scope.enterprise.telephone
            },
            function (data) {
                let res = {}
                res = {user:data,role:$scope.userRoles.enterprise};
                AuthService.changeUser(res)
                SweetAlert.swal("更新成功！", "企业信息获得更新！", "success");
            },function (err) {
                SweetAlert.swal(
                    "失败",
                    "更新企业信息失败！",
                    "error"
                )
            })
    }
}

function MainCtrl(){
    this.polarData = [
        {
            value: 300,
            color:"#a3e1d4",
            highlight: "#1ab394",
            label: "App"
        },
        {
            value: 140,
            color: "#dedede",
            highlight: "#1ab394",
            label: "Software"
        },
        {
            value: 200,
            color: "#A4CEE8",
            highlight: "#1ab394",
            label: "Laptop"
        }
    ];

    /**
     * Options for Polar chart
     */
    this.polarOptions = {
        scaleShowLabelBackdrop : true,
        scaleBackdropColor : "rgba(255,255,255,0.75)",
        scaleBeginAtZero : true,
        scaleBackdropPaddingY : 1,
        scaleBackdropPaddingX : 1,
        scaleShowLine : true,
        segmentShowStroke : true,
        segmentStrokeColor : "#fff",
        segmentStrokeWidth : 2,
        animationSteps : 100,
        animationEasing : "easeOutBounce",
        animateRotate : true,
        animateScale : false
    };

    /**
     * Data for Doughnut chart
     */
    this.doughnutData = [
        {
            value: 300,
            color:"#a3e1d4",
            highlight: "#1ab394",
            label: "App"
        },
        {
            value: 50,
            color: "#dedede",
            highlight: "#1ab394",
            label: "Software"
        },
        {
            value: 100,
            color: "#A4CEE8",
            highlight: "#1ab394",
            label: "Laptop"
        }
    ];

    /**
     * Options for Doughnut chart
     */
    this.doughnutOptions = {
        segmentShowStroke : true,
        segmentStrokeColor : "#fff",
        segmentStrokeWidth : 2,
        percentageInnerCutout : 45, // This is 0 for Pie charts
        animationSteps : 100,
        animationEasing : "easeOutBounce",
        animateRotate : true,
        animateScale : false
    };

    /**
     * Data for Line chart
     */
    this.lineData = {
        labels: ["January", "February", "March", "April", "May", "June", "July"],
        datasets: [
            {
                label: "Example dataset",
                fillColor: "rgba(220,220,220,0.5)",
                strokeColor: "rgba(220,220,220,1)",
                pointColor: "rgba(220,220,220,1)",
                pointStrokeColor: "#fff",
                pointHighlightFill: "#fff",
                pointHighlightStroke: "rgba(220,220,220,1)",
                data: [65, 59, 80, 81, 56, 55, 40]
            },
            {
                label: "Example dataset",
                fillColor: "rgba(26,179,148,0.5)",
                strokeColor: "rgba(26,179,148,0.7)",
                pointColor: "rgba(26,179,148,1)",
                pointStrokeColor: "#fff",
                pointHighlightFill: "#fff",
                pointHighlightStroke: "rgba(26,179,148,1)",
                data: [28, 48, 40, 19, 86, 27, 90]
            }
        ]
    };

    this.lineDataDashboard4 = {
        labels: ["January", "February", "March", "April", "May", "June", "July"],
        datasets: [
            {
                label: "Example dataset",
                fillColor: "rgba(220,220,220,0.5)",
                strokeColor: "rgba(220,220,220,1)",
                pointColor: "rgba(220,220,220,1)",
                pointStrokeColor: "#fff",
                pointHighlightFill: "#fff",
                pointHighlightStroke: "rgba(220,220,220,1)",
                data: [65, 59, 40, 51, 36, 25, 40]
            },
            {
                label: "Example dataset",
                fillColor: "rgba(26,179,148,0.5)",
                strokeColor: "rgba(26,179,148,0.7)",
                pointColor: "rgba(26,179,148,1)",
                pointStrokeColor: "#fff",
                pointHighlightFill: "#fff",
                pointHighlightStroke: "rgba(26,179,148,1)",
                data: [48, 48, 60, 39, 56, 37, 30]
            }
        ]
    };

    /**
     * Options for Line chart
     */
    this.lineOptions = {
        scaleShowGridLines : true,
        scaleGridLineColor : "rgba(0,0,0,.05)",
        scaleGridLineWidth : 1,
        bezierCurve : true,
        bezierCurveTension : 0.4,
        pointDot : true,
        pointDotRadius : 4,
        pointDotStrokeWidth : 1,
        pointHitDetectionRadius : 20,
        datasetStroke : true,
        datasetStrokeWidth : 2,
        datasetFill : true
    };

    /**
     * Options for Bar chart
     */
    this.barOptions = {
        scaleBeginAtZero : true,
        scaleShowGridLines : true,
        scaleGridLineColor : "rgba(0,0,0,.05)",
        scaleGridLineWidth : 1,
        barShowStroke : true,
        barStrokeWidth : 2,
        barValueSpacing : 5,
        barDatasetSpacing : 1
    };

    /**
     * Data for Bar chart
     */
    this.barData = {
        labels: ["January", "February", "March", "April", "May", "June", "July"],
        datasets: [
            {
                label: "My First dataset",
                fillColor: "rgba(220,220,220,0.5)",
                strokeColor: "rgba(220,220,220,0.8)",
                highlightFill: "rgba(220,220,220,0.75)",
                highlightStroke: "rgba(220,220,220,1)",
                data: [65, 59, 80, 81, 56, 55, 40]
            },
            {
                label: "My Second dataset",
                fillColor: "rgba(26,179,148,0.5)",
                strokeColor: "rgba(26,179,148,0.8)",
                highlightFill: "rgba(26,179,148,0.75)",
                highlightStroke: "rgba(26,179,148,1)",
                data: [28, 48, 40, 19, 86, 27, 90]
            }
        ]
    };

    /**
     * Data for Radar chart
     */
    this.radarData = {
        labels: ["Eating", "Drinking", "Sleeping", "Designing", "Coding", "Cycling", "Running"],
        datasets: [
            {
                label: "My First dataset",
                fillColor: "rgba(220,220,220,0.2)",
                strokeColor: "rgba(220,220,220,1)",
                pointColor: "rgba(220,220,220,1)",
                pointStrokeColor: "#fff",
                pointHighlightFill: "#fff",
                pointHighlightStroke: "rgba(220,220,220,1)",
                data: [65, 59, 90, 81, 56, 55, 40]
            },
            {
                label: "My Second dataset",
                fillColor: "rgba(26,179,148,0.2)",
                strokeColor: "rgba(26,179,148,1)",
                pointColor: "rgba(26,179,148,1)",
                pointStrokeColor: "#fff",
                pointHighlightFill: "#fff",
                pointHighlightStroke: "rgba(151,187,205,1)",
                data: [28, 48, 40, 19, 96, 27, 100]
            }
        ]
    };

    /**
     * Options for Radar chart
     */
    this.radarOptions = {
        scaleShowLine : true,
        angleShowLineOut : true,
        scaleShowLabels : false,
        scaleBeginAtZero : true,
        angleLineColor : "rgba(0,0,0,.1)",
        angleLineWidth : 1,
        pointLabelFontFamily : "'Arial'",
        pointLabelFontStyle : "normal",
        pointLabelFontSize : 10,
        pointLabelFontColor : "#666",
        pointDot : true,
        pointDotRadius : 3,
        pointDotStrokeWidth : 1,
        pointHitDetectionRadius : 20,
        datasetStroke : true,
        datasetStrokeWidth : 2,
        datasetFill : true
    };
}

function StaffsManagementCtrl($scope,SweetAlert,StaffService){
    var editor = new $.fn.dataTable.Editor( {
        ajax:  './getStaffsByEnterpriseId',
        table: '#table_staffs',
        idSrc:  'id',
        fields: [
            { label: '工号：', name: 'jobNumber'},
            { label: '姓名：',  name: 'name'},
            { label: '性别：', name: 'sex', type:"radio",
                options:[
                    {label:'男',value:'男'},
                    {label:'女',value:'女'}
                ]},
            { label: '部门：', name:'department.name'},
            { label: '职位', name:'postType.name'},
            { label: '联系方式：', name:'telephone'}
        ],
        i18n: {
            create: {
                button: "添加",
                title:  "添加新员工账号",
                submit: "创建"
            },
            multi: {
                "title": "多个值",
                "info": "所选项包含该输入的不同值。若要编辑和设置此输入的所有项，请单击此处，否则它们将保留各自的值。",
                "restore": "撤销修改",
                "noMulti": "这个输入可以单独编辑，但不是组的一部分。"
            },
            datetime: {
                previous: 'Précédent',
                next:     'Premier',
                months:   [ 'Janvier', 'Février', 'Mars', 'Avril', 'peut', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Décembre' ],
                weekdays: [ 'Dim', 'Lun', 'Mar', 'Mer', 'Jeu', 'Ven', 'Sam' ]
            }
        }
    });
    var backNext = [
        {
            text: "&lt;",
            action: function (e) {
                // On submit, find the currently selected row and select the previous one
                this.submit( function () {
                    var indexes = tableStaffs.rows( {search: 'applied'} ).indexes();
                    var currentIndex = tableStaffs.row( {selected: true} ).index();
                    var currentPosition = indexes.indexOf( currentIndex );

                    if ( currentPosition > 0 ) {
                        tableStaffs.row( currentIndex ).deselect();
                        tableStaffs.row( indexes[ currentPosition-1 ] ).select();
                    }

                    // Trigger editing through the button
                    tableStaffs.button( 1 ).trigger();
                }, null, null, false );
            }
        },
        'Save',
        {
            text: "&gt;",
            action: function (e) {
                // On submit, find the currently selected row and select the next one
                this.submit( function () {
                    var indexes = tableStaffs.rows( {search: 'applied'} ).indexes();
                    var currentIndex = tableStaffs.row( {selected: true} ).index();
                    var currentPosition = indexes.indexOf( currentIndex );

                    if ( currentPosition < indexes.length-1 ) {
                        tableStaffs.row( currentIndex ).deselect();
                        tableStaffs.row( indexes[ currentPosition+1 ] ).select();
                    }

                    // Trigger editing through the button
                    tableStaffs.button( 1 ).trigger();
                }, null, null, false );
            }
        }
    ];
    var tableStaffs = $('#table_staffs').DataTable({
            language: {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                },
                "select": {
                    "style": 'single',
                    "rows": {
                        0: "(点击行选择一项员工数据)",
                        1: "(已选择一项员工数据)"
                    }
                },
            },
            select: true,
            dom:'<"html5buttons"B>lTfgitp',
            ajax:{
                "url":'./getStaffsByEnterpriseId',
                "dataSrc": ""
            },
            columns: [
                { data: "jobNumber" },
                { data: "name" },
                { data: "sex" },
                { data : 'department.name'},
                { data : 'postType.name'},
                { data : 'telephone'}
            ],
            buttons:[
                { extend: 'create', text:'添加',editor:editor},
                {
                    extend: 'selected',
                    text:   '编辑',
                    action: function () {
                        var indexes = tableStaffs.rows( {selected: true} ).indexes();

                        editor.edit( indexes, {
                            title: '编辑员工账号信息',
                            buttons: indexes.length === 1 ?
                                backNext :
                                '保存'
                        } );
                    }
                },
                { extend: 'selected', text: '删除',
                    action: function ( e, dt, node, config ) {
                        var rows = tableStaffs.rows( {selected: true} ).indexes();

                        editor
                            .hide( editor.fields() )
                            .one( 'close', function () {
                                setTimeout( function () { // Wait for animation
                                    editor.show( editor.fields() );
                                }, 500 );
                            } )
                            .edit( rows, {
                                title: '删除员工账号',
                                message: rows.length === 1 ?
                                    '确定将此员工账号移除？' :
                                    '确定将移除这 '+rows.length+'员工账号？',
                                buttons: '删除'
                            } );
                    }},
                { extend: 'excel', text:'下载', title: '员工列表'},
                { extend: 'print', text: '打印',
                    customize: function (win){
                        $(win.document.body).addClass('white-bg');
                        $(win.document.body).css('font-size', '10px');

                        $(win.document.body).find('table')
                            .addClass('compact')
                            .css('font-size', 'inherit');
                    }
                }
            ]
    });
    // $scope.staffs = [];
    // StaffService.getStaffsByEnterpriseId({
    //         enterpriseId : $scope.currentUser.user.id
    //     },
    //     function(data){
    //          $scope.staffs = data;
    //          $scope.$apply();
    //     },
    //     function (err) {
    //         SweetAlert.swal(
    //             "失败",
    //             "获取员工信息失败！",
    //             "error"
    //         )
    //     }
    // )
}


angular
    .module('fishing-path')
    .controller('ApplicationController',['$rootScope','$location', '$scope', '$state', 'SweetAlert','AuthService',
        ApplicationController])
    .controller('UserLoginCtrl',['$rootScope', '$scope', '$state','$location', '$window','SweetAlert','AuthService',
        UserLoginCtrl])
    .controller('EnterpriseRegisterCtrl',['$rootScope', '$scope', '$state', '$location','SweetAlert','AuthService','REG_CONSTANT',
        EnterpriseRegisterCtrl])
    .controller('MainCtrl', MainCtrl)
    .controller('EnterpriseUserInfoCtrl',['$scope','SweetAlert','EnterpriseService','AuthService',EnterpriseUserInfoCtrl])
    .controller('StaffsManagementCtrl',['$scope','SweetAlert','StaffService',StaffsManagementCtrl])