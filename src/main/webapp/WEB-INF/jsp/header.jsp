<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url value="/login" var="loginUrl"/>
<c:url value="/j_spring_security_logout" var="logoutUrl"/>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
<script src="<c:url value="/resources/js/notificationAngular.js"/>"></script>

<c:url var="messageUrl" value="/user/messages"/>

<div class="navbar">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="btn btn-navbar" data-toggle="collapse"
               data-target=".top-nav.nav-collapse,.sidebar-nav.nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand" href="<c:url value="/"/>"><span><spring:message code="page.title"/> </span></a>

            <!-- start: Header Menu -->
            <div ng-app="headerMenuApp" class="nav-no-collapse header-nav">
                <sec:authorize access="isAuthenticated()">
                    <ul ng-controller="Ctrl" class="nav pull-right">

                        <li class="dropdown hidden-phone">
                            <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                                <i class="icon-bell"></i>
                                <span class="badge red">
								1 </span>
                            </a>
                                <%--Notifications--%>
                            <ul class="dropdown-menu notifications">
                                <li class="dropdown-menu-title">
                                    <span>You have 1 notifications</span>
                                    <a href="#refresh"><i class="icon-repeat"></i></a>
                                </li>
                                <li>
                                    <a href="#">
                                        <span class="icon blue"><i class="icon-user"></i></span>
                                        <span class="message">New user registration</span>
                                        <span class="time">1 min</span>
                                    </a>
                                </li>
                                <li class="dropdown-menu-sub-footer">
                                    <a>View all notifications</a>
                                </li>
                            </ul>
                        </li>
                            <%--/Notifications--%>
                            <%--Task--%>
                        <li class="dropdown hidden-phone">
                            <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                                <i class="icon-calendar"></i>
                                <span class="badge red">
								1 </span>
                            </a>
                            <ul class="dropdown-menu tasks">
                                <li class="dropdown-menu-title">
                                    <span>You have 1 tasks in progress</span>
                                    <a href="#refresh"><i class="icon-repeat"></i></a>
                                </li>
                                <li>
                                    <a href="#">
										<span class="header">
											<span class="title">iOS Development</span>
											<span class="percent"></span>
										</span>

                                        <div class="taskProgress progressSlim red">80</div>
                                    </a>
                                </li>
                                <li>
                                    <a class="dropdown-menu-sub-footer">View all tasks</a>
                                </li>
                            </ul>
                        </li>
                            <%--/Task--%>
                            <%--Message--%>
                        <li class="dropdown hidden-phone">
                            <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                                <i class="icon-envelope"></i>
                                <span class="badge red" ng-bind="messageNotifications.userMessages.length">
                                 </span>
                            </a>
                            <ul class="dropdown-menu messages">
                                <li class="dropdown-menu-title">
                                    <span>You have {{messageNotifications.userMessages.length}} messages</span>
                                    <a href="javascript:void(0)" ng-click="notificationRefresh()"><i class="icon-repeat"></i></a>
                                </li>
                                <li ng-repeat="message in messageNotifications.userMessages">
                                    <a href="/messages?friendMessages={{message.userNickname}}">
                                        <span class="avatar"><img src="" alt="Avatar"></span>
                                        <span class="header">
											<span class="from" ng-bind="message.userNickname">
										     </span>
											<span class="time">
										    	6 min
										    </span>
										</span>
                                        <span class="message" ng-bind="message.textPreview">
                                        </span>
                                    </a>
                                </li>

                                <li>
                                    <a href="${messageUrl}" class="dropdown-menu-sub-footer">View all messages</a>
                                </li>
                            </ul>
                        </li>
                            <%--/Message--%>
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
                                <li><a href="#"><i class="halflings-icon user"></i> <spring:message
                                        code="menu.user.profile.title"/></a></li>
                                <form:form id="logout-form" action="${logoutUrl}"
                                           method="post">
                                    <li><a href="javascript:{}"
                                           onclick="document.getElementById('logout-form').submit(); return false;"><i
                                            class="halflings-icon off"></i> <spring:message
                                            code="menu.logout.title"/></a></li>
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