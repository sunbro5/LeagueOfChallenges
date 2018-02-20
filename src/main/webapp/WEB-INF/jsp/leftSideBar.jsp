<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!-- start: Left SideBar -->
<c:url value="/login" var="loginUrl"/>
<c:url value="/showMap" var="showMapUrl"/>
<c:url value="/user/createChallenge" var="createChallengeUrl"/>
<c:url value="/registration" var="registrationUrl"/>
<c:url value="/user/friends" var="friendsUrl"/>
<c:url value="/user/messages" var="messagesUrl"/>
<c:url value="/user/userChallenges" var="userChallengesUrl"/>
<c:url value="/user/report" var="reportUrl"/>
<c:url value="/user/userProfile" var="userProfileUrl"/>
<c:url value="/user/userTeam" var="userTeamUrl"/>
<div id="sidebar-left" class="span2">
    <div class="nav-collapse sidebar-nav">
        <ul class="nav nav-tabs nav-stacked main-menu">
            <li><a href="/"><i class="icon-bar-chart"></i><span class="hidden-tablet"> Dashboard</span></a></li>
            <li><a href="${showMapUrl}"><i class="icon-eye-open"></i><span class="hidden-tablet">Mapa</span></a></li>
            <li><a href="${createChallengeUrl}"><i class="icon-dashboard"></i><span class="hidden-tablet">Vytvor challenge</span></a></li>
            <li><a href="${registrationUrl}"><i class="icon-edit"></i><span class="hidden-tablet"> <spring:message code="page.menu.registration.label"/></span></a></li>
            <sec:authorize access="isAuthenticated()">
                <li><a href="${friendsUrl}"><i class="halflings-icon white user"></i><span class="hidden-tablet"> <spring:message code="page.menu.friends.label"/></span></a></li>
                <li><a href="${userProfileUrl}"><i class="halflings-icon white user"></i><span class="hidden-tablet"> <spring:message code="menu.user.profile.title"/></span></a></li>
                <li><a href="${messagesUrl}"><i class="icon-envelope"></i><span class="hidden-tablet"> <spring:message code="page.menu.messages.label"/></span></a></li>
                <li><a href="${userChallengesUrl}"><i class="icon-lock"></i><span class="hidden-tablet"> <spring:message code="page.menu.userChallenges.label"/></span></a></li>
                <li><a href="${userTeamUrl}"><i class="icon-edit"></i><span class="hidden-tablet"> <spring:message code="page.menu.userTeam.label"/></span></a></li>
                <li><a href="${reportUrl}"><i class="icon-edit"></i><span class="hidden-tablet"> <spring:message code="page.menu.report.label"/></span></a></li>
            </sec:authorize>
            <sec:authorize access="isAnonymous()">
                <li><a href="${loginUrl}"><i class="icon-lock"></i><span class="hidden-tablet"> <spring:message code="page.menu.login"/></span></a></li>
            </sec:authorize>
        </ul>
    </div>
</div>
<!-- end: Left SideBar -->