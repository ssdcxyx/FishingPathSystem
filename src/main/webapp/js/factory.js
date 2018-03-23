function AuthService($http, $cookieStore,CODE_CONSTANT){

    var accessLevels = routingConfig.accessLevels
        , userRoles = routingConfig.userRoles
        , currentUser = $cookieStore.get('user') || { username: '', role: userRoles.public };

    $cookieStore.remove('user');

    function changeUser(user) {
        angular.extend(currentUser, user);
    }

    return {
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
            return user.role.title === userRoles.staff.title || user.role.title === userRoles.admin.title;
        },
        register: function(registerForm, success, error) {
            $.get("verifyVerificationCode", {
                msgId: registerForm.msgId,
                authCode: registerForm.authCode
            }, function (data, statusText) {
                if (data.exception) {
                    alert("与服务器交互出现异常：" + data.exception)
                } else {
                    var result = data;
                    if (result == CODE_CONSTANT.SUCCESS) {
                        $.post("userRegister", {
                            userName: registerForm.userName,
                            telephone: registerForm.telephone,
                            password: registerForm.password,
                            type: 'staff'
                        }, function (data, statusText) {
                            if (data.exception) {
                                alert("与服务器交互出现异常：" + data.exception)
                            } else {
                                var returnResult = data;
                                if (returnResult == CODE_CONSTANT.REPEAT) {
                                    $('#telephone').get(0).setCustomValidity("手机号码已注册");
                                    $('#telephone').get(0).reportValidity();
                                } else if (!!data) {
                                    let res = {}
                                    if(data.type === 'admin'){
                                        res = {username:data.username,role:userRoles.admin};
                                    }else if (data.type === "staff"){
                                        res = {username:data.username,role:userRoles.staff};
                                    }
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
        },
        login: function(user, success, error) {
            $.post("userLogin",user,function (data,statusText) {
                if(data.exception){
                    alert("与服务器交互出现异常："+data.exception)
                }else{
                    if(!!data){
                        let res = {}
                        if(data.type === 'admin'){
                            res = {username:data.username,role:userRoles.admin};
                        }else if (data.type === "staff"){
                            res = {username:data.username,role:userRoles.staff};
                        }
                        changeUser(res);
                        success(data);
                    }else{
                        // 反馈账号和密码错误
                        $('#password').get(0).setCustomValidity("登录名或密码错误");
                        $('#password').get(0).reportValidity();
                        error();
                    }
                }
            });
        },
        logout: function(success, error) {
            $http.post('/logout').success(function(){
                changeUser({
                    username: '',
                    role: userRoles.public
                });
                success();
            }).error(error);
        },
        accessLevels: accessLevels,
        userRoles: userRoles,
        user: currentUser
    };
}

angular
    .module('fishing-path')
    .factory('AuthService',['$http','$cookieStore','CODE_CONSTANT',AuthService])