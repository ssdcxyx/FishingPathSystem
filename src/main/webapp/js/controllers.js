function MainCtrl() {

    this.userName = 'jxufer';
    this.helloText = '欢迎来到渔径';
    this.descriptionText = '当企业培训遇到人工智能';

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