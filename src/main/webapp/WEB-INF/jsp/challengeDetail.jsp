<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="mainHeader.jsp"/>

<!-- start: Content -->
<div id="content" class="span10 text-center">

    <h1><spring:message code="page.menu.userChallenges.label"/></h1>
    <jsp:include page="infoMessage.jsp"/>
    <table class="table">
        <thead>
        <tr>
            <th><spring:message code="userChallenges.form.game.label"/></th>
            <th><spring:message code="userChallenges.form.team.label"/></th>
            <th><spring:message code="userChallenges.form.start.label"/></th>
            <th><spring:message code="userChallenges.form.end.label"/></th>
            <th><spring:message code="userChallenges.form.description.label"/></th>
        </tr>
        </thead>
        <c:forEach items="${allActiveChallengesList}" var="activeChallenge">
            <tr>
                <th>${activeChallenge.gameName}</th>
                <th>${activeChallenge.challengerTeamName}</th>
                <th>${activeChallenge.challengeStart}</th>
                <th>${activeChallenge.challengeEnd}</th>
                <th>${activeChallenge.text}</th>
                <c:url value="/user/cancelChallenge" var="cancelChallengeUrl">
                    <c:param name="challengeId" value="${activeChallenge.challengeId}"/>
                </c:url>
                <th><a href="${cancelChallengeUrl}"><spring:message code="userChallenges.form.delete.label"/></a></th>
                <th><spring:message code="userChallenges.form.team.label"/></th>
            </tr>
        </c:forEach>
    </table>
</div>


<jsp:include page="mainFooter.jsp"/>