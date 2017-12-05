<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
<c:url value="/login" var="loginUrl"/>
<c:url value="/j_spring_security_logout" var="logoutUrl"/>

<div class="navbar">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand" href="<c:url value="/"/>"><span><spring:message code="page.title"/> </span></a>



            <!-- start: Header Menu -->
            <div class="nav-no-collapse header-nav">
                <sec:authorize access="isAuthenticated()">
                    <ul class="nav pull-right">
                        <div ng-app="MyApp">
                            <div ng-controller="testText">
                                <p>{{testMessage.userNickname}}</p>
                            </div>
                        </div>
                        <!-- start: User Dropdown -->
                        <li class="dropdown">
                            <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                                <i class="halflings-icon white user"></i> ${userName}
                                <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu">
                                <li class="dropdown-menu-title">
                                    <span><spring:message code="header.login.settings"/> </span>
                                </li>
                                <li><a href="#"><i class="halflings-icon user"></i> <spring:message code="menu.user.profile.title"/></a></li>
                                <form:form id="logout-form" action="${logoutUrl}"
                                           method="post">
                                    <li><a href="javascript:{}" onclick="document.getElementById('logout-form').submit(); return false;"><i class="halflings-icon off"></i> <spring:message code="menu.logout.title"/></a></li>
                                    <input type="hidden"
                                           name="${_csrf.parameterName}"
                                           value="${_csrf.token}"/>
                                </form:form>
                            </ul>
                        </li>

                        <!-- end: User Dropdown -->
                    </ul>
                </sec:authorize>
                <sec:authorize access="isAnonymous()">
                    <ul class="nav pull-right">
                        <li><a class="btn" href="${loginUrl}"><i class="halflings-icon white user"></i> Login</a></li>
                    </ul>
                </sec:authorize>
            </div>
            <!-- end: Header Menu -->

    </div>
  </div>
</div>
<!-- start: Header -->

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
<script>
    var app = angular.module("MyApp", []);

    app.controller("testText", function($scope, $http) {
        $http.get('testRest').
                success(function(data, status, headers, config) {
                    $scope.testMessage = data;
                }).
                error(function(data, status, headers, config) {
                    // log error
                });
    });
</script>
