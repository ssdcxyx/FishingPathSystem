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
                $rootScope.currentUser = data;
                if(data.user.user.role === 1){
                    $state.go('staff.main');
                }else if(data.user.user.role === 2){
                    $state.go('enterprise.main');
                }else if(data.user.user.role === 3){
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

function EnterpriseMainCtrl(){


    /**
     * Data for Line chart
     */
    this.lineData = {
        labels: ["星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"],
        datasets: [
            {
                label: "学习员工数量",
                fillColor: "rgba(220,220,220,0.5)",
                strokeColor: "rgba(220,220,220,1)",
                pointColor: "rgba(220,220,220,1)",
                pointStrokeColor: "#fff",
                pointHighlightFill: "#fff",
                pointHighlightStroke: "rgba(220,220,220,1)",
                data: [4, 3, 5, 6, 3, 7, 8]
            },
            {
                label: "所学课程数量",
                fillColor: "rgba(26,179,148,0.5)",
                strokeColor: "rgba(26,179,148,0.7)",
                pointColor: "rgba(26,179,148,1)",
                pointStrokeColor: "#fff",
                pointHighlightFill: "#fff",
                pointHighlightStroke: "rgba(26,179,148,1)",
                data: [3, 7, 2, 5, 4, 6, 11]
            }
        ]
    };


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

}

function StaffsManagementCtrl($scope,SweetAlert,EnterpriseService,StaffService){
    $scope.departmentsOptions = [];
    $scope.postTypesOptions = [];
    EnterpriseService.getAllDepartments(
        function (data) {
            $scope.departments = data;
            for(var i = 0;i<$scope.departments.length;i++){
                $scope.departmentsOptions.push({"label":$scope.departments[i].name,"value":$scope.departments[i].id});
            }
            EnterpriseService.getAllPostTypes(
                function (data) {
                    $scope.postTypes = data;
                    for(var i = 0;i<$scope.postTypes.length;i++){
                        $scope.postTypesOptions.push({"label":$scope.postTypes[i].name,"value":$scope.postTypes[i].id});
                    }
                    var editor = new $.fn.dataTable.Editor( {
                        table: '#table_staffs',
                        idSrc:"id",
                        fields: [
                            { label: '企业', name:'enterprise.id',
                                type:'select',
                                options:[{'label':$scope.currentUser.user.name,'value':$scope.currentUser.user.id}]
                            },
                            { label: '工号：', name: 'jobNumber'},
                            { label: '姓名：',  name: 'name'},
                            { label: '性别：', name: 'sex', type:"radio",
                                options:[
                                    {label:'男',value:'男'},
                                    {label:'女',value:'女'}
                                ],
                                def:'男'},
                            { label: '部门：', name:'department.id',
                                type:'select',
                                options : $scope.departmentsOptions},
                            { label: '职位', name:'postType.id',
                                type:'select',
                                options: $scope.postTypesOptions},
                            { label: '联系方式：', name:'telephone'},
                            { label: '账号：', name:'user.account'},
                            { label: '密码：', name:'user.password'}
                        ],
                        ajax: {
                            create:{
                                url:'./addStaff',
                                type:"POST",
                                contentType:"application/json;charset=UTF-8",
                                dataSrc: "",
                                dataFilter:function(d,type){
                                    return d;
                                },
                                data:function (d) {
                                    return JSON.stringify(d);
                                }
                            },
                            edit:{
                                url:'./editStaff?id=_id_',
                                type:"POST",
                                contentType:"application/json;charset=UTF-8",
                                dataSrc: "",
                                dataFilter:function(d,type){
                                    return d;
                                },
                                data:function (d) {
                                    return JSON.stringify(d);
                                }
                            },
                            remove:{
                                url:'./deleteStaff?id=_id_',
                                type:"POST",
                                contentType:"application/json;charset=UTF-8",
                                dataType:"json",
                                dataSrc: "",
                                data:function (d) {
                                    return JSON.stringify(d)
                                }
                            }
                        },
                        i18n: {
                            "create": {
                                "button": "添加",
                                "title":  "添加新员工账号",
                                "submit": "创建"
                            },
                            "edit": {
                                "button": "保存",
                                "title":  "编辑员工账号信息",
                                "submit": "更新"
                            },
                            "error": {
                                "system": "系统出现未知错误"
                            },
                            "remove": {
                                "button": "移除",
                                "title":  "移除员工账号",
                                "submit": "移除",
                                "confirm": {
                                    "1": "确定移除这个员工账号?",
                                    "_": "确定移除这%d个员工账号",
                                }
                            },
                            "multi": {
                                "title": "多个值",
                                "info": "所选项包含该输入的不同值。若要编辑和设置此输入的所有项，请单击此处，否则它们将保留各自的值。",
                                "restore": "撤销修改",
                                "noMulti": "这个输入可以单独编辑，但不是组的一部分。"
                            }
                        }
                    });
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
                        processing:true,
                        dom:'<"html5buttons"B>lTfgitp',
                        lengthMenu:[[10,25,50,-1],['10','25','50','全部']],
                        ajax:{
                            "url":'./getStaffsByEnterpriseId',
                            "data":{
                                "enterpriseId":$scope.currentUser.user.id
                            },
                            "TYPE":"GET",
                            "dataSrc": ""
                        },
                        columns: [
                            { data: "jobNumber" },
                            { data: "name" },
                            { data: "sex" },
                            { data : 'department.name'},
                            { data : 'postType.name'},
                            { data : 'telephone'},
                            { data : 'user.account'},
                            { data : 'user.password'}
                        ],
                        buttons:[
                            { extend: 'create', text:'添加',editor:editor},
                            { extend: 'edit',  text: '编辑', editor:editor},
                            { extend: 'remove', text: '移除', editor:editor},
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
                    editor.on('submitComplete',function(){
                        console.log("postSubmit");
                        tableStaffs.ajax.reload();
                    });
                },
                function (err) {
                    SweetAlert.swal({
                        title:"获取信息失败",
                        text:"与服务器交互出现异常："+err + "！",
                        type:"error"
                    })
                }
            );
        },
        function (err) {
            SweetAlert.swal({
                title:"获取信息失败",
                text:"与服务器交互出现异常："+err + "！",
                type:"error"
            })
        }
    );
}

function AnnouncementManagementCtrl($scope){
    $scope.sendRoles = [
        {"id":'1','name':'所有员工'},
        {'id':'2','name':'管理部员工'}
    ];
    $scope.sendRole = $scope.sendRoles[0];
}

function StaffMainCtrl(){
    this.slideInterval = 3000;
}


angular
    .module('fishing-path')
    .controller('ApplicationController',['$rootScope','$location', '$scope', '$state', 'SweetAlert','AuthService',
        ApplicationController])
    .controller('UserLoginCtrl',['$rootScope', '$scope', '$state','$location', '$window','SweetAlert','AuthService',
        UserLoginCtrl])
    .controller('EnterpriseRegisterCtrl',['$rootScope', '$scope', '$state', '$location','SweetAlert','AuthService','REG_CONSTANT',
        EnterpriseRegisterCtrl])
    .controller('EnterpriseMainCtrl', EnterpriseMainCtrl)
    .controller('EnterpriseUserInfoCtrl',['$scope','SweetAlert','EnterpriseService','AuthService',EnterpriseUserInfoCtrl])
    .controller('StaffsManagementCtrl',['$scope','SweetAlert','EnterpriseService','StaffService',StaffsManagementCtrl])
    .controller('AnnouncementManagementCtrl',['$scope',AnnouncementManagementCtrl])
    .controller('StaffMainCtrl', StaffMainCtrl)