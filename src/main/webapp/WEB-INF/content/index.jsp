<%--
  Created by IntelliJ IDEA.
  User: tiamo
  Date: 2018/3/12
  Time: 上午8:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html ng-app="fishing-path">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title set in pageTitle directive -->
    <title page-title></title>

    <!-- Font awesome -->
    <link href="${pageContext.request.contextPath}/font-awesome/css/font-awesome.css" rel="stylesheet">

    <!-- Bootstrap and Fonts -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    <!-- Main Inspinia CSS files -->
    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link id="loadBefore" href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">


</head>

<!-- ControllerAs syntax -->
<!-- Main controller with serveral data used in Inspinia theme on diferent view -->
<body ng-controller="ApplicationController as appCtrl">

<!-- Main view  -->
<div ui-view></div>

<!-- jQuery and Bootstrap -->
<script src="${pageContext.request.contextPath}/js/jquery/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/plugins/jquery-ui/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>

<!-- MetsiMenu -->
<script src="${pageContext.request.contextPath}/js/plugins/metisMenu/jquery.metisMenu.js"></script>

<!-- SlimScroll -->
<script src="${pageContext.request.contextPath}/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

<!-- Peace JS -->
<script src="${pageContext.request.contextPath}/js/plugins/pace/pace.min.js"></script>

<!-- Custom and plugin javascript -->
<script src="${pageContext.request.contextPath}/js/inspinia.js"></script>

<!-- Main Angular scripts-->
<script src="${pageContext.request.contextPath}/js/angular/angular.min.js"></script>
<script src="${pageContext.request.contextPath}/js/angular/angular-cookies.js"></script>
<script src="${pageContext.request.contextPath}/js/plugins/oclazyload/dist/ocLazyLoad.min.js"></script>
<script src="${pageContext.request.contextPath}/js/ui-router/angular-ui-router.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap/ui-bootstrap-tpls-1.1.2.min.js"></script>


<!-- Anglar App Script -->
<script src="${pageContext.request.contextPath}/js/app.js"></script>
<script src="${pageContext.request.contextPath}/js/config.js"></script>
<script src="${pageContext.request.contextPath}/js/directives.js"></script>
<script src="${pageContext.request.contextPath}/js/controllers.js"></script>
<script src="${pageContext.request.contextPath}/js/inspinia.js"></script>
<script src="${pageContext.request.contextPath}/js/factory.js"></script>
<script src="${pageContext.request.contextPath}/js/service.js"></script>
<script src="${pageContext.request.contextPath}/js/routingConfig.js"></script>

</body>
</html>
