function AuthService($http, $cookieStore,CODE_CONSTANT){

    var accessLevels = routingConfig.accessLevels
        , userRoles = routingConfig.userRoles
        , currentUser = $cookieStore.get('user') || { user: '', role: userRoles.public };

    function changeUser(user) {
        $cookieStore.remove('user');
        angular.extend(currentUser, user);
        $cookieStore.put('user',currentUser);
    }

    return {
        changeUser : function(user){
          changeUser(user);
        },
        authorize: function(accessLevel, role) {
            if(role === undefined) {
                role = currentUser.role;
            }
            return accessLevel.bitMask & role.bitMask;
        },
        isLoggedIn: function(user) {
            if(user === undefined) {
                user = currentUser;
            }
            return user.role.title;
        },
        enterpriseRegister: function(registerForm,SweetAlert, success, error) {
            $.get("verifyVerificationCode", {
                msgId: registerForm.msgId,
                authCode: registerForm.authCode
            }, function (data, statusText) {
                if (data.exception) {
                    SweetAlert.swal({
                        title:"发送验证码失败",
                        text:"与服务器交互出现异常："+data.exception,
                        type:"error"
                    })
                } else {
                    var result = data;
                    if (result == CODE_CONSTANT.SUCCESS) {
                        $.post("enterpriseRegister", {
                            telephone: registerForm.telephone,
                            password: registerForm.password
                        }, function (data, statusText) {
                            if (data.exception) {
                                SweetAlert.swal({
                                    title:"企业用户注册失败",
                                    text:"与服务器交互出现异常："+data.exception,
                                    type:"error"
                                })
                            } else {
                                var returnResult = data;
                                if (returnResult == CODE_CONSTANT.REPEAT) {
                                    $('#telephone').get(0).setCustomValidity("手机号码已注册");
                                    $('#telephone').get(0).reportValidity();
                                } else if (!!data) {
                                    var res = {}
                                    res = {user: data, role:userRoles.enterprise}
                                    changeUser(res);
                                    success();
                                }
                            }
                        }, "json");
                    } else if (result == CODE_CONSTANT.LOSE) {
                        $('#authCode').get(0).setCustomValidity("验证码已过期");
                        $('#authCode').get(0).reportValidity();
                    } else if (result == CODE_CONSTANT.NULL) {
                        $('#authCode').get(0).setCustomValidity("请先发送验证码");
                        $('#authCode').get(0).reportValidity();
                    } else if (result == CODE_CONSTANT.ERROR) {
                        // 反馈验证码错误
                        $('#authCode').get(0).setCustomValidity("验证码错误");
                        $('#authCode').get(0).reportValidity();
                    } else if (result == CODE_CONSTANT.TRIED) {
                        // 反馈验证码错误
                        $('#authCode').get(0).setCustomValidity("验证码已失效");
                        $('#authCode').get(0).reportValidity();
                    } else {
                        $('#authCode').get(0).setCustomValidity("未知错误");
                        $('#authCode').get(0).reportValidity();
                    }
                }
            })
            return false;
        },
        login: function(user,SweetAlert,success, error) {
            $.post("userLogin",user,function (data,statusText) {
                if(data.exception){
                    error();
                }else{
                    if(!!data){
                        let res = {}
                        if(data.user.role==3){
                            res = {user:data,role:userRoles.admin};
                        }else if (data.user.role == 2){
                            res = {user:data,role:userRoles.enterprise};
                        } else if (data.user.role == 1){
                            res = {user:data,role:userRoles.staff};
                        }
                        changeUser(res);
                        success(currentUser);
                    }else{
                        // 反馈账号和密码错误
                        $('#telephone').parent().addClass("has-error");
                        $('#telephone').get(0).setCustomValidity("登录名或密码错误");
                        $('#telephone').get(0).reportValidity();
                    }
                }
            });
            return false;
        },
        logout: function(success) {
            $cookieStore.remove('user');
            currentUser = {user: '',role: userRoles.public};
            $cookieStore.put('user',currentUser);
            success();
        },
        accessLevels: accessLevels,
        userRoles: userRoles,
        user: currentUser
    };
}

function EnterpriseService(){
    return {
        getAllEnterpriseTypes : function (success,error) {
            $.get("getAllEnterpriseTypes",null,function (data,statusText) {
                if(data.exception){
                    error(data.exception);
                }else{
                    success(data);
                }
            })
        },
        getAllDepartments : function (success,error) {
            $.get("getAllDepartments",null,function (data,statusText) {
                if(data.exception){
                    error(data.exception);
                }else{
                    success(data);
                }
            })
        },
        getAllPostTypes : function (success,error) {
            $.get("getAllPostTypes",null,function (data,statusText) {
                if(data.exception){
                    error(data.exception);
                }else{
                    success(data);
                }
            })
        },
        updateEnterpriseInfo : function (enterprise,success,error) {
            $.post('updateEnterpriseInfo',enterprise,function(data,statusText){
                if(data.exception){
                    error(data.exception);
                }else{
                    success(data);
                }
            });
        },
        publishAnnouncement:function (announcement,success,error) {
            $.post('publishAnnouncement',announcement,function (data,statusText) {
                if(data.exception){
                    error(data.exception);
                }else{
                    success(data);
                }
            })
        },
        getAnnouncementsByEnterpriseId:function (enterprise_id,success,error) {
            $.get('getAnnouncementsByEnterpriseId',{'enterpriseId':enterprise_id},function (data,statusText) {
                if(data.exception){
                    error(data.exception);
                }else{
                    success(data);
                }
            })
        },
        getEnterpriseDTOByEnterpriseId:function (enterprise_id,success,error) {
            $.get('getEnterpriseDTOByEnterpriseId',{'enterpriseId':enterprise_id},function (data,statusText) {
                if(data.exception){
                    error(data.exception);
                }else{
                    success(data);
                }
            })
        },
        getEnterpriseByEnterpriseId:function (enterprise_id,success,error) {
            $.get('getEnterpriseByEnterpriseId',{'enterpriseId':enterprise_id},function (data,statusText) {
                if(data.exception){
                    error(data.exception);
                }else{
                    success(data);
                }
            })
        },
        getAllCourseDTOs:function (success,error) {
            $.get('getAllCourseDTOs',function (data,statusText) {
                if(data.exception){
                    error(data.exception);
                }else{
                    success(data);
                }
            })
        },
    }
}

function StaffService(){
    return {
        getStaffsByEnterpriseId : function (enterprise_id,success,error) {
           $.get('getStaffsByEnterpriseId',enterprise_id,function (data,statusText) {
               if(data.exception){
                   error(data.exception);
               }else{
                   success(data);
               }
           })
        },
        getAnnouncementsByEnterpriseIdAndDepartmentId:function (enterprise_id,department_id,success,error) {
            $.get('getAnnouncementsByEnterpriseIdAndDepartmentId',{'enterpriseId':enterprise_id,'departmentId':department_id},function (data,statusText) {
                if(data.exception){
                    error(data.exception);
                }else{
                    success(data);
                }
            })
        },
        storeLearningRecord:function (staff_id,course_id,course_section_id,success,error) {
            $.post('storeLearningRecord',{'staffId':staff_id,'courseId':course_id,'courseSectionId':course_section_id},function (data,statusText) {
                if(data.exception){
                    error(data.exception);
                }else{
                    success(data);
                }
            })
        },
        getLearningPathByStaffId:function (staff_id,success,error) {
            $.get('getLearningPathByStaffId',{'staffId':staff_id},function (data,statusText) {
                if(data.exception){
                    error(data.exception);
                }else{
                    success(data);
                }
            })
        },
        getAllStaffsByEnterpriseIdAndDepartmentIdAndPostTypeId:function (enterprise_id,department_id,post_type_id,success,error,) {
            $.get('getAllStaffsByEnterpriseIdAndDepartmentIdAndPostTypeId',{'enterpriseId':enterprise_id,'departmentId':department_id,"postTypeId":post_type_id},function (data,statusText) {
                if(data.exception){
                    error(data.exception);
                }else{
                    success(data);
                }
            })
        },
        getAllStaffsByEnterpriseIdAndDepartmentId:function (enterprise_id,department_id,success,error,) {
            $.get('getAllStaffsByEnterpriseIdAndDepartmentId',{'enterpriseId':enterprise_id,'departmentId':department_id},function (data,statusText) {
                if(data.exception){
                    error(data.exception);
                }else{
                    success(data);
                }
            })
        }
    }
}

function CourseService(){
    return {
        getAllCourses : function (success,error) {
            $.get('getAllCourses',function (data,statusText) {
                if(data.exception){
                    error(data.exception);
                }else{
                    success(data);
                }
            })
        },
        findCoursesByPage : function (page,success,error) {
            $.get('findCoursesByPage',page,function (data,statusText) {
                if(data.exception){
                    error(data.exception);
                }else{
                    success(data);
                }
            })
        },
        findCoursesLength : function (success,error) {
            $.get('findCoursesLength',function (data,statusText) {
                if(data.exception){
                    error(data.exception);
                }else{
                    success(data);
                }
            })
        },
        findCourseByCourseId : function (courseId,success,error) {
            $.get('findCourseByCourseId',{'courseId':courseId},function (data,statusText) {
                if(data.exception){
                    error(data.exception);
                }else{
                    success(data);
                }
            })
        },
        findCourseSectionByCourseSectionId:function (courseSectionId,success,error) {
            $.get('findCourseSectionByCourseSectionId',{'courseSectionId':courseSectionId},function (data,statusText) {
                if(data.exception){
                    error(data.exception);
                }else{
                    success(data);
                }
            })
        },
        findCourseDTOsByPage:function (page,success,error) {
            $.get('findCourseDTOsByPage',page,function (data,statusText) {
                if(data.exception){
                    error(data.exception);
                }else{
                    success(data);
                }
            })
        },
    }
}

function AssociatesService(){
    return{
        getAllDynamicStatesByEnterpriseId:function (enterprise_id,success,error) {
            $.get('getAllDynamicStatesByEnterpriseId',{"enterpriseId":enterprise_id},function (data,statusText) {
                if(data.exception){
                    error(data.exception);
                }else{
                    success(data);
                }
            })
        },
        publishDynamicState:function (user_id,content,success,error) {
            $.post('publishDynamicState',{"userId":user_id,"content":content},function (data,statusText) {
                if(data.exception){
                    error(data.exception);
                }else{
                    success(data);
                }
            })
        },
        publishDynamicStateComment:function (user_id,content,dynamic_state_id,success,error) {
            $.post('publishDynamicStateComment',{"userId":user_id,"content":content,'dynamicStateId':dynamic_state_id},function (data,statusText) {
                if(data.exception){
                    error(data.exception);
                }else{
                    success(data);
                }
            })
        },
        publishDynamicStateLike:function (user_id,dynamic_state_id,success,error) {
            $.post('publishDynamicStateLike',{"userId":user_id,'dynamicStateId':dynamic_state_id},function (data,statusText) {
                if(data.exception){
                    error(data.exception);
                }else{
                    success(data);
                }
            })
        },
    }
}


angular
    .module('fishing-path')
    .factory('AuthService',['$http','$cookieStore','CODE_CONSTANT',AuthService])
    .factory('EnterpriseService',EnterpriseService)
    .factory('StaffService',StaffService)
    .factory('CourseService',CourseService)
    .factory('AssociatesService',AssociatesService)