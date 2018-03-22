function Session() {
    this.create = function (userId, userRole) {
        this.userId = userId;
        this.userRole = userRole;
    };
    this.destroy = function () {
        this.userId = null;
        this.userRole = null;
    };
    return this;
}

angular
    .module('fishing-path')
    .service('Session',Session)