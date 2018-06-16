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

function EnterpriseContactsCtrl($scope,SweetAlert,StaffService) {
    $scope.enterprise = $scope.currentUser.user;
    $scope.staff = $scope.currentUser.user;
    StaffService.getAllStaffsByEnterpriseIdAndDepartmentIdAndPostTypeId(
        $scope.enterprise.id,
        null,
        null,
        function (data) {
            $scope.staffs = data;
            $scope.$apply();
        },function(err){
            SweetAlert.swal({
                title:"获取员工信息失败",
                text:"与服务器交互出现异常："+err + "！",
                type:"error"
            })
        }
    )
}

function EnterpriseUserInfoCtrl($scope,SweetAlert,toaster,EnterpriseService,AuthService){

    $scope.enterprise = $scope.currentUser.user;
    EnterpriseService.getEnterpriseByEnterpriseId(
        $scope.currentUser.user.id,
        function (data) {
            $scope.enterprise = data;
            let res = {};
            res = {user:data,role:$scope.userRoles.enterprise};
            AuthService.changeUser(res)
            $scope.$apply();
        },
        function (err) {
            SweetAlert.swal({
                title:"获取信息失败",
                text:"与服务器交互出现异常："+err + "！",
                type:"error"
            })
        }
    );
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
        $scope.loading = true;
        EnterpriseService.updateEnterpriseInfo({
                "id":$scope.enterprise.id,
                "name":$scope.enterprise.user.name,
                "enterpriseTypeId":$scope.enterprise_type.id,
                "description":$scope.enterprise.user.description,
                "linkMan":$scope.enterprise.linkMan,
                "telephone":$scope.enterprise.user.telephone
            },
            function (data) {
                $scope.loading = false;
                let res = {};
                res = {user:data,role:$scope.userRoles.enterprise};
                AuthService.changeUser(res)
                uploader.upload();
                toaster.success({ body:"企业信息已成功更新！"});
                $scope.$apply();
            },function (err) {
                $scope.loading = false;
                SweetAlert.swal(
                    "失败",
                    "更新企业信息失败！",
                    "error"
                )
            })
    }
    // 图片上传
    var $list = $('#fileList');
    // 优化retina, 在retina下这个值是2
    var ratio = window.devicePixelRatio || 1;
    // 缩略图大小
    // var thumbnailWidth = 100 * ratio;
    // var thumbnailHeight = 100 * ratio;
    // 初始化Web Uploader
    var uploader = WebUploader.create({
        // 自动上传。
        auto: false,
        // swf文件路径
        swf:'plugins/webuploader/js/Uploader.swf',
        // 文件接收服务端。
        server: '/uploadBusinessLicense?enterpriseId='+$scope.enterprise.id,
        //threads:'5',        //同时运行5个线程传输
        //fileNumLimit:'10',  //文件总数量只能选择10个

        // 选择文件的按钮。可选。
        pick: {id:'#filePicker',  //选择文件的按钮
            multiple:true},   //允许可以同时选择多个图片
        // 图片质量，只有type为`image/jpeg`的时候才有效。
        quality: 90,

        //限制传输文件类型，accept可以不写
        accept: {
            title: 'Images',//描述
            extensions: 'gif,jpg,jpeg,bmp,png,zip',//类型
            mimeTypes: 'image/*'//mime类型
        }
    });


    // 当有文件添加进来的时候，创建img显示缩略图使用
    uploader.on( 'fileQueued', function( file ) {
        var $img = $('#businessLicensePath');

        // 创建缩略图
        // 如果为非图片文件，可以不用调用此方法。
        // thumbnailWidth x thumbnailHeight 为 100 x 100
        uploader.makeThumb( file, function( error, src ) {
            if ( error ) {
                $img.replaceWith('<span>不能预览</span>');
                return;
            }
            $img.attr( 'src', src );
        }, 210, 297 );
    });

    // 文件上传过程中创建进度条实时显示。    uploadProgress事件：上传过程中触发，携带上传进度。 file文件对象 percentage传输进度 Nuber类型
    uploader.on( 'uploadProgress', function( file, percentage ) {
        var $img = $('#businessLicensePath');
        var $percent = $img.find('.progress span');

        // 避免重复创建
        if ( !$percent.length ) {
            $percent = $('<p class="progress"><span></span></p>')
                .appendTo( $img )
                .find('span');
        }

        $percent.css( 'width', percentage * 100 + '%' );
    });

    // 文件上传成功时候触发，给item添加成功class, 用样式标记上传成功。 file：文件对象，    response：服务器返回数据
    uploader.on( 'uploadSuccess', function( file,response) {
        $scope.enterprise.businessLicensePath = "http://localhost:8080/img/enterprise/" + $scope.enterprise.id + "."+ file.ext;
        let res = {};
        res = {user:$scope.enterprise,role:$scope.userRoles.enterprise};
        AuthService.changeUser(res)
        $scope.$apply();
        $('#businessLicensePath').addClass('upload-state-done');
        //console.info(response);
        $("#upInfo").html("<font color='red'>"+response._raw+"</font>");
    });

    // 文件上传失败                                file:文件对象 ， code：出错代码
    uploader.on( 'uploadError', function(file,code) {
        var $img = $('#businessLicensePath');
        var $error = $img.find('div.error');

        // 避免重复创建
        if ( !$error.length ) {
            $error = $('<div class="error"></div>').appendTo( $img );
        }

        $error.text('上传失败!');
    });

    // 不管成功或者失败，文件上传完成时触发。 file： 文件对象
    uploader.on( 'uploadComplete', function( file ) {
        $('#businessLicensePath').find('.progress').remove();
    });
}

function StaffsManagementCtrl($scope,SweetAlert,EnterpriseService,StaffService){
    $scope.departmentsOptions = [];
    $scope.postTypesOptions = [];
    EnterpriseService.getAllDepartments(
        function (data) {
            $scope.departments = data;
            // 移除第一个数据即所有部门
            for(var i = 1;i<$scope.departments.length;i++){
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
                                options:[{'label':$scope.currentUser.user.user.name,'value':$scope.currentUser.user.id}]
                            },
                            { label: '工号：', name: 'jobNumber'},
                            { label: '姓名：',  name: 'user.name'},
                            { label: '性别：', name: 'user.sex', type:"radio",
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
                            { label: '联系方式：', name:'user.telephone'},
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
                            { data: "user.name" },
                            { data: "user.sex" },
                            { data : 'department.name'},
                            { data : 'postType.name'},
                            { data : 'user.telephone'},
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

function StudyStatisticsCtrl($scope,SweetAlert,EnterpriseService,StaffService){
    $scope.enterprise = $scope.currentUser.user;
    EnterpriseService.getEnterpriseDTOByEnterpriseId(
        $scope.enterprise.id,
        function (data) {
            $scope.enterpriseDTO = data;
            $scope.staffCount = 0;
            for (let i = 0; i < $scope.enterpriseDTO.departmentDTOList.length; i++) {
                $scope.staffCount += $scope.enterpriseDTO.departmentDTOList[i].staffDTOList.length;
            }
            $scope.tab = $scope.enterpriseDTO.departmentDTOList[0].department.id;
            $scope.client = $scope.enterpriseDTO.departmentDTOList[0].staffDTOList[0].staff.id;
            $scope.$apply();
        },function(err){
            SweetAlert.swal({
                title:"获取课程信息失败",
                text:"与服务器交互出现异常："+err + "！",
                type:"error"
            })
        }
    )
    $scope.tabChange = function (department_id,index) {
        $scope.tab = department_id;
        $scope.tabIndex = index;
    }
    $scope.clientChange = function (staff_id,index) {
        $scope.client = staff_id;
        $scope.clientIndex = index;
    }
}

function findCoursesDTOByPage($scope,SweetAlert,CourseService,coursesLength,pageNo,pageSize) {
    CourseService.findCourseDTOsByPage(
        {pageNo:pageNo,pageSize:pageSize},
        function (data) {
            $scope.courseDTOs=data;
            $('#pagination').twbsPagination({
                totalPages: coursesLength/10 + 1,
                visiblePages: 10,
                onPageClick: function (event, page) {
                    findCoursesDTOByPage($scope,SweetAlert,CourseService,coursesLength,page,pageSize);
                }
            });
            $scope.$apply();
        },
        function (err) {
            SweetAlert.swal({
                title:"获取课程信息失败",
                text:"与服务器交互出现异常："+err + "！",
                type:"error"
            })
        }
    )
}

function CoursesManagementCtrl($scope,SweetAlert,CourseService) {
    CourseService.findCoursesLength(function (data) {
        $scope.coursesLenth = data;
        findCoursesDTOByPage($scope,SweetAlert,CourseService,$scope.coursesLenth,1,10);
    },function(err){
        SweetAlert.swal({
            title:"获取课程信息失败",
            text:"与服务器交互出现异常："+err + "！",
            type:"error"
        })
    });
}

function getNewDynamicState($scope,SweetAlert,AssociatesService,enterprise_id) {
    AssociatesService.getAllDynamicStatesByEnterpriseId(
        enterprise_id,
        function (data) {
            $scope.dynamicStates = data;
            $('.dynamic_state_content').html()
            $scope.$apply();
        },function(err){
            SweetAlert.swal({
                title:"获取动态信息失败",
                text:"与服务器交互出现异常："+err + "！",
                type:"error"
            })
        }
    )
}

function EnterpriseAssociatesCtrl($scope,SweetAlert,toaster,AssociatesService,StaffService) {
    $scope.enterprise = $scope.currentUser.user;
    getNewDynamicState($scope,SweetAlert,AssociatesService,$scope.enterprise.id);
    StaffService.getStaffsByEnterpriseId(
        {
            'enterpriseId':$scope.enterprise.id,
        },
        function (data) {
            $scope.staffs = data;
            $scope.$apply();
        },function(err){
            SweetAlert.swal({
                title:"获取信息失败",
                text:"与服务器交互出现异常："+err + "！",
                type:"error"
            })
        }
    );
    $scope.publishComment = function (dynamicStatId,index) {
        if($('#comment').val().replace(/(^s*)|(s*$)/g, "").length==0){
            SweetAlert.swal({
                title:"错误",
                text:"请您填写评论后再发表！",
                type:"error"
            })
        }else{
            AssociatesService.publishDynamicStateComment(
                $scope.enterprise.user.id,
                $('#comment').val(),
                dynamicStatId,
                function (data) {
                    if($scope.dynamicStates[index].dynamicStateCommentList==null){
                        $scope.dynamicStates[index].dynamicStateCommentList = new Array();
                    }
                    $scope.dynamicStates[index].dynamicStateCommentList.push(data);
                    $('#comment').val(""),
                    $scope.$apply();
                },function(err){
                    SweetAlert.swal({
                        title:"获取信息失败",
                        text:"与服务器交互出现异常："+err + "！",
                        type:"error"
                    })
                }
            )
        }
    };
    $scope.publishLike = function (dynamicStatId,index) {
        AssociatesService.publishDynamicStateLike(
            $scope.enterprise.user.id,
            dynamicStatId,
            function (data) {
                if(data!=""){
                    if($scope.dynamicStates[index].dynamicStateLikeList==null){
                        $scope.dynamicStates[index].dynamicStateLikeList = new Array();
                    }
                    $scope.dynamicStates[index].dynamicStateLikeList.push(data);
                }else{
                    for(var i=0; i<$scope.dynamicStates[index].dynamicStateLikeList.length; i++) {
                        if($scope.dynamicStates[index].dynamicStateLikeList[i].user.id == $scope.enterprise.user.id) {
                            $scope.dynamicStates[index].dynamicStateLikeList.splice(i, 1);
                            break;
                        }
                    }
                }
                $scope.$apply();
            },function(err){
                SweetAlert.swal({
                    title:"获取信息失败",
                    text:"与服务器交互出现异常："+err + "！",
                    type:"error"
                })
            }
        )
    };
    $scope.publishState = function () {
        if($('#summernote').summernote('code')=='<p><br></p>'||$('#summernote').summernote('code')==''){
            SweetAlert.swal({
                title:"错误",
                text:"请您填写动态后再发表！",
                type:"error"
            })
        }else{
            AssociatesService.publishDynamicState(
                $scope.enterprise.user.id,
                $('#summernote').summernote('code'),
                function (data) {
                    $scope.dynamicStates.unshift(data);
                    $('#summernote').summernote('code',"<p><br></p>");
                    toaster.success({ body:"动态已成功发表！"});
                    $scope.$apply();
                },function(err){
                    SweetAlert.swal({
                        title:"获取信息失败",
                        text:"与服务器交互出现异常："+err + "！",
                        type:"error"
                    })
                }
            )
        }
    };
    $scope.isLike = function (dynamicState) {
        if(dynamicState.dynamicStateLikeList!=null&&dynamicState.dynamicStateLikeList.length>0){
            for (let i = 0; i < dynamicState.dynamicStateLikeList.length; i++) {
                if($scope.enterprise.user.id == dynamicState.dynamicStateLikeList[i].user.id){
                    return true;
                }
            }
        }
        return false;

    };
    $('#summernote').summernote({
        lang: 'zh-CN',
        placeholder: '最近你进步了嘛，和大家一起分享一下吧！',
    });
}

function EnterpriseAnnouncementsCtrl($scope,SweetAlert,EnterpriseService){
    $scope.enterprise = $scope.currentUser.user;
    EnterpriseService.getAnnouncementsByEnterpriseId(
        $scope.enterprise.id,
        function (data) {
            $scope.announcements = data;
            $scope.$apply();
        },function(err){
            SweetAlert.swal({
                title:"获取课程信息失败",
                text:"与服务器交互出现异常："+err + "！",
                type:"error"
            })
        }
    )
}

function AnnouncementManagementCtrl($scope,SweetAlert,EnterpriseService){
    $scope.sendRole = 0;
    $scope.enterprise = $scope.currentUser.user;
    EnterpriseService.getAllDepartments(
        function (data) {
            $scope.departments = data;
            if($scope.departments!=null){
                $scope.sendRole = $scope.departments[0];
                $scope.$apply();
            }
        },
        function (err) {
            SweetAlert.swal({
                title:"获取信息失败",
                text:"与服务器交互出现异常："+err + "！",
                type:"error"
            })
        });

    $scope.publishAnnouncement = function(){
        $scope.loading=true;
        EnterpriseService.publishAnnouncement(
            {
                "title":$scope.announcementTitle,
                "information":$scope.announcementInfo,
                "enterpriseId":$scope.enterprise.id,
                "departmentId":$scope.sendRole.id
            },
            function (data) {
                $scope.loading=false;
                SweetAlert.swal("发布成功！", "公告信息已经成功推送至指定部门！", "success");
                $('#announcementForm')[0].reset();
            },
            function (err) {
                $scope.loading=false;
                SweetAlert.swal({
                    title:"发布公告失败",
                    text:"与服务器交互出现异常："+err + "！",
                    type:"error"
                })
            }
        )
    }

}

function StaffMainCtrl(){
    this.slideInterval = 3000;
}

function StaffContactsCtrl($scope,SweetAlert,StaffService) {
    $scope.staff = $scope.currentUser.user;
    StaffService.getAllStaffsByEnterpriseIdAndDepartmentIdAndPostTypeId(
        $scope.staff.enterprise.id,
        $scope.staff.department.id,
        $scope.staff.postType.id,
        function (data) {
            $scope.staffs = data;
            $scope.$apply();
        },function(err){
            SweetAlert.swal({
                title:"获取员工信息失败",
                text:"与服务器交互出现异常："+err + "！",
                type:"error"
            })
        }
    )
}

function findCoursesByPage($scope,SweetAlert,CourseService,coursesLength,pageNo,pageSize) {
    CourseService.findCoursesByPage(
        {pageNo:pageNo,pageSize:pageSize},
        function (data) {
            $scope.courses=data;
            $('#pagination').twbsPagination({
                totalPages: coursesLength/12 + 1,
                visiblePages: 10,
                onPageClick: function (event, page) {
                    findCoursesByPage($scope,SweetAlert,CourseService,coursesLength,page,pageSize);
                }
            });
            $scope.$apply();
            $('.star-grade').each(function () {
                $(this).raty();
                $(this).raty('score',parseInt($(this).attr('score'))/2)
                $(this).raty('readOnly',true);
            });
            shave('.product-name',32)
            shave('.m-t-xs',38);
        },
        function (err) {
            SweetAlert.swal({
                title:"获取课程信息失败",
                text:"与服务器交互出现异常："+err + "！",
                type:"error"
            })
        }
    )
}

function CoursesLibraryCtrl($scope,SweetAlert,CourseService){
    CourseService.findCoursesLength(function (data) {
        $scope.coursesLenth = data;
        findCoursesByPage($scope,SweetAlert,CourseService,$scope.coursesLenth,1,12);
    },function(err){
        SweetAlert.swal({
            title:"获取课程信息失败",
            text:"与服务器交互出现异常："+err + "！",
            type:"error"
        })
    });
}

function CourseDetailCtrl($scope,$stateParams,SweetAlert,CourseService){
    $scope.courseId = $stateParams.courseId;
    CourseService.findCourseByCourseId(
        $scope.courseId,
        function (data) {
            $scope.course = data;
            $scope.$apply();
        },function(err){
            SweetAlert.swal({
                title:"获取课程信息失败",
                text:"与服务器交互出现异常："+err + "！",
                type:"error"
            })
        }
    )
}

function StudyVideoCtrl($scope,$stateParams,SweetAlert,CourseService,StaffService){
    $scope.courseId = $stateParams.courseId;
    CourseService.findCourseByCourseId(
        $scope.courseId,
        function (data) {
            $scope.course = data;
            if($stateParams.flag==0){
                for (let i = 0; i < $scope.course.courseChapterList.length; i++) {
                    if($scope.course.courseChapterList[i].id == $stateParams.courseChapterIndex){
                        $scope.courseChapter = $scope.course.courseChapterList[i];
                    }
                }
                for (let i = 0; i < $scope.courseChapter.courseSectionList.length; i++) {
                    if($scope.courseChapter.courseSectionList[i].id == $stateParams.courseSectionIndex){
                        $scope.courseSection = $scope.courseChapter.courseSectionList[i];
                    }
                }
            }else if($stateParams.flag==1){
                $scope.courseChapter = $scope.course.courseChapterList[$stateParams.courseChapterIndex];
                $scope.courseSection = $scope.courseChapter.courseSectionList[$stateParams.courseSectionIndex];
            }

            $scope.$apply();
            StaffService.storeLearningRecord(
                $scope.currentUser.user.id,
                $scope.course.id,
                $scope.courseSection.id,
                function (data) {

                },function(err){
                    SweetAlert.swal({
                        title:"获取课程信息失败",
                        text:"与服务器交互出现异常："+err + "！",
                        type:"error"
                    })
                }
            )
        },function(err){
            SweetAlert.swal({
                title:"获取课程信息失败",
                text:"与服务器交互出现异常："+err + "！",
                type:"error"
            })
        }
    )
}

function LearningPathCtrl($scope,SweetAlert,StaffService){
    $scope.staff = $scope.currentUser.user;
    StaffService.getLearningPathByStaffId(
        $scope.staff.id,
        function (data) {
            $scope.learningPaths = data;
            $scope.tab = $scope.learningPaths[0].id;
            $scope.tabIndex = 0;
            $scope.$apply();
        },function(err){
            SweetAlert.swal({
                title:"获取学习记录信息失败",
                text:"与服务器交互出现异常："+err + "！",
                type:"error"
            })
        }
    )
    $scope.tabChange = function (learning_path_id,index) {
        $scope.tab = learning_path_id;
        $scope.tabIndex = index;
    }
}



function StaffAssociatesCtrl($scope,SweetAlert,toaster,AssociatesService,StaffService) {
    $scope.staff = $scope.currentUser.user;
    getNewDynamicState($scope,SweetAlert,AssociatesService,$scope.staff.enterprise.id);
    StaffService.getAllStaffsByEnterpriseIdAndDepartmentIdAndPostTypeId(
        $scope.staff.enterprise.id,
        $scope.staff.department.id,
        $scope.staff.postType.id,
        function (data) {
            $scope.staffs = data;
            $scope.$apply();
        },function(err){
            SweetAlert.swal({
                title:"获取信息失败",
                text:"与服务器交互出现异常："+err + "！",
                type:"error"
            })
        }
    );
    $scope.publishComment = function (dynamicStatId,index) {
        if($('#comment').val().replace(/(^s*)|(s*$)/g, "").length==0){
            SweetAlert.swal({
                title:"错误",
                text:"请您填写评论后再发表！",
                type:"error"
            })
        }else{
            AssociatesService.publishDynamicStateComment(
                $scope.staff.user.id,
                $('#comment').val(),
                dynamicStatId,
                function (data) {
                    if($scope.dynamicStates[index].dynamicStateCommentList==null){
                        $scope.dynamicStates[index].dynamicStateCommentList = new Array();
                    }
                    $scope.dynamicStates[index].dynamicStateCommentList.push(data);
                    $('#comment').val(""),
                        $scope.$apply();
                },function(err){
                    SweetAlert.swal({
                        title:"获取信息失败",
                        text:"与服务器交互出现异常："+err + "！",
                        type:"error"
                    })
                }
            )
        }
    };
    $scope.publishLike = function (dynamicStatId,index) {
        AssociatesService.publishDynamicStateLike(
            $scope.staff.user.id,
            dynamicStatId,
            function (data) {
                if(data!=""){
                    if($scope.dynamicStates[index].dynamicStateLikeList==null){
                        $scope.dynamicStates[index].dynamicStateLikeList = new Array();
                    }
                    $scope.dynamicStates[index].dynamicStateLikeList.push(data);
                }else{
                    for(var i=0; i<$scope.dynamicStates[index].dynamicStateLikeList.length; i++) {
                        if($scope.dynamicStates[index].dynamicStateLikeList[i].user.id == $scope.staff.user.id) {
                            $scope.dynamicStates[index].dynamicStateLikeList.splice(i, 1);
                            break;
                        }
                    }
                }
                $scope.$apply();
            },function(err){
                SweetAlert.swal({
                    title:"获取信息失败",
                    text:"与服务器交互出现异常："+err + "！",
                    type:"error"
                })
            }
        )
    };
    $scope.publishState = function () {
        if($('#summernote').summernote('code')=='<p><br></p>'||($('#summernote').summernote('code')=='')){
            SweetAlert.swal({
                title:"错误",
                text:"请您填写动态后再发表！",
                type:"error"
            })
        }else{
            AssociatesService.publishDynamicState(
                $scope.staff.user.id,
                $('#summernote').summernote('code'),
                function (data) {
                    $scope.dynamicStates.unshift(data);
                    $('#summernote').summernote('code',"<p><br></p>");
                    toaster.success({ body:"动态已成功发表！"});
                    $scope.$apply();
                },function(err){
                    SweetAlert.swal({
                        title:"获取信息失败",
                        text:"与服务器交互出现异常："+err + "！",
                        type:"error"
                    })
                }
            )
        }
    };
    $scope.isLike = function (dynamicState) {
        if(dynamicState.dynamicStateLikeList!=null&&dynamicState.dynamicStateLikeList.length>0){
            for (let i = 0; i < dynamicState.dynamicStateLikeList.length; i++) {
                if($scope.staff.user.id == dynamicState.dynamicStateLikeList[i].user.id){
                    return true;
                }
            }
        }
        return false;
    };
    $('#summernote').summernote({
        lang: 'zh-CN',
        placeholder: '最近你进步了嘛，和大家一起分享一下吧！',
    });
}


function StaffAnnouncementsCtrl($scope,SweetAlert,StaffService){
    $scope.staff = $scope.currentUser.user;
    StaffService.getAnnouncementsByEnterpriseIdAndDepartmentId(
        $scope.staff.enterprise.id,
        $scope.staff.department.id,
        function (data) {
            $scope.announcements = data;
            $scope.$apply();
        },function(err){
            SweetAlert.swal({
                title:"获取课程信息失败",
                text:"与服务器交互出现异常："+err + "！",
                type:"error"
            })
        }
    )
}


angular
    .module('fishing-path')
    .controller('ApplicationController',['$rootScope','$location', '$scope', '$state', 'SweetAlert','AuthService',
        ApplicationController])
    .controller('UserLoginCtrl',['$rootScope', '$scope', '$state','$location', '$window','SweetAlert','AuthService',
        UserLoginCtrl])
    .controller('EnterpriseRegisterCtrl',['$rootScope', '$scope', '$state', '$location','SweetAlert','AuthService','REG_CONSTANT',
        EnterpriseRegisterCtrl])
    // 企业控制器
    .controller('EnterpriseMainCtrl', EnterpriseMainCtrl)
    .controller('EnterpriseContactsCtrl',['$scope','SweetAlert','StaffService',EnterpriseContactsCtrl])
    .controller('EnterpriseUserInfoCtrl',['$scope','SweetAlert','toaster','EnterpriseService','AuthService',EnterpriseUserInfoCtrl])
    .controller('StaffsManagementCtrl',['$scope','SweetAlert','EnterpriseService','StaffService',StaffsManagementCtrl])
    .controller('StudyStatisticsCtrl',['$scope','SweetAlert','EnterpriseService','StaffService',StudyStatisticsCtrl])
    .controller('CoursesManagementCtrl',['$scope','SweetAlert','CourseService',CoursesManagementCtrl])
    .controller('EnterpriseAssociatesCtrl',['$scope','SweetAlert','toaster','AssociatesService','StaffService',EnterpriseAssociatesCtrl])
    .controller('EnterpriseAnnouncementsCtrl'['$scope','SweetAlert','EnterpriseService',EnterpriseAnnouncementsCtrl])
    .controller('AnnouncementManagementCtrl',['$scope','SweetAlert','EnterpriseService',AnnouncementManagementCtrl])
    // 员工控制器
    .controller('StaffMainCtrl', StaffMainCtrl)
    .controller('StaffContactsCtrl',['$scope','SweetAlert','StaffService',StaffContactsCtrl])
    .controller('CoursesLibraryCtrl',['$scope','SweetAlert','CourseService',CoursesLibraryCtrl])
    .controller('CourseDetailCtrl',['$scope','$stateParams','SweetAlert','CourseService',CourseDetailCtrl])
    .controller('StudyVideoCtrl',['$scope','$stateParams','SweetAlert','CourseService','StaffService',StudyVideoCtrl])
    .controller('LearningPathCtrl',['$scope','SweetAlert','StaffService',LearningPathCtrl])
    .controller('StaffAssociatesCtrl',['$scope','SweetAlert','toaster','AssociatesService','StaffService',StaffAssociatesCtrl])
    .controller('StaffAnnouncementsCtrl',['$scope','SweetAlert','StaffService',StaffAnnouncementsCtrl])
