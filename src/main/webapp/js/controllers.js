function MainCtrl() {

    this.userName = 'Example user';
    this.helloText = 'Welcome in SeedProject';
    this.descriptionText = 'It is an application skeleton for a typical AngularJS web app. You can use it to quickly bootstrap your angular webapp projects and dev environment for these projects.';

};

// 用户登录
function UserLoginCtrl($scope,$state) {
    $scope.userLogin = function () {
        $.get("userLogin",{account:$scope.account,password:$scope.password},function (data,statusText) {
            if(data.exception){
                alert("与服务器交互出现异常："+data.exception)
            }else{
                if(data!=""&&data!=null){
                    $state.go("index.main")
                }else{
                    alert("登录失败！");
                }
            }
        })
    }
}

angular
    .module('fishing-path')
    .controller('MainCtrl', MainCtrl)
    .controller('UserLoginCtrl',['$scope','$state'],UserLoginCtrl)